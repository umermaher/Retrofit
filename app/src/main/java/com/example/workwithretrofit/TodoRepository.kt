package com.example.workwithretrofit

import android.util.Log
import androidx.core.view.isVisible
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TodoRepository {
    companion object{
        val NIL: Response<List<Todo>>? = null
    }
    suspend fun getTodos(): Response<List<Todo>>? {
        return try{
            RetrofitInstance.api.getTodos()
        }catch (e:IOException){
            Log.e(MainActivity.TAG,"IOException, you might not have Internet Connection")
            return NIL
        }catch (e: HttpException){
            Log.e(MainActivity.TAG,"HttpException, unexpected response!")
            return NIL
        }
    }
}