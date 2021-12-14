package com.isimtl.waitingline.Exensions

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.isimtl.waitingline.EventBus.MessageEvent
import org.greenrobot.eventbus.EventBus

var Prefs: SharedPreferences?= null
// add entry in shared preference
fun SharedPreferences.putAny(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name,any).apply()

        // also accepts Float, Long & StringSet
    }
}

// remove entry from shared preference
fun SharedPreferences.remove(name:String){
    edit().remove(name).apply()
}

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

fun Context.toast(str: String)
{
    Toast.makeText(this,str,Toast.LENGTH_LONG).show()
}

fun View.font(font: String) {
    when(this)
    {
      is Button ->{
          typeface = Typeface.createFromAsset(context.assets, "fonts/$font")
      }
      is TextView ->{
          typeface = Typeface.createFromAsset(context.assets, "fonts/$font")
      }
        is EditText ->{
            typeface = Typeface.createFromAsset(context.assets, "fonts/$font")
        }
    }

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