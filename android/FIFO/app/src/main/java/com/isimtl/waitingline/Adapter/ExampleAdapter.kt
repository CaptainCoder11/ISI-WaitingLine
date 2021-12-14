package com.isimtl.waitingline.Adapter

import android.app.Dialog
import android.content.Context
import android.util.Base64
import android.view.*
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.isimtl.waitingline.Adapter.ExampleAdapter.ExampleViewHolder
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.isimtl.waitingline.Exensions.Prefs
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Exensions.putAny
import com.isimtl.waitingline.Extensions.backgroundscope
import com.isimtl.waitingline.Extensions.mainscope
import com.isimtl.waitingline.Models.Store
import com.isimtl.waitingline.R
import kotlinx.coroutines.launch
import java.util.*

class ExampleAdapter(private val mcontext: Context, private val mExampleList: ArrayList<Store>) : RecyclerView.Adapter<ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleList[position]
       backgroundscope.launch {
           val image = Base64.decode(currentItem.logo, Base64.DEFAULT)
            mainscope.launch {
                Glide.with(mcontext).asBitmap()
                    .load(image)
                    .placeholder(R.mipmap.store)
                    .into(holder.mImageView)
            }
       }
        //holder.mImageView.setImageResource(R.mipmap.store)
        holder.mTextView1.text = currentItem.name
        holder.mTextView2.text = currentItem.openingHour
        holder.mTextView3.text = currentItem.closingHour

       holder.itemView.setOnClickListener {
           var dialog = Dialog(mcontext, android.R.style.Theme_Dialog)
           dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
           dialog.setContentView(R.layout.activity_store_dialog)
           dialog.setCanceledOnTouchOutside(true)
           dialog.setCanceledOnTouchOutside(false);
           dialog.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
           dialog.show()

           Prefs?.putAny("storeid", currentItem.id)


       }

    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImageView: ImageView
        var mTextView1: TextView
        var mTextView2: TextView
        var mTextView3: TextView

        init {
            mImageView = itemView.findViewById(R.id.imageView)
            mTextView1 = itemView.findViewById(R.id.tv_stores)
            mTextView2 = itemView.findViewById(R.id.textView2)
            mTextView3 = itemView.findViewById(R.id.textView3)
            mTextView1.font("Poppins-Bold.ttf")
            mTextView2.font("DidactGothic-Regular.ttf")
            mTextView3.font("DidactGothic-Regular.ttf")
        }
    }
}