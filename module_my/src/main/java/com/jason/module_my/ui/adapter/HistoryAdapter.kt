package com.jason.module_my.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jason.module_my.R
import com.jason.module_my.model.bean.HistoryContent

class HistoryAdapter(layoutId:Int = R.layout.item_history):BaseQuickAdapter<HistoryContent,BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder?, item: HistoryContent) {
        helper?.run {
            setText(R.id.item_tv_title,item.title)
                    .setText(R.id.item_tv_content,item.date)
        }
    }
}