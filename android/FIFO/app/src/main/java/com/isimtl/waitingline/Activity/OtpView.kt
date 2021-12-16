package com.isimtl.waitingline.Activity

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import com.isimtl.waitingline.R
import android.widget.FrameLayout
import android.view.Gravity
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.isimtl.waitingline.Api.BASE_URL
import com.isimtl.waitingline.Api.joinline
import com.isimtl.waitingline.Exensions.*
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
import com.isimtl.waitingline.Models.Otp
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.otp_layout.view.*
import kotlinx.coroutines.launch
import org.json.JSONObject

class OtpView(context: Context,storeid:String,uid:Int) : BlurPopupWindow(context) {
    private var uid = uid
    private var storeid = storeid
    override fun createContentView(parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.otp_layout, parent, false)
        view.visibility = INVISIBLE
        view?.otpback?.setOnClickListener {
            (context as Activity).openActivity(Login::class.java)
        }
        view?.btverify?.setOnClickListener {
            view?.otp_view?.otp?.log()
            val paramObject = JSONObject()
            paramObject.put("id",uid)
            paramObject.put("otp",view?.otp_view?.otp)

            backgroundscope.launch {
                val (request, response, result) = Fuel.post(BASE_URL + "customer/verify")
                    .header("Content-Type" to "application/json").body(paramObject.toString())
                    .responseObject(Otp.Deserializer())
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        ex.message?.log()
                        ex.log()
                        "Wrong OTP".log()
                        mainscope.launch { context.toast("Wrong OTP!") }
                    }
                    is Result.Success -> {
                        var data = result.component1()
                        if (data?.id != null) {
                            "Correct!".log()
                            mainscope.launch { context.toast("Correct!") }
                            Prefs?.putAny("islogin",true)
                            Prefs?.putAny("userid",data.id)

                            if(!storeid.isNullOrEmpty())
                            {
                                joinline(storeid = storeid.toInt(),userid = data.id)
                            }


                            (context as Activity).finish()
                        }
                    }
                }

            }
        }
        return view
    }

    override fun onShow() {
        super.onShow()
        contentView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeGlobalOnLayoutListener(this)
                contentView.visibility = VISIBLE
                val height = contentView.measuredHeight
                ObjectAnimator.ofFloat(contentView, "translationY", height.toFloat(), 0f)
                    .setDuration(
                        animationDuration
                    ).start()
            }
        })
    }

    override fun createShowAnimator(): ObjectAnimator? {
        return null
    }

    override fun createDismissAnimator(): ObjectAnimator {

        val height = contentView.measuredHeight
        return ObjectAnimator.ofFloat(contentView, "translationY", 0f, height.toFloat())
            .setDuration(
                animationDuration
            )
    }

    class Builder(context: Context?,storeid:String,id:Int) : BlurPopupWindow.Builder<OtpView>(context) {
        private var id = id
        private var storeid = storeid

        override fun createPopupWindow(): OtpView {
            return OtpView(mContext, storeid , id)
        }

        init {
            this.setDismissOnTouchBackground(false)
            setScaleRatio(0.25f).setBlurRadius(8f).setTintColor(0x30000000)
        }
    }
}