package com.masai.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masai.newsapp.model.local.NewsEntity
import com.masai.newsapp.model.remote.Response
import com.masai.newsapp.repository.DataRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun CreateTransation() {
      //  Log.d("nalini", int.toString())
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getData()
        }
    }

    val user: LiveData<Response>
        get() = dataRepository.user
    fun insertDataInDb(itunesDbTable: NewsEntity) {
        dataRepository.insertDataInDb(itunesDbTable)
    }

    fun deleteDataFromDb() {
        dataRepository.deleteDataFromDb()
    }

    fun getDataFromDb() {
        dataRepository.getDataFromDb()
    }
    fun getData():LiveData<List<NewsEntity>>{
        return dataRepository.getDataFromDb()
    }
}
