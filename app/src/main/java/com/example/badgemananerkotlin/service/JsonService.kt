package com.example.badgemananerkotlin.service

import android.content.Context
import com.example.badgemananerkotlin.model.Data
import org.json.JSONArray
import org.json.JSONObject
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import com.example.badgemananerkotlin.model.BadgeData
import java.io.IOException
import java.io.InputStream
import kotlin.collections.HashMap


class JsonService private constructor() {

    private var _mapImages=HashMap<Int,Bitmap>()
    fun getImageMap():HashMap<Int,Bitmap>{
        return _mapImages
    }
    fun setImageMap(value: HashMap<Int,Bitmap>){
        _mapImages=value
    }



    private lateinit var _context:Context
    fun getContext():Context{
        return _context
    }
    fun setContext(value:Context){
        _context=value
    }

    companion object{
        var single=JsonService()
        fun getInstance():JsonService{
            if(single==null){
                single= JsonService()
            }
            return single
        }

    }

    lateinit var dataModelList: ArrayList<Data>
    lateinit var badgeDataModelList: ArrayList<BadgeData>
    var sizeGeneral :Int=0
    var ratingAverageGeneral :Float= 0f

    fun loadJSONFromAssets(fileName: String): String {
        return getContext().assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }


    fun bindJSONDataInFacilityList():List<Data> {
        dataModelList = ArrayList<Data>()
        val jsonObject = JSONObject(loadJSONFromAssets("list-data.json")) // Extension Function call here
        val jsonArraylist = jsonObject.getJSONArray("Row")
        for (i in 0 until jsonArraylist.length()) {

            val dataJSONObject = jsonArraylist.getJSONObject(i)
            ///////////////////////related person için
            var relatedPersonTitle:String?=null
            val objectArrayRelated:JSONArray=dataJSONObject
                .getJSONArray("RelatedPerson")
            val objectRelated =
                objectArrayRelated.getJSONObject(0)
            relatedPersonTitle=objectRelated.getString("title")
            //////////////////////////////////////////////////////////////

            ///////////////badgeData için
            var badgelookupValue :String?=null
            var idForBadge:Int?=null
            val objectArrayBadge: JSONArray = dataJSONObject.getJSONArray("Badge")
            val objectBadge = objectArrayBadge.getJSONObject(0)
            badgelookupValue=objectBadge.getString("lookupValue")
            idForBadge=objectBadge.getInt("lookupId")
            //////////////////////////////////////////////////////////////////

            //bu kullanılmıyor şu an
            var objectArrayAuthor: JSONArray =dataJSONObject.getJSONArray("Author")
            //////////////////////////////////////////

            var cratedDate:String?=null
            cratedDate = dataJSONObject.getString("Created.")
            cratedDate = converterDate(cratedDate)
            var message :String?= null
            message =dataJSONObject.getString("Message")

            var ratingScrore:Int?=null
            ratingScrore = dataJSONObject.getInt("PraiseRating")
            val dataModel = Data()

            dataModel.getRelated_person().setRelatedPersonTitle(relatedPersonTitle)
            if (cratedDate != null) {
                dataModel.setCreateDate(cratedDate)
            }
            dataModel.setMessage(message)
            dataModel.getBadgeData().setBadgeTitle(badgelookupValue)
            dataModel.setPraiseRating(ratingScrore)
            dataModel.getBadgeData().setBadgeId(idForBadge)
            dataModelList.add(dataModel)
        }

        return dataModelList
    }
    fun bindJSONBadgeDataInFacilityList():List<BadgeData>
    {
        badgeDataModelList = ArrayList<BadgeData>()

        val jsonObject = JSONObject(loadJSONFromAssets("badge-data.json")) // Extension Function call here
        val jsonArraylist = jsonObject.getJSONArray("value")
        for (i in 0 until jsonArraylist.length()){

            val objectBadgeData: JSONObject = jsonArraylist.getJSONObject(i)
            var title:String?=null
            title=objectBadgeData.getString("Title")
            var id :Int?= 0
            id = objectBadgeData.getInt("Id")
            if(calculateSize(id)!=0){
                var badgeData=BadgeData()
                addImages(id)
                badgeData.setBadgeId(id)
                badgeData.setBadgeTitle(title)
                badgeDataModelList.add(badgeData)

            }

        }



       return badgeDataModelList
    }


    //istenilen zamana çeviriyoruz
    fun converterDate(newDate: String): String? {
        //2021-08-06T20:26:56Z
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'")
        return try {
            val date: Date = format.parse(newDate)
            val formatter = SimpleDateFormat(
                "dd MMMM HH:mm",
                Locale("tr")
            )
            formatter.format(date)
            formatter.format(date)
        } catch (pe: ParseException) {
            null
        }
    }

    //hangi rozetten kaç tane var onu bulan fonksiyon
    //id sine göre sayıyorum
    fun calculateSize(id: Int): Int {
        var size = 0
        for (i in 0 until dataModelList.size) {
            if (dataModelList.get(i).getBadgeData().getBadgeId()== id) {
                size++
            }
        }
        return size
    }
    //resimleri yüklemek için keye göre bitmap atıyorum
    private fun addImages(key: Int) {
        var ims: InputStream? = null
        try {
            ims = getContext().getAssets().open("resource/image$key.png")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //  Drawable dan image çekmek
        val bitmap = BitmapFactory.decodeStream(ims)
        assert(ims != null)
        ims?.close()
        getImageMap()[key] = bitmap

    }
}