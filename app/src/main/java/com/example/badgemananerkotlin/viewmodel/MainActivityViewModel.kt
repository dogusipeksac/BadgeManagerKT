package com.example.badgemananerkotlin.viewmodel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context

import com.example.badgemananerkotlin.model.BadgeData
import com.example.badgemananerkotlin.model.Data
import com.example.badgemananerkotlin.service.JsonService
import android.app.Activity

import com.example.badgemananerkotlin.fragment.GridFragment



class MainActivityViewModel :ViewModel() {
    private lateinit var mDataList: MutableLiveData<List<Data>>
    fun getDataListObserve(): MutableLiveData<List<Data>> {
        return mDataList
    }
    //butun data-listi getirme
    fun setDataList() {
        val dataList: List<Data> = service.bindJSONDataInFacilityList()
        if (dataList.isNotEmpty()) {
            mDataList.setValue(dataList)
        } else {
            mDataList.setValue(null)
        }
    }


    private lateinit var mBadgeDataListForSpinner: MutableLiveData<List<BadgeData>>
    fun getBadgeDataListForSpinnerObserve(): MutableLiveData<List<BadgeData>> {
        return mBadgeDataListForSpinner
    }
    //butun badge-list getirme
    fun setDataBadgeList() {
        val dataList: List<BadgeData> = service.bindJSONBadgeDataInFacilityList()
        if (dataList.isNotEmpty()) {
            mBadgeDataList.setValue(dataList)
        } else {
            mBadgeDataList.setValue(null)
        }
    }


    private lateinit var mBadgeDataList: MutableLiveData<List<BadgeData>>
    fun getBadgeDataListObserve(): MutableLiveData<List<BadgeData>>{
        return mBadgeDataList
    }
    //elimdeki badge-list çekiyorum
    //daha sonra spinner için yeniden
    // modify ediyorum en başına tüm rozetler ekliyorum
    fun setBadgeAddedDataListForSpinner() {
        val dataList = service.bindJSONBadgeDataInFacilityList()
        val newList:ArrayList<BadgeData> =ArrayList()
        val dataForZero = BadgeData()
        dataForZero.setBadgeTitle("Tüm Rozetler")
        dataForZero.setBadgeId(2)
        newList.add(0, dataForZero)
        newList.addAll(dataList!!)
        mBadgeDataListForSpinner.value = newList
    }


    private lateinit var mDataComingBadgeTitle: MutableLiveData<List<Data>>
    fun getDataComingBadgeTitleObserve(): MutableLiveData<List<Data>> {
        return mDataComingBadgeTitle
    }
    //spinner için title ile data-list getirme
    fun setDataComingBadgeTitleList(title: String) {
        if (title.equals("Tüm Rozetler", ignoreCase = true)) {
            val dataList = mDataList.value
            mDataComingBadgeTitle.setValue(dataList)
        } else {
            val newList: List<Data>?
            newList = service.getWithTitleList(title)
            mDataComingBadgeTitle.setValue(newList)
        }
    }

    //4lü sınıflamak için listeyi yeniden düzenliyorum
    fun getFourBadgeList(): List<GridFragment>? {
        val dataList = mBadgeDataList.value
        val it = dataList!!.iterator()
        val gridFragmentList: MutableList<GridFragment> = ArrayList()
        var i = 0
        while (it.hasNext()) {
            val imlst: ArrayList<BadgeData> = ArrayList()
            for (y in 1..4) {
                if (it.hasNext()) {
                    val badgeData = it.next()
                    val itm1 = BadgeData()
                    itm1.setBadgeId(badgeData.getBadgeId())
                    itm1.setBadgeTitle(badgeData.getBadgeTitle())
                    imlst.add(itm1)
                    i += 1
                }
            }
            //burada çekilen verileri diziye atıyoruz
            val gp = arrayOf<BadgeData>()
            val gridPage = imlst.toArray(gp)
            //ve 4 er diziler şeklinde listeye ekliyoruz
            gridFragmentList.add(GridFragment(gridPage, (context as Activity?)!!))
        }
        return gridFragmentList
    }

    private lateinit  var service: JsonService

    private lateinit var context: Context

    //burada jsona bağlanmak için bir context atıyorum ve gerekli
    // değişkenlierimi ve litelerimi açıyorum
    fun init(context: Context){
        service = JsonService.getInstance()
        service.setContext(context)
        this.context=context
        mDataList = MutableLiveData()
        mBadgeDataListForSpinner = MutableLiveData()
        mBadgeDataList = MutableLiveData()
        mDataComingBadgeTitle = MutableLiveData()
    }




    //butun data-list listenin uzunluğu
    fun getDataSize(): Int {
        return service.getSizeGeneral()!!
    }

    //butun liste data-list için average hesaplama
    fun getDataListAverage(): Float {
        return service.getRatingGeneral()!!
    }


}