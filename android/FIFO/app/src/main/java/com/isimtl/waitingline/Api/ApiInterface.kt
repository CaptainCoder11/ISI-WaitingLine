package com.isimtl.waitingline.Api

import com.isimtl.waitingline.Models.Store
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("store")
    fun getStores(): Call<List<Store>>



    companion object{
        var BASE_URL = "http://192.99.108.204:3001/api/"

        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}