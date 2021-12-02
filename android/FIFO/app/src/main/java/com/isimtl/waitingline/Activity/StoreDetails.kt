package com.isimtl.waitingline.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import android.os.Bundle
import java.util.ArrayList
import com.isimtl.waitingline.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.isimtl.waitingline.Adapter.ExampleAdapter
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.openActivity
import com.isimtl.waitingline.Models.ExampleName
import kotlinx.android.synthetic.main.store_details.*

class StoreDetails : AppCompatActivity() {
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_details)
        btlogin.font("DidactGothic-Regular.ttf")
        tvtitle.font("DancingScript.ttf")
        tv_stores.font("DidactGothic-Regular.ttf")
        etsearch.font("DidactGothic-Regular.ttf")

        qrcode.setOnClickListener {
            openActivity(QRScanActivity::class.java)
        }

        initstores()


    }

    fun initstores(){
        val exampleList = ArrayList<ExampleName>()
        exampleList.add(
            ExampleName(
                R.mipmap.store,
                "Walmart",
                "Open : 10:00 AM",
                "Close : 10:00 PM"
            )
        )
        exampleList.add(ExampleName(R.mipmap.store, "Costco", "Open : 8:00 AM", "Close : 8:00 PM"))
        exampleList.add(
            ExampleName(
                R.mipmap.store,
                "Dollarama",
                "Open : 10:00 AM",
                "Close : 10:00 PM"
            )
        )
        exampleList.add(ExampleName(R.mipmap.store, "Tim", "Open : 8:00 AM", "Close : 8:00 PM"))
        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ExampleAdapter(exampleList)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setAdapter(mAdapter)
    }
}