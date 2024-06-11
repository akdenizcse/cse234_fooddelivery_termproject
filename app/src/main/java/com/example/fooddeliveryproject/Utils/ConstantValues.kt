package com.example.fooddeliveryproject.Utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ConstantValues {
    companion object {
         val _isRestourantApp = MutableLiveData<Boolean>().apply { value = false }
        val isRestourantApp: LiveData<Boolean> get() = _isRestourantApp
    }
}