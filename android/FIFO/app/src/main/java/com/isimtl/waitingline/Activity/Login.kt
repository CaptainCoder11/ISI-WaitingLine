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
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Extensions.MyImageAnalyzer
import com.isimtl.waitingline.R
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow
import com.mukesh.OtpView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import java.security.cert.CertPathValidator

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        homeback.setOnClickListener {
            finish()
        }

        btsubmit.setOnClickListener {
            BlurPopupWindow.Builder<BlurPopupWindow>(it.context)
                .setContentView(R.layout.otp_layout)
                .setGravity(Gravity.CENTER)
                .setScaleRatio(0.2f)
                .setBlurRadius(10f)
                .setTintColor(0x30000000)
                .build()
                .show()
        }

        et_email.font("DidactGothic-Regular.ttf")
        tv_or.font("DancingScript.ttf")
        et_phone.font("DidactGothic-Regular.ttf")
        btsubmit.font("Poppins-Bold.ttf")
    }
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText || v is OtpView) {
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

