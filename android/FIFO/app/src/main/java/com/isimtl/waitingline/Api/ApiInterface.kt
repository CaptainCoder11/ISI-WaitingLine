package com.isimtl.waitingline.Api

import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.isimtl.waitingline.Exensions.log
import com.isimtl.waitingline.Exensions.openActivity
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
import com.isimtl.waitingline.Models.Store
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus


var BASE_URL = "http://192.99.108.204:3001/api/"


fun joinline(storeid:Int,userid:Int){
    storeid.log()
    userid.log()
    backgroundscope.launch {
        val (request, response, result) = Fuel.get(  "customer/join-waiting-line/${userid}/${storeid}").response()
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                ex.message?.log()
                ex.log()
            }
            is Result.Success -> {
                mainscope.launch { EventBus.getDefault().post("userjoined")  }
            }
        }
    }
}