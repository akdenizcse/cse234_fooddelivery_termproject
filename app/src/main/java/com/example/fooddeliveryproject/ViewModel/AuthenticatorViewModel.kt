package com.example.fooddeliveryproject.ViewModel

import android.app.Activity
import android.net.Uri
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Restaurant
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch


class AuthenticatorViewModel:ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db=Firebase.firestore
    private val storage= FirebaseStorage.getInstance()
    private val storageReference=storage.reference
    private val _isRestaurantUser = MutableLiveData<Boolean>()
    val isRestaurantUser: LiveData<Boolean> get() = _isRestaurantUser

    fun setUserType(isRestaurant: Boolean) {
        _isRestaurantUser.value = isRestaurant
    }
    fun signInWithEmail(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }

    fun isLogin(): Boolean{
        if(auth.currentUser != null){
            return true
        }else{
            return false
        }

    }

    fun createUser(email:String, password:String, onResult: (Boolean) -> Unit){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                db.collection("Users").document(it.result?.user?.uid.toString()).set(Restaurant(id = auth.uid.toString()))
                    .addOnSuccessListener {
                    onResult(true)
                }.addOnFailureListener {
                    onResult(false)
                    }
            }else{
                onResult(false)
            }

        }
    }

    fun createRestaurant(email:String, password:String,name:String ,imageUri: Uri, onResult: (Boolean) -> Unit){
        //Restoran için restoran fotoğrafı eklenecek
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) {
                uploadImage(imageUri,"RestaurantImage",storageReference){imageUrl->
                    if ("error"!=imageUrl){
                        db.collection("Restaurant").document(it.result?.user?.uid.toString())
                            .set(Restaurant(id = auth.uid.toString(),name=name,imageUrl=imageUrl))
                            .addOnSuccessListener {
                                onResult(true)
                            }.addOnFailureListener{
                                onResult(false)
                            }
                    }else{
                        onResult(false)
                    }
                }

            } else {
                onResult(false)
            }
        }
    }

    fun signOut(){
        viewModelScope.launch {
            auth.signOut()
        }
    }

    fun restaurantSignIn(email: String, password: String, onResult: (Boolean) -> Unit) {
        db.collection("RestaurantList").document(email).get().addOnCompleteListener(){
            if(it.result!=null && it.result.exists()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onResult(true)
                        } else {
                            onResult(false)
                        }
                    }

                }else{
                    onResult(false)
                }
        }

    }

    fun restaurantSignUp(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    db.collection("RestaurantList").document(email).set("restaurantEmail" to email)
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }


}