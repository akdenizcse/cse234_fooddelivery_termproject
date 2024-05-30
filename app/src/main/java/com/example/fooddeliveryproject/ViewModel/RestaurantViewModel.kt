package com.example.fooddeliveryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Restaurant
import kotlinx.coroutines.launch

class RestaurantViewModel ():ViewModel() {
    val _restaurant=MutableLiveData<Restaurant>()
    val restaurant :LiveData<Restaurant> get() = _restaurant
    fun getRestaurantInfo(){
        viewModelScope.launch {

        }
    }
}