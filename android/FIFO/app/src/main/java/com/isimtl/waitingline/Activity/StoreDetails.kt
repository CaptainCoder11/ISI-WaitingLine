package com.isimtl.waitingline.Activity

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import java.util.ArrayList
import com.isimtl.waitingline.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.result.Result
import com.isimtl.waitingline.Adapter.StoresAdapter
import com.isimtl.waitingline.Api.BASE_URL
import com.isimtl.waitingline.Exensions.*
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
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

        FuelManager.instance.basePath = BASE_URL

        Prefs = PreferenceManager.getDefaultSharedPreferences(this)

        btlogin.font("DidactGothic-Regular.ttf")
        tvtitle.font("DancingScript.ttf")
        tv_stores.font("DidactGothic-Regular.ttf")
        etsearch.font("DidactGothic-Regular.ttf")

        backgroundscope.launch {
            val (request, response, result) = Fuel.get( "store").responseObject(Store.Deserializer())
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

        if(!Prefs?.getString("storeid","").isNullOrEmpty())
        {
            lystoredetails.show()
            qrcode.hide()
        }

        
        etsearch.doOnTextChanged { text, start, before, count ->
            text?.log()
           var list =  stores.filter {
               it -> it.name.lowercase().contains(text.toString().lowercase())
            } as ArrayList
            initstores(list)
        }


    }

    override fun onResume() {
        super.onResume()
        if(Prefs!=null)
        {
            var login = Prefs?.getBoolean("islogin",false)
            if(login!!)  btlogin.visibility = View.GONE
        }
    }

    fun initstores(list:ArrayList<Store>){
        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = StoresAdapter(this,list)
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