package com.isimtl.waitingline.Activity

import android.animation.ObjectAnimator
import android.content.Context
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import com.isimtl.waitingline.R
import android.widget.FrameLayout
import android.view.Gravity
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.core.view.marginBottom
import com.isimtl.waitingline.Activity.OtpView
import com.isimtl.waitingline.Exensions.log
import kotlinx.android.synthetic.main.otp_layout.view.*

class OtpView(context: Context) : BlurPopupWindow(context) {
    override fun createContentView(parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.otp_layout, parent, false)
        view.visibility = INVISIBLE
        view?.btverify?.setOnClickListener {
            view?.otp_view?.otp?.log()
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

    class Builder(context: Context?) : BlurPopupWindow.Builder<OtpView>(context) {
        override fun createPopupWindow(): OtpView {
            return OtpView(mContext)
        }

        init {
            setScaleRatio(0.25f).setBlurRadius(8f).setTintColor(0x30000000)
        }
    }
}