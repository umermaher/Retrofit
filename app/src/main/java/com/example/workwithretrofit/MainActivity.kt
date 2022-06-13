package com.example.workwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwithretrofit.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    companion object{
        const val TAG="MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible=true
            val response=try{
                RetrofitInstance.api.getTodos()
            }catch (e:IOException){
                Log.e(TAG,"IOException, you might not have Internet Connection")
                binding.progressbar.isVisible=false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG,"HttpException, you might not have Internet Connection")
                binding.progressbar.isVisible=false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                todoAdapter.todos=response.body()!!
            }else{
                Log.e(TAG,"HttpException, you might not have Internet Connection")
            }
            binding.progressbar.isVisible=false
        }
    }
    private fun setUpRecyclerView() = binding.rvTodos.apply {
        todoAdapter= TodoAdapter()
        layoutManager=LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
        adapter=todoAdapter
    }
}