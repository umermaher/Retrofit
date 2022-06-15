package com.example.workwithretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workwithretrofit.databinding.FragmentGetBinding

class GetFragment : Fragment() {
    private var _binding : FragmentGetBinding?=null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: TodoAdapter
    private val todoViewModel=TodoViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentGetBinding.inflate(inflater,container,false)

        setUpRecyclerView()

        binding.progressbar.isVisible=true
        todoViewModel.response.observe(requireActivity()){response->
            response?.let {
                if(it.isSuccessful && it.body()!=null) {
                    todoAdapter.todos = it.body()!!
                    binding.progressbar.isVisible=false
                }else{
                    Toast.makeText(requireContext(),"Response not successful!", Toast.LENGTH_LONG).show()
                }
            }

            if(response==TodoRepository.NIL){
                Toast.makeText(requireContext(),"Check your internet!", Toast.LENGTH_LONG).show()
                binding.progressbar.isVisible=false
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