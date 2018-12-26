package com.jason.module_my.mvp.ui.activity

import android.support.v7.widget.Toolbar
import com.jason.module_my.R
import com.jason.tools.base.BaseActivity
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import kotlinx.android.synthetic.main.activity_luck.*

/**
 * Created by jason on 2018/12/24.
 */
class LuckActivity : BaseActivity<BasePresenter<IBaseView>>() {

    override fun createPresenter(): BasePresenter<IBaseView>? = null


    override fun getLayoutId(): Int = R.layout.activity_luck

    override fun initViews() {
        val toolBar = findViewById<Toolbar>(com.jason.tools.R.id.toolBar)
        setToolBarByBack(toolBar, "星座运势", null)
        rg_luck.setOnCheckedChangeListener { _, checkedId ->{

        } }
        et_luck.text.toString()
    }

    override fun requestData() {

    }


}