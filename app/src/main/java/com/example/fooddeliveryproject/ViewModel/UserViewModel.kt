package com.example.fooddeliveryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class UserViewModel():ViewModel() {
    private val db= Firebase.firestore
    private var uuid :String= FirebaseAuth.getInstance().currentUser!!.uid
    private val _user=MutableLiveData<User>()
    private val _cartList=MutableLiveData<List<OrderedFood>>()
    val cartList:LiveData<List<OrderedFood>> get() = _cartList
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
    fun addToCart(food: OrderedFood,callBack:(Boolean)->Unit){
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
    fun getCartList(){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Cart")
                .get().addOnSuccessListener {
                    _cartList.value = it.toObjects(OrderedFood::class.java)
                }
        }
    }
    fun removeFromCart(food: OrderedFood,callBack:(Boolean)->Unit){
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
    fun updateCartCount(food:OrderedFood,uptadeCount:Int){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Cart")
                .document(food.id).update("soldCount",uptadeCount)
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
//    fun addCart(food:Food,callBack:(Boolean)->Unit){
//        val newOrder=OrderedFood(food.id,food.name,food.description,food.imageUrl,food.price,food.category,1,System.currentTimeMillis().toString(),false,uuid)
//        _cartList.value?.add(newOrder)
//
//    }
//    fun removeCart(food:Food,callBack:(Boolean)->Unit){
//        val newOrder=OrderedFood(food.id,food.name,food.description,food.imageUrl,food.price,food.category,1,System.currentTimeMillis().toString(),false,uuid)
//        if(_cartList.value?.contains(newOrder)!!){
//            _cartList.value?.remove(newOrder)
//        }
//
//    }
//    fun updateCartCount(food:OrderedFood,uptadeCount:Int){
//
//    }


}