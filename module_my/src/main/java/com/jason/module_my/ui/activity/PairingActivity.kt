package com.jason.module_my.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.jason.module_my.R
import com.jason.module_my.model.bean.ChinesePairingBean
import com.jason.module_my.model.bean.PairingBean
import com.jason.module_my.viewmodel.ChinesePairingViewModel
import com.jason.module_my.viewmodel.PairingViewModel
import com.jason.tools.base.BaseActivity
import com.jason.tools.base.BasePresenter
import com.jason.tools.base.IBaseView
import kotlinx.android.synthetic.main.activity_pairing.*

/**
 * Created by jason on 2018/12/24.
 */
class PairingActivity : BaseActivity<BasePresenter<IBaseView>>(), View.OnClickListener {

    private var flag = false
    private val constellation = arrayOf("白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔蝎座", "水瓶座", "双鱼座")
    private val chinese_zodiac = arrayOf("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪")
    private lateinit var viewModel: PairingViewModel
    private lateinit var cViewModel: ChinesePairingViewModel

    override fun createPresenter(): BasePresenter<IBaseView>? = null


    override fun getLayoutId(): Int = R.layout.activity_pairing

    override fun initViews() {
        val title = intent.getStringExtra("title")
        flag = intent.getBooleanExtra("flag", false)
        setToolBarByBack(toolBar as Toolbar, title, null)
        btn_submit.setOnClickListener(this)
        tv_options_one.setOnClickListener(this)
        tv_options_two.setOnClickListener(this)
        when {
            flag -> {
                tv_options_one.text = "请选择星座"
                tv_options_two.text = "请选择星座"
            }
            !flag -> {
                tv_options_one.text = "请选择生肖"
                tv_options_two.text = "请选择生肖"
            }
        }
    }

    override fun requestData() {
        if (flag) {
            viewModel = ViewModelProviders.of(this).get(PairingViewModel::class.java)
            viewModel.getData().observe(this, Observer<PairingBean> { data ->
                run {
                    if (data == null) {
                        tv_pairing_title.visibility = View.GONE
                        tv_pairing_content1.visibility = View.GONE
                        tv_pairing_content2.visibility = View.GONE
                        showLoading()
                    } else {
                        hideLoading()
                        tv_pairing_title.visibility = View.VISIBLE
                        tv_pairing_content1.visibility = View.VISIBLE
                        tv_pairing_content2.visibility = View.VISIBLE
                        tv_pairing_title.text = data.title
                        tv_pairing_content1.text = data.content1
                        tv_pairing_content2.text = data.content2
                    }
                }
            })
        } else {
            cViewModel = ViewModelProviders.of(this).get(ChinesePairingViewModel::class.java)
            cViewModel.getData().observe(this, Observer<ChinesePairingBean> { data ->
                run {
                    if (data == null) {
                        tv_pairing_title.visibility = View.GONE
                        tv_pairing_content1.visibility = View.GONE
                        tv_pairing_content2.visibility = View.GONE
                        showLoading()
                    } else {
                        hideLoading()
                        tv_pairing_title.visibility = View.VISIBLE
                        tv_pairing_content1.visibility = View.VISIBLE
                        tv_pairing_content2.visibility = View.VISIBLE
                        tv_pairing_title.text = data.title
                        tv_pairing_content1.text = data.content1
                        tv_pairing_content2.text = data.content2
                    }
                }
            })
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_submit -> if (flag) viewModel.requestData(tv_options_one.text.toString(), tv_options_two.text.toString()) else cViewModel.requestData(tv_options_one.text.toString(), tv_options_two.text.toString())
            R.id.tv_options_one -> if (flag) showPicker(constellation, tv_options_one) else showPicker(chinese_zodiac, tv_options_one)
            R.id.tv_options_two -> if (flag) showPicker(constellation, tv_options_two) else showPicker(chinese_zodiac, tv_options_two)
        }
    }


    private fun showPicker(list: Array<String>, view: TextView) {
        val pvOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, _, _, _ ->
            run {
                view.text = list[options1]
            }
        })
                .setContentTextSize(25)
                .setOutSideCancelable(true)
                .build<Any>()
        pvOptions.setPicker(list.asList())
        pvOptions.show()

    }
}