package com.example.fooddeliveryproject.Models

data class Restaurant(
    var id:String="",
    var name:String="",
    var imageUrl:String="",
    var foodList: ArrayList<String> =ArrayList<String>(),
    var soldFood:ArrayList<OrderedFood> =ArrayList<OrderedFood>(),
    var commentCount:Int=0,
    var starRate:Double=0.0,

    )
