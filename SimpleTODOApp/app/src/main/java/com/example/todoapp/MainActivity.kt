package com.example.todoapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.todoapp.model.TodoData
import com.example.todoapp.view.TodoAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    var todoList = mutableListOf(
        TodoData("Workout","May 13 2021" ,"30min Workout",false),
        TodoData("Workout","May 13 2021","30min Workout",false),
    )
    val adapter = TodoAdapter(todoList)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv= findViewById<RecyclerView>(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val addBtn = findViewById<Button>(R.id.materialButton)
        addBtn.setOnClickListener{
            /*Toast.makeText(this, "Button Clicked",Toast.LENGTH_SHORT).show()*/
            showDialog()
        }
    }

    fun showDialog(){
        val inflater = LayoutInflater.from(this)
        val itemsView = inflater.inflate(R.layout.custom_dialog, null)

        val title = itemsView.findViewById<EditText>(R.id.et_title)
        val notes = itemsView.findViewById<EditText>(R.id.et_notes)
        val date = getCurrentDate()

        val itemDialog = AlertDialog.Builder(this)
            .setView(itemsView)
            .setNegativeButton("Cancel"){
                    dialog,_->
                dialog.dismiss()
            }
            .setPositiveButton("OK"){
                dialog,_->
                val a_title = title.text.toString()
                val a_notes = notes.text.toString()
                val testData = TodoData(a_title,date,a_notes,false)
                todoList.add(testData)
                adapter.notifyItemInserted(todoList.size-1)
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun getCurrentDate():String{
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MMM d yyyy")
        val date = simpleDateFormat.format(calendar.time)
        return date
    }


}