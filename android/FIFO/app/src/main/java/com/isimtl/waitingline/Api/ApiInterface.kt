package com.isimtl.waitingline.Api

import com.isimtl.waitingline.Models.Otp
import com.isimtl.waitingline.Models.Store
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @GET("store")
    fun getStores(): Call<List<Store>>

    @Headers("Content-Type: application/json")
    @POST("request_otp/")
    fun request_otp(@Body value: String): Call<Otp>

    @Headers("Content-Type: application/json")
    @POST("verify_otp/")
    fun verify_otp(@Body value: String): Call<Otp>



    companion object{
        var BASE_URL = "http://192.99.108.204:3001/api/"
        var DUMMY_URL = "http://10.0.2.2:3333/"



        fun createDummy() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DUMMY_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}