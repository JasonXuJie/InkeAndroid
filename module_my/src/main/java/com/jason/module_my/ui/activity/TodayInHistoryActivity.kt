package com.jason.module_my.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jason.module_my.R
import com.jason.module_my.ui.adapter.HistoryAdapter
import com.jason.module_my.viewmodel.HistoryListViewModel
import com.jason.tools.base.BaseActivity
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import com.jason.tools.config.RouterConfig
import kotlinx.android.synthetic.main.activity_today_in_history.*

@Route(path = RouterConfig.TODAY_PATH)
class TodayInHistoryActivity : BaseActivity<BasePresenter<IBaseView>>() {

    private lateinit var viewModel:HistoryListViewModel

    private val adapter by lazy { HistoryAdapter() }

    override fun createPresenter(): BasePresenter<IBaseView>? = null

    override fun getLayoutId(): Int = R.layout.activity_today_in_history

    override fun initViews() {
        rv_history.layoutManager = LinearLayoutManager(this)
        rv_history.setHasFixedSize(true)
        img_back.setOnClickListener { finish() }
        abl_history.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange){
                tv_title.visibility = View.VISIBLE
            } else{
                if (tv_title.visibility == View.VISIBLE) tv_title.visibility = View.GONE
            }
        }
        rv_history.adapter = adapter
    }

    override fun requestData() {
        viewModel = ViewModelProviders.of(this).get(HistoryListViewModel::class.java)
        viewModel.list.observe(this, Observer { data ->
            if (data == null) {
                showLoading()
            } else {
                hideLoading()
                adapter.addData(data)
                adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                    val bundle = Bundle()
                    bundle.putString("title", data[position].title)
                    bundle.putString("id", data[position].e_id)
                    openActivityByParams(HistoryDetailsActivity::class.java, bundle)
                }
            }
        })
        viewModel.errMsg.observe(this, Observer {
            //Toast.makeText(TestActivity.this,s,Toast.LENGTH_SHORT).show();
            hideLoading()
        })
    }
}