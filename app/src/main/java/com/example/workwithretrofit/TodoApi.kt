package com.example.workwithretrofit

import com.example.workwithretrofit.models.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("/todos/{todoNumber}")
    suspend fun getTodo(
        @Path("todoNumber") number:Int
    ):Response<Todo>
}
