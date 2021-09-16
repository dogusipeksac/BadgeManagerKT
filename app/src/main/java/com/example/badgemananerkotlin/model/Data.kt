package com.example.badgemananerkotlin.model

import java.io.Serializable

class Data:Serializable{


    private var _created_date:String=""
    fun getCreatedDate():String{
        return _created_date
    }
    fun setCreateDate(value: String){
        _created_date=value
    }

    private var _badgeData:BadgeData=BadgeData()
    fun getBadgeData():BadgeData{
        return _badgeData
    }
    fun setBadgeData(value: BadgeData){
        _badgeData=value
    }




    private var _praiseCount:Int=0
    fun getPraiseCount():Int{
        return _praiseCount
    }
    fun setPraiseCount(value: Int ){
        _praiseCount=value

    }

    private var _praiseRating:Int=0
    fun getPraiseRating():Int{
        return _praiseRating
    }
    fun setPraiseRating(value: Int){
        _praiseRating=value
    }


    private var _author:Author= Author()
    fun getAuthor():Author{
        return  _author
    }
    fun setAuthor(value: Author){
        _author=value
    }


    private var _id:String=""
    fun getId():String{
        return  _id
    }
    fun setId(value: String){
        _id=value
    }


    private var _related_person:RelatedPerson= RelatedPerson()
    fun getRelated_person(): RelatedPerson {
        return _related_person
    }

    fun setRelated_person(value: RelatedPerson) {
        _related_person= value
    }


    private var _message = ""
    fun getMessage(): String {
        return _message
    }

    fun setMessage(value: String) {
        _message= value
    }
}