package com.example.fooddeliveryproject.Models

data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price: Int,
    val category:String,
    val soldCount:Int?=null
)
