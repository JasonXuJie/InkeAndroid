package com.jason.module_my.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.module_my.R
import com.jason.module_my.ui.activity.PairingActivity
import com.jason.module_my.ui.activity.SettingActivity
import com.jason.module_my.ui.activity.TodayInHistoryActivity
import com.jason.tools.base.BaseFragment
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import com.jason.tools.config.RouterConfig
import com.jason.tools.utils.PlatformUtil
import com.orhanobut.logger.Logger

//@Route(path = RouterConfig.FRAGMENT_MY_PATH)
class BackFragment : BaseFragment<BasePresenter<IBaseView>>(), View.OnClickListener {

    private val titles = arrayOf("历史上的今天", "Item 2", "Item 3", "Item 4", "设置", "Item 6")
    private lateinit var img_header:ImageView
    private lateinit var tv_constellation_matching:TextView
    private lateinit var tv_chinese_zodiac_matching:TextView
    private lateinit var layout_function:LinearLayout

    override fun createPresenter(): BasePresenter<IBaseView>? = null

    override fun getLayoutId(): Int = R.layout.fragment_my

    override fun initViews(view: View?) {
        img_header = view!!.findViewById(R.id.img_header)
        tv_constellation_matching = view.findViewById(R.id.tv_constellation_matching)
        tv_chinese_zodiac_matching = view.findViewById(R.id.tv_chinese_zodiac_matching)
        layout_function = view.findViewById(R.id.layout_function)
        img_header.setOnClickListener(this)
        tv_constellation_matching.setOnClickListener(this)
        tv_chinese_zodiac_matching.setOnClickListener(this)
        buildList()
    }

    override fun requestData() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_header -> Logger.e("11")
            R.id.tv_constellation_matching -> openPairingActivity("星座配对", true)
            R.id.tv_chinese_zodiac_matching -> openPairingActivity("生肖配对", false)
        }
    }


    private fun buildList() {
        for (i in titles.indices) {
            val index = i
            val itemView = View.inflate(activity, R.layout.item_menu_function, null)
            val item_layout = itemView.findViewById<LinearLayout>(R.id.item_layout)
            val img_menu_icon = itemView.findViewById<ImageView>(R.id.img_menu_icon)
            val item_tv_title = itemView.findViewById<TextView>(R.id.item_tv_title)
            val item_space = itemView.findViewById<View>(R.id.item_space)
            item_tv_title.text = titles[i]
            if (PlatformUtil.isLollipop()) item_layout.setBackgroundResource(R.drawable.ripple_item_my)
            if (index % 3 == 0) item_space.visibility = View.VISIBLE
            when (index) {
                0 -> img_menu_icon.setImageResource(R.drawable.my_icon_history)
                1 -> img_menu_icon.setImageResource(R.drawable.my_icon_tool)
                2 -> img_menu_icon.setImageResource(R.drawable.my_icon_share)
                3 -> img_menu_icon.setImageResource(R.drawable.my_icon_other)
                4 -> img_menu_icon.setImageResource(R.drawable.my_icon_setting)
                5 -> img_menu_icon.setImageResource(R.drawable.my_icon_about_us)
            }
            item_layout.setOnClickListener {
                when (i) {
                    0 -> openActivityByNoParams(TodayInHistoryActivity::class.java)
                    4 -> openActivityByNoParams(SettingActivity::class.java)
                    else -> {
                        Logger.e("暂未开发")
                    }
                }
            }
            layout_function.addView(itemView)
        }
    }


    private fun openPairingActivity(title: String, flag: Boolean) {
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putBoolean("flag", flag)
        openActivityByParams(PairingActivity::class.java, bundle)
    }

}