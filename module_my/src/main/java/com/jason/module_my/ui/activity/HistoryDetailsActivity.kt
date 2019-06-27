package com.jason.module_my.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.Toolbar
import android.view.View
import com.jason.module_my.R
import com.jason.module_my.model.bean.DetailsResult
import com.jason.module_my.ui.adapter.PicAdapter
import com.jason.module_my.viewmodel.DetailsViewModel
import com.jason.tools.base.BaseActivity
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import com.jason.tools.netimage.ImageLoader
import kotlinx.android.synthetic.main.activity_history_details.*

class HistoryDetailsActivity : BaseActivity<BasePresenter<IBaseView>>(),View.OnClickListener {

    private lateinit var data: DetailsResult

    override fun createPresenter(): BasePresenter<IBaseView> ? = null

    override fun getLayoutId(): Int = R.layout.activity_history_details

    override fun initViews() {
       val title = intent.getStringExtra("title")
       setToolBarByBack(toolBar as Toolbar,title,null)
       img_pic.setOnClickListener(this)
       layout_photo.setOnClickListener(this)
    }

    override fun requestData() {
        val id = intent.getStringExtra("id")
        val viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.requestDetails(id)
        viewModel.data.observe(this, Observer<List<DetailsResult>> {
            if (it ==null){
                showLoading()
            }else{
                hideLoading()
                data = it[0]
                if (data.picUrl.size == 1) {
                    img_pic.visibility = View.VISIBLE
                    ImageLoader.loadImage(this@HistoryDetailsActivity, data.picUrl[0].url, img_pic)
                } else {
                    vp_pics.visibility = View.VISIBLE
                    val adapter = PicAdapter(data.picUrl)
                    vp_pics.adapter = adapter
                    adapter.setOnItemClickListener(object :PicAdapter.OnPicItemListener{
                        override fun onClick(url: String, position: Int) {
                            layout_photo.visibility = View.VISIBLE
                            ImageLoader.loadImage(this@HistoryDetailsActivity, url, photoView)
                        }

                    })
                }
                tv_content.text = data.content
            }
        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.img_pic->{
                layout_photo.visibility=View.VISIBLE
                ImageLoader.loadImage(this,data.picUrl[0].url,photoView)
            }
            R.id.layout_photo->layout_photo.visibility = View.GONE
        }
    }


}