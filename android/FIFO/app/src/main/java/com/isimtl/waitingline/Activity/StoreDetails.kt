package com.isimtl.waitingline.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import android.os.Bundle
import java.util.ArrayList
import com.isimtl.waitingline.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.isimtl.waitingline.Adapter.ExampleAdapter
import com.isimtl.waitingline.Api.ApiInterface
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.log
import com.isimtl.waitingline.Exensions.openActivity
import com.isimtl.waitingline.Models.Store
import kotlinx.android.synthetic.main.store_details.*
import retrofit2.Call
import retrofit2.Response

class StoreDetails : AppCompatActivity() {
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: LayoutManager? = null
    private var stores:ArrayList<Store> = ArrayList<Store>()
    private var apiInterface = ApiInterface.create()
    val getstores =  apiInterface.getStores()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_details)
        btlogin.font("DidactGothic-Regular.ttf")
        tvtitle.font("DancingScript.ttf")
        tv_stores.font("DidactGothic-Regular.ttf")
        etsearch.font("DidactGothic-Regular.ttf")
        
        getstores.enqueue( object : retrofit2.Callback<List<Store>> {
            override fun onResponse(call: Call<List<Store>>?, response: Response<List<Store>>?) {
                   var response =  response?.body()!!
                    response.forEach {
                        stores.add(it)
                    }
                initstores()
            }

            override fun onFailure(call: Call<List<Store>>?, t: Throwable?) {
                t.let { it!!.log() }
            }
        })



        qrcode.setOnClickListener {
            openActivity(QRScanActivity::class.java)
        }

        initstores()


    }

    fun initstores(){
        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ExampleAdapter(stores)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setAdapter(mAdapter)
    }
}