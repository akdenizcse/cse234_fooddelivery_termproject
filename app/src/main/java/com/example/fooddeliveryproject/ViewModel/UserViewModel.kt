package com.example.fooddeliveryproject.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel():ViewModel() {
    private val db= Firebase.firestore
    private var uuid :String=""
    private val _user=MutableLiveData<User>()
    private val _cartList=MutableLiveData<List<OrderedFood>>()
    val cartList:LiveData<List<OrderedFood>> get() = _cartList
    val user:LiveData<User> get() = _user
    private val _preparedOrderList =MutableLiveData<List<OrderedFood>>()
    val preparedOrderList  :LiveData<List<OrderedFood>> get() = _preparedOrderList

    val _orderHistoryList =MutableLiveData<List<OrderedFood>>()
    val orderHistoryList  :LiveData<List<OrderedFood>> get() = _orderHistoryList
    init {
        if (FirebaseAuth.getInstance().currentUser != null) {
            uuid = FirebaseAuth.getInstance().currentUser!!.uid
        }
    }
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
            try {
                db.collection("Users").document(uuid).collection("Cart")
                    .get().addOnSuccessListener {
                        _cartList.value = it.toObjects(OrderedFood::class.java)
                    }.addOnFailureListener{
                        Log.e("hatamUVM",it.toString())
                    }
            }catch (e:Exception){
                Log.e("hatamUVM",e.toString())
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


    fun order(foodList :List<OrderedFood>,callBack:(Boolean)->Unit){
        viewModelScope.launch {
            foodList.forEach {
                db.collection("Users").document(uuid).collection("Order").add(it)
                Log.d("hatamOrder","ds  "+it.toString())
                db.collection("Restaurant").document(it.restaurantId).collection("Order").add(it)
            }
            removeCartList {
                callBack(it)
            }
        }
    }

    private fun removeCartList(callBack: (Boolean) -> Unit){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Cart")
                .get().addOnSuccessListener {
                    for (document in it){
                        db.collection("Users").document(uuid).collection("Cart")
                            .document(document.id).delete().addOnSuccessListener {
                                callBack(true)
                            }.addOnFailureListener(){
                                callBack(false)
                            }
                    }
                }
        }
    }

    fun getOrderHistoryList(){
        viewModelScope.launch {
            db.collection("Users").document(uuid).collection("Order")
                .get().addOnSuccessListener {
                    _orderHistoryList.value=it.toObjects(OrderedFood::class.java)
                }
        }
    }


}