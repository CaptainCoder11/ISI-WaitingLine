package com.isimtl.waitingline.Models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Otp( val id:Int , val name:String, val email:String, val phone:String) {
    class Deserializer : ResponseDeserializable<Otp> {
        override fun deserialize(content: String): Otp = Gson().fromJson(content, Otp::class.java)
    }

}
