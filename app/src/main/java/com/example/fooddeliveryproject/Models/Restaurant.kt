package com.example.fooddeliveryproject.Models

data class Restaurant(
    val id:Int,
    val name:String,
    val image:String,
    val foodList: ArrayList<Food>,
    val soldFood:ArrayList<Food>
)
