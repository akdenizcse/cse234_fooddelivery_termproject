package com.example.fooddeliveryproject.Network

import com.example.fooddeliveryproject.Models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/provinces")
    fun getPorvinceList(): Call<ApiResponse>


}