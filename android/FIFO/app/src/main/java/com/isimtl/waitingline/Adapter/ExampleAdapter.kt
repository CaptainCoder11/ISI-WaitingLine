package com.isimtl.waitingline.Adapter

import java.util.ArrayList
import com.isimtl.waitingline.Models.ExampleName
import androidx.recyclerview.widget.RecyclerView
import com.isimtl.waitingline.Adapter.ExampleAdapter.ExampleViewHolder
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.isimtl.waitingline.Exensions.font
import com.isimtl.waitingline.Models.Store
import com.isimtl.waitingline.R

class ExampleAdapter(private val mExampleList: ArrayList<ExampleName>) : RecyclerView.Adapter<ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleList[position]
        holder.mImageView.setImageResource(R.mipmap.store)
        holder.mTextView1.text = currentItem.name
        holder.mTextView2.text = currentItem.openingHour
        holder.mTextView3.text = currentItem.closingHour
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