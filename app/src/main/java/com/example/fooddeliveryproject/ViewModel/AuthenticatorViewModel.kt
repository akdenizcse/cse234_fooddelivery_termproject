package com.example.fooddeliveryproject.ViewModel

import android.app.Activity
import android.net.Uri
import androidx.compose.ui.res.stringResource
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


}