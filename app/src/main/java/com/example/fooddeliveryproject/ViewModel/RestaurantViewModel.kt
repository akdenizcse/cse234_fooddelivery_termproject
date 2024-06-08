package com.example.fooddeliveryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantViewModel ():ViewModel() {
    private val db=Firebase.firestore
    private val storage=FirebaseStorage.getInstance()
    private val storageReference=storage.reference
    private var uuid :String= "1231312"
    val isLoading= mutableStateOf(false)


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
         try {
             isLoading.value=true
             delay(5000)
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
                             isLoading.value=false
                         }.addOnFailureListener(){error->
                             Log.d("hatamVM","false error= "+error.toString())
                             calllback(false)
                             isLoading.value=false
                         }
                 }
             }
         }catch (e:Exception){
             Log.d("hatamAddProduct","error Add poroduct"+e.toString())
         }

     }
    }

    private fun addFoodToRestaurant(food: Food,calllback: (Boolean) -> Unit){
        try{
            viewModelScope.launch {
                db.collection("Restaurant").document(uuid).collection("Food").document(food.id).set(food).addOnSuccessListener {
                    Log.e("hatamAddProduct","success Add poroduct")

                    calllback(true)
                }.addOnFailureListener(){error->
                    Log.e("hatamAddProduct","error Add poroduct"+error.toString())
                    calllback(false)
                }
            }
        }catch (e:Exception){
            Log.d("hatamAddProduct","error Add poroduct"+e.toString())
        }

    }



}