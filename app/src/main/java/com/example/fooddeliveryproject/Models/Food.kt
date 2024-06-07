package com.example.fooddeliveryproject.Models

data class Food(
    val id: String,
    var name: String,
    var description: String,
    var imageUrl: String,
    var price: Int,
    var category:String,
    val soldCount:Int?=null
)
