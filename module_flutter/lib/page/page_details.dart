import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:module_flutter/config/route_config.dart';
import 'package:module_flutter/page/page_test.dart';
import 'package:module_flutter/bean/movie_detail_result_entity.dart';
import 'package:module_flutter/bean/still_result_entity.dart';
import 'package:module_flutter/bean/comment_result_entity.dart';
import 'dart:ui';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'package:module_flutter/config/app_config.dart';
import 'package:module_flutter/utils/screen_util.dart';
import 'package:like_button/like_button.dart';
import 'package:module_flutter/http/http_manager.dart';
import 'package:module_flutter/http/api.dart';
import 'package:module_flutter/components/base_widget.dart';
import 'package:module_flutter/page/page_photo_details.dart';

class DetailsPage extends StatefulWidget {
  final Map params;

  DetailsPage(this.params);

  @override
  _State createState() => _State();
}

class _State extends State<DetailsPage> {
  MovieDetailResultEntity _detailData;
  StillResultEntityEntity _stillData;
  CommentResultEntity _commentData;
  double _ratingAverage = 0;
  bool _isOpenIntro = false;

  @override
  void initState() {
    super.initState();
    _requestData();
  }

  _requestData() async {
    ///获取详情数据
    var detailResponse = await HttpManager.getInstance()
        .get(ApiService.movieDetailsUrl(widget.params['id']));
    _detailData = MovieDetailResultEntity.fromJson(detailResponse);

    ///获取剧照
    var stillResponse = await HttpManager.getInstance()
        .get(ApiService.moviePhotosUrl(widget.params['id'], 6));
    _stillData = StillResultEntityEntity.fromJson(stillResponse);

    ///获取评论区
    var commentResponse = await HttpManager.getInstance()
        .get(ApiService.movieCommentsUrl(widget.params['id'], 0, 8));
    _commentData = CommentResultEntity.fromJson(commentResponse);
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.white,
      body: CustomScrollView(
        slivers: <Widget>[
          _buildAppBar(),
          _buildBody(),
          _buildComments(),
        ],
      ),
    );
  }

  SliverAppBar _buildAppBar() {
    return SliverAppBar(
      leading: GestureDetector(
        child: Image.asset('lib/src/back.png',width: 8.0,height: 8.0,),
        onTap: (){
            FlutterBoost.singleton.closePageForContext(context);
        },
      ),
      title: Text(widget.params['title'] ?? ''),
      expandedHeight: 300.0,
      pinned: true,
      centerTitle: true,
      flexibleSpace: FlexibleSpaceBar(
        collapseMode: CollapseMode.parallax,
        background: Container(
            margin: EdgeInsets.only(
                top: ScreenUtil.getSafeTopPadding(context) + 56),
            child: Stack(
              ///高斯模糊效果
              children: <Widget>[
                Image.network(
                  widget.params['image'],
                  width: ScreenUtil.getDeviceWidth(context),
                  height: 300,
                  fit: BoxFit.fill,
                ),
                BackdropFilter(
                  filter: ImageFilter.blur(sigmaX: 3, sigmaY: 3),
                  child: Container(
                    color: Colors.white.withOpacity(0.1),
                    width: ScreenUtil.getDeviceWidth(context),
                    height: 300,
                  ),
                ),
                Align(
                  alignment: Alignment.center,
                  child: ClipRRect(
                      borderRadius: BorderRadius.circular(10),
                      child: GestureDetector(
                        child: Image.network(
                          widget.params['image'],
                          height: 200.0,
                        ),
                        onTap: () {
                          if (_detailData != null) {
                            FlutterBoost.singleton.openPage(
                                RouteConfig.nativeWeb,
                                {
                                  "query": {'url': _detailData.mobileUrl}
                                },
                                animated: true);
                          }
                        },
                      )),
                )
              ],
            )),
      ),
    );
  }

  Widget _buildBody() {
    if (_commentData != null) {
      return SliverToBoxAdapter(
        child: Column(
          children: <Widget>[
            _buildInfo(),
            _buildMyRatingView(),
            Container(
              height: 5.0,
              color: AppColors.color_e6,
            ),
            _buildSummery(),
            _buildDirectorsAndCasts(),
            _buildStill(),
            Container(
              height: 5.0,
              color: AppColors.color_e6,
            ),
            Padding(
              padding: const EdgeInsets.only(top: 20.0, bottom: 10.0),
              child: Text(
                '评论区',
                style: const TextStyle(
                    color: Colors.black,
                    fontSize: 15.0,
                    fontWeight: FontWeight.bold),
              ),
            ),
          ],
        ),
      );
    } else {
      return SliverToBoxAdapter(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context),
            BaseWidget.buildShimmer(context, width: 300.0),
            BaseWidget.buildShimmer(context, width: 200.0),
            BaseWidget.buildShimmer(context, width: 100.0),
            BaseWidget.buildShimmer(context, width: 50.0),
          ],
        ),
      );
    }
  }

  ///电影基本信息
  Padding _buildInfo() {
    var stringBuffer = StringBuffer();
    _detailData.casts
        .map((cast) => {stringBuffer.write('${cast.name} ')})
        .toList();
    double descWidth = ScreenUtil.getDeviceWidth(context) - 140;
    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.only(bottom: 10.0),
                child: Text(
                  _detailData.title,
                  style: TextStyle(
                      fontSize: 16.0,
                      color: AppColors.color_66,
                      fontWeight: FontWeight.bold),
                ),
              ),
              _buildBasicText(
                  descWidth, '${_detailData.year}/${_detailData.genres}'),
              _buildBasicText(descWidth, '原名:${_detailData.originalTitle}'),
              _buildBasicText(descWidth, '导演:${_detailData.directors[0].name}'),
              _buildBasicText(descWidth, '主演:${stringBuffer.toString()}'),
            ],
          ),
          Container(
            width: 100.0,
            height: 100.0,
            decoration: BoxDecoration(
                color: AppColors.white,
                borderRadius: BorderRadius.circular(6),
                boxShadow: [
                  BoxShadow(
                      color: Theme.of(context).primaryColor,
                      offset: Offset(1, 1),
                      blurRadius: 3)
                ]),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Text(
                  '综合评分',
                  style: TextStyle(fontSize: 12, color: AppColors.color_9d),
                ),
                Text(
                  _detailData.rating.average.toString(),
                  style: TextStyle(
                      fontSize: 18,
                      color: AppColors.color_66,
                      fontWeight: FontWeight.bold),
                ),
                _buildRatingView(_detailData.rating.average / 2, 12),
                Text(
                  '${_detailData.ratingsCount}人',
                  style: TextStyle(fontSize: 12, color: AppColors.color_9d),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  ///基本信息文字
  Container _buildBasicText(double width, String text) {
    return Container(
      width: width,
      child: Text(
        text,
        style: TextStyle(fontSize: 13, color: AppColors.color_9d),
        overflow: TextOverflow.ellipsis,
        maxLines: 1,
      ),
    );
  }

  ///电影评分控件
  SmoothStarRating _buildRatingView(double rating, double size) {
    return SmoothStarRating(
      allowHalfRating: true,
      starCount: 5,
      rating: rating,
      size: size,
      color: AppColors.color_fc3,
      borderColor: AppColors.color_e6,
    );
  }

  ///我要评分控件
  Container _buildMyRatingView() {
    return Container(
      height: 48.0,
      margin: const EdgeInsets.only(
          top: 10.0, bottom: 20.0, left: 15.0, right: 15.0),
      decoration: BoxDecoration(
        border: Border.all(color: AppColors.color_fc3, width: 2.0),
        borderRadius: BorderRadius.circular(4.0),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text(
            '我来评分:',
            style: TextStyle(color: AppColors.color_fc3, fontSize: 16.0),
          ),
          Padding(
            padding: const EdgeInsets.only(left: 10.0),
            child: SmoothStarRating(
              allowHalfRating: true,
              starCount: 5,
              rating: _ratingAverage,
              size: 20,
              color: AppColors.color_fc3,
              borderColor: AppColors.color_e6,
              onRatingChanged: (rate) {
                setState(() {
                  _ratingAverage = rate;
                });
              },
            ),
          ),
        ],
      ),
    );
  }

  ///简介
  Container _buildSummery() {
    return Container(
      padding: const EdgeInsets.only(
          top: 20.0, bottom: 10.0, left: 10.0, right: 10.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(bottom: 10.0),
            child: Text(
              '剧情介绍',
              style: const TextStyle(
                  color: Colors.black,
                  fontSize: 15.0,
                  fontWeight: FontWeight.bold),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 10.0),
            child: Stack(
              children: <Widget>[
                Positioned(
                  child: Text(
                    _detailData.summary,
                    style: TextStyle(
                        fontSize: 14.0, color: AppColors.color_9d, height: 1.2),
                    maxLines: _isOpenIntro ? 20 : 4,
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
                Visibility(
                  visible: !_isOpenIntro,
                  child: Positioned(
                      right: 0,
                      bottom: 0,
                      width: 40,
                      height: 24,
                      child: FlatButton(
                          padding: const EdgeInsets.all(0),
                          color: AppColors.white,
                          onPressed: () {
                            setState(() {
                              _isOpenIntro = true;
                            });
                          },
                          child: Text(
                            "展开",
                            style: TextStyle(
                                fontSize: 14,
                                height: 1.2,
                                color: Theme.of(context).primaryColor),
                          ))),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  ///导演及演员
  Container _buildDirectorsAndCasts() {
    List data = [];
    for (int i = 0; i < _detailData.directors.length; i++) {
      data.add(_detailData.directors[i]);
    }
    for (int i = 0; i < _detailData.casts.length; i++) {
      data.add(_detailData.casts[i]);
    }
    var list = ListView.builder(
        scrollDirection: Axis.horizontal,
        itemCount: data.length,
        itemBuilder: (BuildContext context, int position) {
          return Container(
            child: GestureDetector(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Image.network(
                    data[position].avatars.small,
                    width: 80.0,
                    height: 100.0,
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 5.0),
                    child: Text(
                      data[position].name,
                      style: TextStyle(
                        fontSize: 13.0,
                      ),
                    ),
                  ),
                ],
              ),
              onTap: () {
                FlutterBoost.singleton.openPage(RouteConfig.nativeWeb, {
                  'query': {'url': data[position].alt}
                });
//                RouteUtil.pushByWidget(context,
//                    Web(title: data[position].name, url: data[position].alt));
              },
            ),
          );
        });
    return Container(
      padding: const EdgeInsets.fromLTRB(0.0, 10.0, 0.0, 10.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(left: 10.0),
            child: Text(
              '演职人员',
              style: const TextStyle(
                  color: Colors.black,
                  fontSize: 15.0,
                  fontWeight: FontWeight.bold),
            ),
          ),
          Container(
            height: 150.0,
            child: list,
          )
        ],
      ),
    );
  }

  ///剧照
  Padding _buildStill() {
    List<Widget> widget = [];
    int photoCount = _stillData.photos[0].photosCount;
    for (StillResultEntityPhoto item in _stillData.photos) {
      widget.add(ClipRRect(
        borderRadius: BorderRadius.circular(2),
        child: Padding(
          padding: const EdgeInsets.only(right: 4),
          child: Image.network(
            item.image,
            fit: BoxFit.fill,
            width: 220,
            height: 150,
          ),
        ),
      ));
    }
    widget.add(FlatButton(
        padding: const EdgeInsets.all(0),
        onPressed: () {
          Map map = Map();
          map['id'] = _detailData.id;
          map['count'] = photoCount;
          Navigator.of(context).push(MaterialPageRoute(builder: (context){
              return PhotoPage(arguments: map,);
          }));
        },
        child: ClipRRect(
          borderRadius: BorderRadius.circular(2),
          child: Container(
            width: 150,
            height: 150,
            color: AppColors.color_9d,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  '全部剧照',
                  style: TextStyle(fontSize: 14.0, color: AppColors.color_ff),
                ),
                Container(
                  color: AppColors.color_ff,
                  width: 75,
                  height: 1,
                  margin: const EdgeInsets.symmetric(vertical: 4),
                ),
                Text(
                  '$photoCount张',
                  style: TextStyle(fontSize: 12, color: AppColors.color_ff),
                )
              ],
            ),
          ),
        )));
    return Padding(
      padding: const EdgeInsets.all(10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.only(bottom: 10.0),
            child: Text(
              '剧照',
              style: const TextStyle(
                  color: Colors.black,
                  fontSize: 15.0,
                  fontWeight: FontWeight.bold),
            ),
          ),
          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: widget,
            ),
          ),
        ],
      ),
    );
  }

  ///评论区
  Widget _buildComments() {
    if (_commentData != null) {
      return SliverList(
        delegate: SliverChildBuilderDelegate(
          (BuildContext context, int index) {
            CommantResultCommants itemData = _commentData.comments[index];
            return Container(
              color: AppColors.color_ff,
              padding: const EdgeInsets.all(10),
              margin: const EdgeInsets.only(top: 2),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  ClipOval(
                    child: Image.network(
                      itemData.author.avatar,
                      width: 36.0,
                      height: 36.0,
                    ),
                  ),
                  Expanded(
                      child: Padding(
                    padding: const EdgeInsets.only(left: 10.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              children: <Widget>[
                                Text(
                                  itemData.author.name,
                                  style: TextStyle(
                                      fontSize: 12.0,
                                      color: AppColors.color_66),
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(left: 4.0),
                                  child: _buildRatingView(
                                      itemData.rating.value / 2, 12),
                                ),
                              ],
                            ),
                            Expanded(
                              child: LikeButton(
                                size: 25.0,
                                likeCount: itemData.usefulCount,
                                mainAxisAlignment: MainAxisAlignment.end,
                              ),
                            ),
                          ],
                        ),
                        Text(
                          itemData.content,
                          style: TextStyle(
                              fontSize: 14.0,
                              color: AppColors.color_66,
                              height: 1.1),
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: <Widget>[
                            Text(
                              itemData.createdAt,
                              style: TextStyle(
                                  fontSize: 12.0, color: AppColors.color_66),
                            ),
                          ],
                        )
                      ],
                    ),
                  ))
                ],
              ),
            );
          },
          childCount: _commentData.comments.length,
        ),
      );
    } else {
      return SliverToBoxAdapter(
        child: Container(),
      );
    }
  }
}
