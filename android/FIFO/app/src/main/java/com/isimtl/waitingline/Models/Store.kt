package com.isimtl.waitingline.Models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Store(var id:String , var name : String , var address : String ,
                 var storeCapacity:Int , var waitingCapacity : Int ,
                 var category: String ,
                 var logo:String,
                 var openingHour : String ,
                 var closingHour:String){
    class Deserializer : ResponseDeserializable<Array<Store>> {
        override fun deserialize(content: String): Array<Store> = (Gson().fromJson(content, Array<Store>::class.java))
    }
}