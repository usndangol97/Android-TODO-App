package com.example.todoapp.model

data class TodoData (
    val title :String,
    val date:String,
    val notes:String,
    var isCompleted:Boolean
)