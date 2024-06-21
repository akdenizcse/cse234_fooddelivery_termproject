package com.example.fooddeliveryproject.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Food(
    val id: String="",
    var name: String="",
    var description: String="",
    var imageUrl: String="",
    var price: Int=0,
    var category:String="",
    var rating:Double=0.0,
    var ratingCount:Int=0
):Parcelable
