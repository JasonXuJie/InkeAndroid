package com.jason.plugin;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * Paging的使用
 * **/
public class PagingActivity extends BaseActivity {

    private RecyclerView rv_paging;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_paging;
    }

    @Override
    public void initViews() {
        rv_paging = findViewById(R.id.rv_paging);
        rv_paging.setLayoutManager(new LinearLayoutManager(this));
        final MyAdapter myAdapter = new MyAdapter();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)//配置分页加载的数量
                .setEnablePlaceholders(false)//配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10)//初始化加载的数量
                .build();
        /**
         * LiveData<PagedList<DataBean>> 用LivePagedListBuilder生成
         * LivePagedListBuilder 用 DataSource.Factory<Integer,DataBean> 和PagedList.Config.Builder 生成
         * DataSource.Factory<Integer,DataBean> 用 PositionalDataSource<DataBean>
         */
        LiveData<PagedList<DataBean>> liveData = new LivePagedListBuilder(new MyDataSourceFactory(),config)
                .build();
        liveData.observe(this, new Observer<PagedList<DataBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<DataBean> dataBeans) {
                myAdapter.submitList(dataBeans);
            }
        });
        rv_paging.setAdapter(myAdapter);
    }

    @Override
    public void requestData() {

    }


    private class DataBean{
        public int id;
        public String content;
    }

    private List<DataBean> loadData(int start,int count){
        List<DataBean> list = new ArrayList<>();
        for (int i = 0;i<count;i++){
            DataBean data = new DataBean();
            data.id = start +i;
            data.content = "测试的内容=" + data.id;
            list.add(data);
        }
        return list;
    }


    private class MyDataSourceFactory extends DataSource.Factory<Integer,DataBean>{

        @Override
        public DataSource<Integer, DataBean> create() {
            return new MyDataSource();
        }
    }


    private class MyDataSource extends PositionalDataSource<DataBean>{

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataBean> callback) {
            callback.onResult(loadData(0,10),0,10);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataBean> callback) {
            callback.onResult(loadData(params.startPosition,10));
        }
    }



    private class MyAdapter extends PagedListAdapter<DataBean,MyViewHolder>{

        protected MyAdapter() {
            super(mDiffCallBack);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_2,null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                DataBean data = getItem(position);
                holder.text1.setText(String.valueOf(position));
                holder.text2.setText(String.valueOf(data.content));
        }
    }


    private DiffUtil.ItemCallback<DataBean> mDiffCallBack = new DiffUtil.ItemCallback<DataBean>() {
        @Override
        public boolean areItemsTheSame(DataBean oldItem, DataBean newItem) {
            Log.d("DiffCallback","areItemsTheSame");
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(DataBean oldItem, DataBean newItem) {
            Log.d("DiffCallback","areContentsTheSame");
            return (oldItem == newItem);
        }
    };




    private class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView text1;
        public TextView text2;

        public MyViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text1.setTextColor(Color.RED);
            text2 = itemView.findViewById(android.R.id.text2);
            text2.setTextColor(Color.BLUE);
        }
    }




}

