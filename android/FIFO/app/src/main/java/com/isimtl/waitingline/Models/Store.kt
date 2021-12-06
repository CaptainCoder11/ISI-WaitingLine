package com.isimtl.waitingline.Models

data class Store(var id:String , var name : String , var address : String ,
                 var storeCapacity:Int , var waitingCapacity : Int ,
                 var category: String ,
                 var openingHour : String ,
                 var closingHour:String)