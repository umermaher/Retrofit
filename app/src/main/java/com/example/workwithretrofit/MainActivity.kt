package com.example.workwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwithretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val todoViewModel=TodoViewModel()
    companion object{
        const val TAG="MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        binding.progressbar.isVisible=true
        todoViewModel.response.observe(this){response->
            response?.let {
                if(it.isSuccessful && it.body()!=null) {
                    todoAdapter.todos = it.body()!!
                    binding.progressbar.isVisible=false
                }
            }

            if(response==TodoRepository.NIL){
                Toast.makeText(this,"Check your internet!",Toast.LENGTH_LONG).show()
                binding.progressbar.isVisible=false
            }
        }
    }
    private fun setUpRecyclerView() = binding.rvTodos.apply {
        todoAdapter= TodoAdapter()
        layoutManager=LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
        adapter=todoAdapter
    }
}