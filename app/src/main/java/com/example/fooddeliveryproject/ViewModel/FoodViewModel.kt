package com.example.fooddeliveryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class FoodViewModel():ViewModel() {
    private val db= Firebase.firestore

    private val _foodList=MutableLiveData<ArrayList<Food>>()
    private val foodList : LiveData<ArrayList<Food>> get() = _foodList
    fun getFoodList(callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Food").get().addOnSuccessListener {
                _foodList.value=it.toObjects(Food::class.java) as ArrayList<Food>
            }.addOnSuccessListener {
                callBack(true)
            }.addOnFailureListener{
                callBack(false)
            }
        }
    }

    fun searchFood(foodName:String,callBack:(Boolean)->Unit):List<Food>?{

          return  _foodList.value?.filter {
                      it.name.contains(foodName, ignoreCase = true) ||
                      it.description.contains(foodName, ignoreCase = true) ||
                      it.category.contains(foodName, ignoreCase = true)
          }


    }

}