package com.example.workwithretrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwithretrofit.Resource
import com.example.workwithretrofit.adapters.TodoAdapter
import com.example.workwithretrofit.TodoRepository
import com.example.workwithretrofit.TodoViewModel
import com.example.workwithretrofit.databinding.FragmentGetBinding

class GetFragment : Fragment() {
    private var _binding : FragmentGetBinding?=null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: TodoAdapter
    private val todoViewModel= TodoViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentGetBinding.inflate(inflater,container,false)

        setUpRecyclerView()

        todoViewModel.todos.observe(requireActivity()){response->
            when(response){
                is Resource.Loading -> binding.progressbar.isVisible=true
                is Resource.Error -> {
                    Toast.makeText(requireContext(),"Response not successful!", Toast.LENGTH_LONG).show()
                    binding.progressbar.isVisible=false
                }
                is Resource.Success ->{
                    todoAdapter.todos= response.data!!
                    binding.progressbar.isVisible=false
                }
            }
        }
        return binding.root
    }
    private fun setUpRecyclerView() = binding.rvTodos.apply {
        todoAdapter= TodoAdapter()
        layoutManager= LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter=todoAdapter
    }
}