package com.isimtl.waitingline.Activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.isimtl.waitingline.Api.BASE_URL
import com.isimtl.waitingline.Exensions.Prefs
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.log
import com.isimtl.waitingline.Exensions.toast
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
import com.isimtl.waitingline.Models.Otp
import com.isimtl.waitingline.R
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.otp_layout.*
import kotlinx.coroutines.launch
import org.json.JSONObject

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        Prefs = PreferenceManager.getDefaultSharedPreferences(this)

        et_email.font("DidactGothic-Regular.ttf")
        tv_or.font("DancingScript.ttf")
        et_phone.font("DidactGothic-Regular.ttf")
        btsubmit.font("Poppins-Bold.ttf")

        homeback.setOnClickListener {
            finish()
        }

        btsubmit.setOnClickListener {
            if (et_email.text.isNullOrEmpty() && et_phone.text.isNullOrEmpty() && et_name.text.isNullOrEmpty()) {
                toast("Please Enter Name and Phone or Email")
            } else {
                val paramObject = JSONObject()
                paramObject.put("email", et_email.text.toString())
                paramObject.put("phone", et_phone.text.toString())
                paramObject.put("name", et_name.text.toString())
                paramObject.log()
                backgroundscope.launch {
                    val (request, response, result) = Fuel.post(BASE_URL + "customer/login")
                        .header("Content-Type" to "application/json").body(paramObject.toString())
                        .responseObject(Otp.Deserializer())
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            ex.message?.log()
                            ex.log()
                        }
                        is Result.Success -> {
                            var data = result.component1()
                            if (data?.id != null) {
                                mainscope.launch {
                                    OtpView.Builder(this@Login, data.id).build().show()
                                }
                            }
                        }
                    }

                }

            }
        }
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

