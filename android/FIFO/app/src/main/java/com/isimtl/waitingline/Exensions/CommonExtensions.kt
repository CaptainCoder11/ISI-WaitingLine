package com.isimtl.waitingline.Exensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.isimtl.waitingline.EventBus.MessageEvent
import org.greenrobot.eventbus.EventBus

fun Context.register(){
    EventBus.getDefault().register(this)
}

fun Context.unregister(){
    EventBus.getDefault().unregister(this)
}

fun send(msg : MessageEvent){
    EventBus.getDefault().post(msg)
}


fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.INVISIBLE
}

fun Any.log(){
    if(this is String)
    {
        Log.i("App:",this)
    }
    else
    {
        Log.i("App:",this.toString())
    }
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}