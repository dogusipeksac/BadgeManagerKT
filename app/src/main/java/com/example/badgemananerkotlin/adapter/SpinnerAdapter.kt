package com.example.badgemananerkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.badgemananerkotlin.R
import com.example.badgemananerkotlin.model.BadgeData
import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream


class SpinnerAdapter(context:Context,badgeList:List<BadgeData>)
    :ArrayAdapter<BadgeData>(context,0,badgeList){


    override fun getView(position: Int, convertView: View?,
                         parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }
    private fun initView(position: Int, convertView: View?, parent: ViewGroup) :View{
        var badge=getItem(position) as BadgeData
        val view =convertView?: LayoutInflater.from(context).inflate(
            R.layout.spinner_item,parent,false)
            var imageViewBadge: ImageView =view.findViewById(R.id.image_spinner)
            var textSpinnerTitle: TextView =view.findViewById(R.id.text_spinner_title)
            if (badge != null) {
            //burada id ye göre resimleri atıyorum
            var ims: InputStream? = null
            try {
                ims = context.assets.open("resource/image" + badge.getBadgeId().toString() + ".png")
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //  Drawable dan image çekmek
            val d = Drawable.createFromStream(ims, null)
            imageViewBadge.setImageDrawable(d)
            textSpinnerTitle.text = badge.getBadgeTitle()
            }

        return view
    }

}