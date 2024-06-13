package com.example.fooddeliveryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.Models.User
import com.example.fooddeliveryproject.View.Pages.OrderStatus
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class UserViewModel():ViewModel() {
    private val db= Firebase.firestore
    private var uuid :String= ""
    private val _user=MutableLiveData<User>()
    val user:LiveData<User> get() = _user
    private val _preparedOrderList =MutableLiveData<List<OrderedFood>>()
    val preparedOrderList  :LiveData<List<OrderedFood>> get() = _preparedOrderList
    fun getUserInfo(callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).get()
                .addOnSuccessListener {
                _user.value=it.toObject(User::class.java)
                callBack(true)
                }
                .addOnFailureListener{
                callBack(false)
            }
        }
    }

    fun addFavoriteFood(food: Food,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("FavoriteFood")
                .document(food.id).set(food)
                .addOnSuccessListener {
                    callBack(true)
                }.addOnFailureListener{
                    callBack(false)
                }
        }
    }
    fun removeFavoriteFood(food: Food,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("FavoriteFood")
                .document(food.id).delete().addOnSuccessListener {
                    callBack(true)
                }.addOnFailureListener{
                    callBack(false)
                }
        }
    }
    fun addToCart(food: Food,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Cart")
                .document(food.id).set(food)
                .addOnSuccessListener {
                    callBack(true)
                }.addOnFailureListener{
                    callBack(false)
                }
        }
    }
    fun removeFromCart(food: Food,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Cart")
                .document(food.id).delete()
                .addOnSuccessListener {
                    callBack(true)
                }.addOnFailureListener{
                    callBack(false)
                }
        }
    }
    fun addToOrder(foodList:ArrayList<OrderedFood>,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            for (food in foodList){
                db.collection("Users").document(uuid).collection("Order")
                    .document(food.id).set(food)
                    .addOnSuccessListener {
                        callBack(true)
                    }.addOnFailureListener {
                        callBack(false)
                    }
            }
        }
    }


    fun getPreparedOrderList(){
        viewModelScope.launch {
            db.collection("User").document(uuid).collection("Order")
                .whereEqualTo("isDelivered",false).get().addOnSuccessListener {

                }
        }
    }


}