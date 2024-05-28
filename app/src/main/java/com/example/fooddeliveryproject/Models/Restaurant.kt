package com.example.fooddeliveryproject.Models

data class Restaurant(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val foodList: ArrayList<Food>,
    val soldFood:ArrayList<Food>,
    var commentCount:Int=0,
    var starRate:Double=0.0,

    )
