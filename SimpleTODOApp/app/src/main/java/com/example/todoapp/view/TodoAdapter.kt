package com.example.todoapp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.TodoData
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(
    var listTodo: List<TodoData>
):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private var selectedItemPosition: Int = 0

    inner class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val date = itemView.findViewById<TextView>(R.id.tv_secText)
        val notes = itemView.findViewById<TextView>(R.id.tv_suppText)
        val checkbox = itemView.findViewById<CheckBox>(R.id.checkbox)
        var cardView = itemView.findViewById<LinearLayout>(R.id.l_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val newList = listTodo[position]
        holder.apply{
            title.text = newList.title
            notes.text = newList.notes
            date.text = newList.date
            checkbox.isChecked = newList.isCompleted
        }
        holder.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                selectedItemPosition = position
                notifyDataSetChanged()
            }
            if(selectedItemPosition == position){
                holder.cardView.setBackgroundColor(Color.parseColor("#81c784"))
            }else{
                holder.cardView.setBackgroundColor(Color.parseColor("#ffddc1"))
            }
        }

    }

    override fun getItemCount(): Int {
        return listTodo.size
    }
}