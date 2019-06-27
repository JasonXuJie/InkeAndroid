package com.jason.module_my.ui.activity

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.jason.module_my.R
import com.jason.tools.base.BaseActivity
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: BaseActivity<BasePresenter<IBaseView>>() {

    private val items = arrayOf("账号与安全","清除缓存","消息与推送通知","诊断网络")

    override fun createPresenter(): BasePresenter<IBaseView>? = null

    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initViews() {
        setToolBarByBack(toolBar as Toolbar,"设置",null)
        buildFuncList()
        btn_logout.setOnClickListener { Logger.e("登出") }

    }

    override fun requestData() {

    }

    private fun buildFuncList(){
       for (item:String in items){
           val itemView = View.inflate(this@SettingActivity,R.layout.item_setting_func,null)
           val tv_func_name = itemView.findViewById<TextView>(R.id.tv_func_name)
           tv_func_name.text = item
           setting_container.addView(itemView)
       }
    }

}