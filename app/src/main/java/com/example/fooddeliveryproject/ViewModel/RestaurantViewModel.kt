package com.example.fooddeliveryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.Restaurant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch

class RestaurantViewModel ():ViewModel() {
    private val db=Firebase.firestore
    private val storage=FirebaseStorage.getInstance()
    private val storageReference=storage.reference
    private var urlQueue :String= ""


    val _restaurant=MutableLiveData<Restaurant>()
    val restaurant :LiveData<Restaurant> get() = _restaurant
    fun getRestaurantInfo(){
        viewModelScope.launch {

        }
    }

    fun addProduct(food: Food,imageUri: Uri,calllback: (Boolean) -> Unit){
     viewModelScope.launch {
        uploadImage(imageUri,"food"){
            if (it=="error"){
                Log.d("hatamVM","image not upload error")
                calllback(false)
            }else{
                food.imageUrl=it
                db.collection("Food").document(food.id).set(food)
                    .addOnSuccessListener {
                        Log.d("hatamVM","success")
                        calllback(true)
                    }.addOnFailureListener(){error->
                        Log.d("hatamVM","falseerror= "+error.toString())
                        calllback(false)
                    }
            }
        }
     }
    }

    private fun uploadImage(imageUri:Uri,folderName: String,calllback: (String) -> Unit) {
        val imageNamePrefix = "image_${System.currentTimeMillis()}"
        var uploadedCount = 0
        val imageName = "$imageNamePrefix${uploadedCount++}"
        val imageRef: StorageReference = storageReference.child("$folderName/$imageName")

        if (imageUri != null) {
            imageRef.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri->
                        urlQueue=(uri.toString())
                        calllback(uri.toString())
                    }
                }
                .addOnFailureListener { exception ->
                    calllback("error")
                    Log.e("hatamVM", "error= "+exception.toString())
                    exception.printStackTrace()
                }
        }
    }
}