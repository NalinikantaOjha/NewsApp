package com.masai.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.masai.newsapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.news_details.*

class DetailsActivity : AppCompatActivity() {
    var title:String=""
    var desc:String=""
    var date:String=""
    var sorce:String=""
    var url:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_details)
        val intent=intent
        title= intent.getStringExtra("name").toString()
       desc= intent.getStringExtra("desc").toString()
        date= intent.getStringExtra("date").toString()
        sorce= intent.getStringExtra("sorce").toString()
        url=intent.getStringExtra("url").toString()
tvTitle.setText(title)
        tvSorce.setText(sorce)
        tvDate.setText(date)
        tvdesc.setText(desc)

        Log.d("nalini",url)
        Glide.with(ivImageView).load(url)
            .into(ivImageView)

    }
}