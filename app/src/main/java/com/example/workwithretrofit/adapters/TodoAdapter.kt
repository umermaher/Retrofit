package com.example.workwithretrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.workwithretrofit.databinding.ItemTodosBinding
import com.example.workwithretrofit.models.Todo

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodosBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val todo=todos[position]
            tvTitle.text=todo.title
            tvId.text = todo.id.toString()
            checkBox.isChecked=todo.completed
        }
    }

    override fun getItemCount(): Int = todos.size

    inner class ViewHolder(val binding:ItemTodosBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallback=object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) : Boolean=  oldItem.id==newItem.id
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem==newItem
    }

    private val differ=AsyncListDiffer(this,diffCallback)
    var todos:List<Todo>
        get() = differ.currentList
        set(value){differ.submitList(value)}
}