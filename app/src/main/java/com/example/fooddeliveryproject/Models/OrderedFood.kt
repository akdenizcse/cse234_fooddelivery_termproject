package com.example.fooddeliveryproject.Models

import com.example.fooddeliveryproject.View.Pages.OrderStatus

data class OrderedFood(
    val id: String,
    var name: String,
    var description: String,
    var imageUrl: String,
    var price: Int,
    var category:String,
    var soldCount:Int?=null,
    var orderedDate:String?=null,
    var isDelivered: Boolean=false,
    var userId:String ? =null
)
