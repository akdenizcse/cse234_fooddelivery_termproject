package com.example.fooddeliveryproject.Models

data class ApiResponse(
    var status:String,
    var data : ArrayList<Province>
)

data class Province(
    var id:Int,
    var name:String,
    var districts:ArrayList<District>
)

data class District(
    var id:Int,
    var name:String
)