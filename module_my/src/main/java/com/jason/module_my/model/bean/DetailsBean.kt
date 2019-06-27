package com.jason.module_my.model.bean

data class DetailsResult(
        val content: String,
        val e_id: String,
        val picNo: String,
        val picUrl: List<PicUrl>,
        val title: String
)

data class PicUrl(
        val id: Int,
        val pic_title: String,
        val url: String
)
