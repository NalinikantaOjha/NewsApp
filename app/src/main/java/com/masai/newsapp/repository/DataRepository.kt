package com.masai.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masai.newsapp.model.local.Dao
import com.masai.newsapp.model.local.NewsEntity
import com.masai.newsapp.model.remote.Response
import com.masai.newsapp.model.remote.api.ApiService

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository (private val userApi: ApiService, private val dao: Dao) {
    private val userLiveData = MutableLiveData<Response>()

    val user: LiveData<Response>
        get() = userLiveData

    suspend fun getData() {

        val result = userApi.getData("india","b5becfb5a3d34c7991cb1af2c22c0d57")
        if (result?.body() != null) {
            userLiveData.postValue(result.body())
            Log.d("getdata", "response")
        }
    }
    fun insertDataInDb(newsTable: NewsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllDataFromDB()
            dao.addDataFromAPI(newsTable)
        }
    }



    fun getDataFromDb():LiveData<List<NewsEntity>>{
        return dao.getResponseFromDb()
    }
    fun deleteDataFromDb(){
        dao.deleteAllDataFromDB()
    }

}