package com.example.badgemananerkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.ImageView

import com.example.badgemananerkotlin.model.BadgeData
import com.example.badgemananerkotlin.service.JsonService
import android.widget.TextView

import android.widget.RatingBar
import com.example.badgemananerkotlin.R


class BadgeGridAdapter(private var context: Context):BaseAdapter(){
    private lateinit var items: Array<BadgeData>
    fun getItems():Array<BadgeData>{
        return items
    }
    fun setItems(value:Array<BadgeData>){
        items=value
    }

    private lateinit var mInflater: LayoutInflater
    private lateinit var service: JsonService
    private var number = 0



    class ViewHolder {
        var imageView: ImageView? = null
        var title: TextView? = null
        var ratingBar: RatingBar? = null
        var howManydata: TextView? = null
    }


    override fun getCount(): Int {
        return items.size
        return 0
    }

    override fun getItem(position: Int): Any? {
        if (position>=0 && position < getCount()){
            return items[position]
        }
        return null
    }

    override fun getItemId(position: Int): Long {
        if (items!=null && position>=0 && position < getCount()){
            return items[position].getBadgeId().toLong()
        }
        return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
       var view: View? =convertView
       var viewHolder:ViewHolder
       service = JsonService.getInstance()
       mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
       if(view==null){
          
           view=mInflater.inflate(R.layout.badge_item,parent,false)
           viewHolder = ViewHolder()
           viewHolder.imageView=view.findViewById(R.id.badgesImage)
           viewHolder.title=view.findViewById(R.id.badgeTitle);
           viewHolder.ratingBar=view.findViewById(R.id.ratingBarInBadge);
           viewHolder.howManydata=view.findViewById(R.id.howmanydata);
           view.tag = viewHolder
       }
       else{
           viewHolder=view.tag as ViewHolder
       }
        //Grid içindeki bir itemin bilgilerinin set edilmesi
        var gridItems:BadgeData=items[position]
        number=service.calculateSize(gridItems.getBadgeId())
        viewHolder.title?.text=gridItems.getBadgeTitle()
        viewHolder.imageView?.setImageBitmap(service.getImageMap().get(gridItems.getBadgeId()))
        viewHolder.howManydata?.text= "$number adet"
        viewHolder.ratingBar?.setRating(service.calculateAverage(gridItems.getBadgeId()));
        return view
    }

}