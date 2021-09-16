package com.example.badgemananerkotlin.model

import java.io.Serializable

class RelatedPerson:Serializable{

    private var _relatedPersonId:String=""
    fun getRelatedPersonId():String{
        return _relatedPersonId
    }
    fun setRelatedPersonId(value:String){
        _relatedPersonId=value
    }

    private var _relatedPersonTitle:String=""
    fun getRelatedPersonTitle():String{
        return _relatedPersonTitle
    }
    fun setRelatedPersonTitle(value: String){
        _relatedPersonTitle=value
    }
}