package com.example.fooddeliveryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.Restaurant
import com.example.fooddeliveryproject.Utils.uploadImage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch

class RestaurantViewModel ():ViewModel() {
    private val db=Firebase.firestore
    private val storage=FirebaseStorage.getInstance()
    private val storageReference=storage.reference
    private var uuid :String= ""


    val _restaurant=MutableLiveData<Restaurant>()
    val restaurant :LiveData<Restaurant> get() = _restaurant



    fun getRestaurantInfo(){
        viewModelScope.launch {
            db.collection("Restaurant").document(uuid).get().addOnSuccessListener {
                _restaurant.value=it.toObject(Restaurant::class.java)
            }
        }
    }

    fun addProduct(food: Food,imageUri: Uri,calllback: (Boolean) -> Unit){
     viewModelScope.launch {
        uploadImage(imageUri,"food",storageReference){
            if (it=="error"){
                Log.d("hatamVM","image not upload error")
                calllback(false)
            }else{
                food.imageUrl=it
                db.collection("Food").document(food.id).set(food)
                    .addOnSuccessListener {
                        Log.d("hatamVM","success")
                        addFoodToRestaurant(food){
                            if (it){
                                calllback(true)
                            }else{
                                calllback(false)
                            }
                        }

                    }.addOnFailureListener(){error->
                        Log.d("hatamVM","false error= "+error.toString())
                        calllback(false)
                    }
            }
        }
     }
    }

    private fun addFoodToRestaurant(food: Food,calllback: (Boolean) -> Unit){
        viewModelScope.launch {
            db.collection("Restaurant").document(uuid).collection("Food").document(food.id).set(food).addOnSuccessListener {
                calllback(true)
            }.addOnFailureListener(){
                calllback(false)
            }
        }
    }



}