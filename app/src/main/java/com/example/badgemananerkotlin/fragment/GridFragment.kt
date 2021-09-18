package com.example.badgemananerkotlin.fragment

import android.support.v4.app.Fragment
import com.example.badgemananerkotlin.model.BadgeData
import com.example.badgemananerkotlin.adapter.BadgeGridAdapter
import android.os.Bundle
import android.widget.GridView
import android.app.Activity
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


@SuppressLint("ValidFragment")
class GridFragment constructor(gridItems: Array<BadgeData>, activity: Activity) : Fragment() {
    private lateinit var mBadgeGridAdapter: BadgeGridAdapter
    private lateinit var gridView: GridView
    var gridItems = arrayOf<BadgeData>()
    private var activity: Activity = activity


    init {
        this.gridItems=gridItems
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //burada grid oluşturuluyor
        val view: View
        view = inflater.inflate(com.example.badgemananerkotlin.R.layout.grid_view,
            container,
            false)
        gridView = view.findViewById<View>(com.example.badgemananerkotlin.R.id.gridView) as GridView
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBadgeGridAdapter=BadgeGridAdapter(activity)
        mBadgeGridAdapter.setItems(gridItems)
        gridView.adapter=mBadgeGridAdapter


    }




}