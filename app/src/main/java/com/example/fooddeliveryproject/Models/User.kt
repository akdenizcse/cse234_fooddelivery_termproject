package com.example.fooddeliveryproject.Models

data class User(
    var id:String="",
    var name:String="",
    var surname:String="",
    var favoriteList: ArrayList<Food> = ArrayList(),
    var cartList: ArrayList<Food> = ArrayList(),
    var orderList:ArrayList<Food> = ArrayList(),
    var userAddress:Address = Address(),
)
