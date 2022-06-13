package com.example.workwithretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import okhttp3.Response

class TodoViewModel : ViewModel(){
    private lateinit var _response:LiveData<List<Todo>>
    val response=_response

    init {

    }
}