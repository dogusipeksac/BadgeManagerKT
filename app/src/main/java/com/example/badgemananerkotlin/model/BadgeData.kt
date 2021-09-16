package com.example.badgemananerkotlin.model

import java.io.Serializable

class BadgeData:Serializable{
    private var _badgeId:Int=0
    fun getBadgeId():Int{
        return _badgeId
    }
    fun setBadgeId(value:Int){
        _badgeId=value
    }

    private var _howmany:Int=0
    fun getHowmany():Int{
        return _howmany
    }
    fun setHowmany(value:Int){
        _howmany=value
    }


    private var _average:Float=0.0f
    fun getAverage():Float{
        return _average
    }
    fun setAverage(value: Float){
        _average=value
    }


    private var _badgeTitle:String=""
    fun getBadgeTitle():String{
        return _badgeTitle
    }
    fun setBadgeTitle(value: String){
        _badgeTitle=value
    }
}