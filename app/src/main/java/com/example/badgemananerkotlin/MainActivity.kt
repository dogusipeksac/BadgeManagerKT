package com.example.badgemananerkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.badgemananerkotlin.adapter.ListAdapter
import com.example.badgemananerkotlin.service.JsonService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jsonService:JsonService= JsonService.getInstance()
        jsonService.setContext(this)
        jsonService.bindJSONDataInFacilityList()
        jsonService.bindJSONBadgeDataInFacilityList()
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.adapter=ListAdapter(jsonService.bindJSONDataInFacilityList())
        recyclerView?.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.setHasFixedSize(true)

    }
}