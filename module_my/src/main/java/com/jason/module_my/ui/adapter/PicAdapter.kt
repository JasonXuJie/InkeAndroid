package com.jason.module_my.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jason.module_my.model.bean.PicUrl
import com.jason.tools.netimage.ImageLoader

class PicAdapter(val list:List<PicUrl>):PagerAdapter() {

    private  var listener:OnPicItemListener? = null
    override fun isViewFromObject(view: View, obj: Any): Boolean = view==obj

    override fun getCount(): Int = list.size

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
         container.removeView(container.rootView)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imgView = ImageView(container.context)
        with(imgView){
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            scaleType = ImageView.ScaleType.FIT_XY
            ImageLoader.loadImage(context,list[position].url,imgView)
            listener?.let {
                setOnClickListener {
                    listener!!.onClick(list[position].url,position)
                }
            }
        }
        container.addView(imgView)
        return imgView
    }


    fun setOnItemClickListener(listener:OnPicItemListener){
        this.listener = listener
    }


    interface OnPicItemListener{

        fun onClick(url:String,position:Int)
    }





}