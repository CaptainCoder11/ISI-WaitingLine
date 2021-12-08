package com.isimtl.waitingline.Activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
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
import com.isimtl.waitingline.Api.ApiInterface
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.log
import com.isimtl.waitingline.Exensions.toast
import com.isimtl.waitingline.Extensions.MyImageAnalyzer
import com.isimtl.waitingline.Models.Otp
import com.isimtl.waitingline.R
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.otp_layout.*
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.security.cert.CertPathValidator
import javax.security.auth.callback.Callback

class Login : AppCompatActivity() {
    private var apiInterface = ApiInterface.createDummy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        homeback.setOnClickListener {
            finish()
        }

        btsubmit.setOnClickListener {
            if (et_email.text.isNullOrEmpty() && et_phone.text.isNullOrEmpty()) {
                toast("Please Enter Either Phone or Email")
            }
            else
            {
                val paramObject = JSONObject()
                paramObject.put("email", et_email.text.toString())
                val call = apiInterface.request_otp(paramObject.toString())
                call.enqueue(object : retrofit2.Callback<Otp> {
                    override fun onResponse(call: Call<Otp>, response: Response<Otp>) {
                        response.body()?.log()
                        if (response.body()?.message == "email has been sent") {
                            toast("Email Sent!")
                            OtpView.Builder(this@Login).build().show()
                        } else {
                            toast("Oops! something went wrong!")
                        }
                    }

                    override fun onFailure(call: Call<Otp>, t: Throwable) {
                        toast(t.message.toString())
                    }
                })
            }
        }

        et_email.font("DidactGothic-Regular.ttf")
        tv_or.font("DancingScript.ttf")
        et_phone.font("DidactGothic-Regular.ttf")
        btsubmit.font("Poppins-Bold.ttf")
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

