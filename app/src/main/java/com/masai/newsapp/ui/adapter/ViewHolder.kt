package com.masai.newsapp.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.newsapp.model.local.NewsEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View, val onClick: OnClick) : RecyclerView.ViewHolder(itemView) {
    fun setData(result: NewsEntity) {

        itemView.apply {
            crCardView1.setOnClickListener {
                onClick.setOnClick(result)
            }
sorce.setText(result.date)
            tvTitle.setText(result.Name)
            Glide.with(ivImageView).load(result.ImageUrl)
                .into(ivImageView)
        }

    }
}