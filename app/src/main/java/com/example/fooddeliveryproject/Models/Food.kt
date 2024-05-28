package com.example.fooddeliveryproject.Models

data class Food(
    val id: Int,
    var name: String,
    var description: String,
    val image: String,
    var price: Int,
    var category:String,
    val soldCount:Int?=null
)
