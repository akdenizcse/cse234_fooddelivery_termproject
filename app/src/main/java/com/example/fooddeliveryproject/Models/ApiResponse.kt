package com.example.fooddeliveryproject.Models

data class ApiResponse(
    var status:String,
    var data : ArrayList<Data>
)

data class Data(
    var id:Int,
    var name:String,
    var district:ArrayList<District>
)

data class District(
    var id:Int,
    var name:String
)