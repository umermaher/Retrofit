package com.example.workwithretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workwithretrofit.models.Todo
import kotlinx.coroutines.launch
import retrofit2.Response

class TodoViewModel : ViewModel(){
    private var _todos = MutableLiveData<Resource<List<Todo>?>>()
    val todos=_todos
    private val todoRepository:TodoRepository= TodoRepository()
    var todoResponse = MutableLiveData<Resource<Todo>>()

    init {
        getTodos()
    }

    private fun getTodos() = viewModelScope.launch {
        _todos.postValue(Resource.Loading())
        val response = todoRepository.getTodos()
        _todos.postValue(handleGetTodos(response))
    }

    private fun handleGetTodos(response: Response<List<Todo>>?): Resource<List<Todo>?> {
        response?.let {
            if(response.isSuccessful){
                return Resource.Success(it.body())
            }
        }
        return Resource.Error(response?.message())
    }

    fun getTodo(todoNumber:Int) = viewModelScope.launch {
        todoResponse.value=Resource.Loading()
        val response=todoRepository.getTodo(todoNumber)
        todoResponse.value=handleGetTodo(response)
    }

    private fun handleGetTodo(response: Response<Todo>): Resource<Todo>? {
        return if(response.isSuccessful)
            Resource.Success(response.body()!!)
        else
            Resource.Error(response.message())
    }

}