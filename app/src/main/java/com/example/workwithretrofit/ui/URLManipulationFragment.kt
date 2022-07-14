package com.example.workwithretrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workwithretrofit.R
import com.example.workwithretrofit.Resource
import com.example.workwithretrofit.TodoViewModel
import com.example.workwithretrofit.databinding.FragmentUrlManupilationBinding
import kotlinx.android.synthetic.main.fragment_url_manupilation.*

class URLManipulationFragment : Fragment() {
    private var _binding:FragmentUrlManupilationBinding?=null
    private val binding get() = _binding!!
    private val todoViewModel= TodoViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentUrlManupilationBinding.inflate(layoutInflater,container,false)

        binding.getTodoBtn.setOnClickListener {
            if(binding.todoEditText.text.isNotEmpty()){
                todoViewModel.getTodo(binding.todoEditText.text.toString().toInt())

                todoViewModel.todoResponse.observe(viewLifecycleOwner){
                    when(it){
                        is Resource.Loading -> binding.fragmentUrlPb.visibility=View.VISIBLE
                        is Resource.Success -> {
                            binding.fragmentUrlPb.visibility=View.GONE

                            val todo = it.data
                            todo?.let {
                                todoText.text="Id: ${todo.id} \n User Id: ${todo.userId} \n Title: ${todo.title}"
                                binding.fragmentUrlPb.visibility=View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding.fragmentUrlPb.visibility=View.GONE
                        }
                    }
                }
            }
        }

        return binding.root
    }
}
