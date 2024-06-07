package com.example.fooddeliveryproject.Models

data class Restaurant(
    var id:String="",
    var name:String="",
    var imageUrl:String="",
    var foodList: ArrayList<Food> =ArrayList<Food>(),
    var soldFood:ArrayList<Food> =ArrayList<Food>(),
    var commentCount:Int=0,
    var starRate:Double=0.0,

    )
