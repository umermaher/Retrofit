package com.example.workwithretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class TodoViewModel : ViewModel(){
    private var _response = MutableLiveData<Response<List<Todo>>?>()
    val response=_response
    private val todoRepository:TodoRepository= TodoRepository()

    init {
        getTodos()
    }

    private fun getTodos() = viewModelScope.launch {
        _response.postValue(todoRepository.getTodos())
    }
}