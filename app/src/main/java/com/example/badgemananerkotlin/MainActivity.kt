package com.example.badgemananerkotlin

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import com.example.badgemananerkotlin.adapter.ListAdapter
import com.example.badgemananerkotlin.adapter.SpinnerAdapter

import com.example.badgemananerkotlin.model.Data
import com.example.badgemananerkotlin.service.JsonService
import com.example.badgemananerkotlin.viewmodel.MainActivityViewModel


import android.arch.lifecycle.ViewModelProviders

import com.example.badgemananerkotlin.adapter.PagerAdapter
import com.example.badgemananerkotlin.model.BadgeData
import android.support.v4.view.ViewPager

import com.viewpagerindicator.PageIndicator





class MainActivity(): AppCompatActivity() {

    private lateinit var numberText:TextView
    private lateinit var averageText:TextView
    private lateinit var  ratingBar:RatingBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner:Spinner
    private lateinit var mainActivityViewModel:MainActivityViewModel
    private lateinit var spinnerAdapter:SpinnerAdapter
    private lateinit var adapter:ListAdapter
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var mIndicator: PageIndicator
    private lateinit var awesomePager: ViewPager


    private var first = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        spinnerGetList()
        cardSlider()
        forDataList()
        arrayWithFourGroup()

        


    }
    private fun arrayWithFourGroup() {
        mainActivityViewModel.setDataBadgeList()
        mainActivityViewModel.getBadgeDataListObserve().observe(this@MainActivity,
            {
                pagerAdapter= PagerAdapter(supportFragmentManager,
                    mainActivityViewModel.getFourBadgeList()!!)
                awesomePager.adapter=pagerAdapter
                mIndicator.setViewPager(awesomePager)
            })

    }
    private fun spinnerGetList() {
        mainActivityViewModel.setBadgeAddedDataListForSpinner()
        mainActivityViewModel.getBadgeDataListForSpinnerObserve()
            .observe(this@MainActivity,
                { badgeDataList ->
                    spinnerAdapter = SpinnerAdapter(this@MainActivity, badgeDataList!!)
                    spinner.adapter = spinnerAdapter
                    spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            var itemSelectedTitle:BadgeData=parent?.getItemAtPosition(position) as BadgeData
                            mainActivityViewModel.setDataComingBadgeTitleList(itemSelectedTitle.getBadgeTitle())
                            mainActivityViewModel.getDataComingBadgeTitleObserve().observe(this@MainActivity,
                                { data -> setAdapterBottomList(data) })

                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                    }
                })
    }

    //alttaki liste için
    //item selected için iki kere girmesi engelleniyor
    fun setAdapterBottomList(listData: List<Data?>?) {
        if (first) {
            first = false
        } else {
            adapter.setDataList(listData as List<Data>)
        }
    }

    private fun forDataList() {
        mainActivityViewModel.getDataListObserve()
            .observe(this@MainActivity,
                { dataList ->
                    recyclerView.layoutManager= LinearLayoutManager(this@MainActivity,
                        LinearLayoutManager.VERTICAL, false)
                    adapter=ListAdapter()
                    if (dataList != null) {
                        adapter.setDataList(dataList)
                    }
                    recyclerView.adapter=adapter
                    recyclerView.setHasFixedSize(true)
                })
    }

    private fun cardSlider() {
        val number=mainActivityViewModel.getDataSize()
        numberText.text="$number adet"
        val rating:Float= mainActivityViewModel.getDataListAverage()
        val strDouble = String.format("%.1f", rating)
        averageText.text=strDouble
        ratingBar.rating=rating
    }



    fun init(){
        numberText=findViewById(R.id.number)
        ratingBar=findViewById(R.id.ratingBarMain)
        averageText=findViewById(R.id.average_text)
        recyclerView=findViewById(R.id.list_data_recycler)
        spinner=findViewById(R.id.spinner)
        awesomePager=findViewById(R.id.view_pager)
        mIndicator=findViewById(R.id.pagerIndicator)
        mainActivityViewModel = ViewModelProviders.of(this)
            .get(MainActivityViewModel::class.java)
        mainActivityViewModel.init(this)
        mainActivityViewModel.setDataList()



    }



}