package com.isimtl.waitingline.Activity

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import java.util.ArrayList
import com.isimtl.waitingline.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.isimtl.waitingline.Adapter.ExampleAdapter
import com.isimtl.waitingline.Api.BASE_URL
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.log
import com.isimtl.waitingline.Exensions.openActivity
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
import com.isimtl.waitingline.Models.Otp
import com.isimtl.waitingline.Models.Store
import kotlinx.android.synthetic.main.store_details.*
import kotlinx.coroutines.launch


class StoreDetails : AppCompatActivity() {
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: LayoutManager? = null
    private var stores:ArrayList<Store> = ArrayList<Store>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_details)
        btlogin.font("DidactGothic-Regular.ttf")
        tvtitle.font("DancingScript.ttf")
        tv_stores.font("DidactGothic-Regular.ttf")
        etsearch.font("DidactGothic-Regular.ttf")

        backgroundscope.launch {
            val (request, response, result) = Fuel.get(BASE_URL + "store").responseObject(Store.Deserializer())
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    ex.message?.log()
                    ex.log()
                }
                is Result.Success -> {
                    var data = result.component1()
                    data?.forEach {
                        stores.add(it)
                    }
                  mainscope.launch {  initstores(stores) }
                }
            }
        }

        qrcode.setOnClickListener {
            openActivity(QRScanActivity::class.java)
        }

        btlogin.setOnClickListener {
            openActivity(Login::class.java)
        }

        
        etsearch.doOnTextChanged { text, start, before, count ->
            text?.log()
           var list =  stores.filter {
               it -> it.name.contains(text.toString())
            } as ArrayList
            initstores(list)
        }


    }

    fun initstores(list:ArrayList<Store>){
        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ExampleAdapter(this,list)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setAdapter(mAdapter)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}