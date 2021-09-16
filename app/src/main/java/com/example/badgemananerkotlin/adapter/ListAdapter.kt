package com.example.badgemananerkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.badgemananerkotlin.model.Data
import android.widget.RatingBar

import android.widget.TextView
import com.example.badgemananerkotlin.R
import com.example.badgemananerkotlin.service.JsonService


class ListAdapter (val dataList:List<Data>):
    RecyclerView.Adapter<ListAdapter.MyModelViewHolder>() {


    class MyModelViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var related_name: TextView? = itemView.findViewById(R.id.related_name)
        var created_date: TextView? = itemView.findViewById(R.id.crated_date)
        var badge_title: TextView? = itemView.findViewById(R.id.badget_title)
        var message: TextView? = itemView.findViewById(R.id.message)
        var ratingBar: RatingBar? = itemView.findViewById(R.id.ratingBar)
        //bu manuel
        var profileImage: ImageView? =itemView.findViewById(R.id.profile_image)
        var badges_image: ImageView = itemView.findViewById(R.id.badges_image)


        fun bindItems(data: Data) {
            related_name?.text = data.getRelated_person().getRelatedPersonTitle()
            created_date?.text=data.getCreatedDate()+"de gönderdi"
            badge_title?.text=data.getBadgeData().getBadgeTitle()
            message?.text=data.getMessage()
            ratingBar?.rating=data.getPraiseRating().toFloat()
            badges_image.setImageBitmap(
                JsonService.getInstance().getImageMap()[data.getBadgeData().getBadgeId()]
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyModelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false)

        return  MyModelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListAdapter.MyModelViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}