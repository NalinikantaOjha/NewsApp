package com.masai.newsapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.masai.newsapp.viewmodel.ViewModelFactory
import com.masai.newsapp.R
import com.masai.newsapp.model.local.Dao
import com.masai.newsapp.model.local.NewsEntity
import com.masai.newsapp.model.local.Newsdatabase
import com.masai.newsapp.model.remote.Article
import com.masai.newsapp.model.remote.api.ApiService
import com.masai.newsapp.model.remote.api.Network
import com.masai.newsapp.repository.DataRepository
import com.masai.newsapp.ui.adapter.Adapter
import com.masai.newsapp.ui.adapter.OnClick
import com.masai.newsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.net.NetworkInfo

import android.net.ConnectivityManager




class MainActivity : AppCompatActivity() ,OnClick{
    lateinit var adapter2: Adapter
    lateinit var viewModel2: MainViewModel
    lateinit var repository: DataRepository
    private var List = mutableListOf<Article>()
    private var miusicList= mutableListOf<NewsEntity>()
    lateinit var dao: Dao

    var page: Int = 1
    var manager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)
        dao= Newsdatabase.getNewsDatabase(this).getNews()

        val userApi = Network.getInstance().create(ApiService::class.java)
        repository = DataRepository(userApi,dao)
        val Factory = ViewModelFactory(repository)
        viewModel2 = ViewModelProviders.of(this, Factory).get(MainViewModel::class.java)
       // loadApi()
        if (isNetworkAvailable()){
            loadApi()
        }

        viewModel2.getData().observe(this, Observer {

            miusicList.addAll(it)
            setRecycle()
        })

    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    private fun insertDataToDb(resultModels: List<Article>) {
        viewModel2.deleteDataFromDb()
        resultModels.forEach {
            val newsDb =NewsEntity(it.title,it.urlToImage,it.description,it.publishedAt,it.source.name)
            viewModel2.insertDataInDb(newsDb)
        }
    }
    fun loadApi(){
        viewModel2.CreateTransation()
        viewModel2.user.observe(this, Observer {
            List.clear()
            if (it!=null){
                CoroutineScope(Dispatchers.IO).launch {
                    insertDataToDb(it.articles)
                }
            }

            List.addAll(it.articles as MutableList<Article>)
            // setRecycle()
        })
    }
    fun setRecycle() {
        adapter2 = Adapter(miusicList, this, this)
        recycleAll.adapter = adapter2
        recycleAll.layoutManager = LinearLayoutManager(this)
    }



    override fun setOnClick(result: NewsEntity) {
        val url=result.ImageUrl.toString()
        val intent=Intent(this,DetailsActivity::class.java)
        intent.putExtra("name",result.Name)
        intent.putExtra("desc",result.desc)
        intent.putExtra("date",result.date)
        intent.putExtra("sorce",result.sorce)
        intent.putExtra("url",url)
       startActivity(intent)
    }
}