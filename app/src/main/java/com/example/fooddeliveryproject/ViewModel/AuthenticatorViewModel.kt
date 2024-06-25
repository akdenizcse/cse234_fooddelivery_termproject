package com.example.fooddeliveryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Restaurant
import com.example.fooddeliveryproject.Models.User
import com.example.fooddeliveryproject.Utils.uploadImage
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch


class AuthenticatorViewModel:ViewModel() {
    private val db=Firebase.firestore
    private val storage= FirebaseStorage.getInstance()
    private val storageReference=storage.reference

    fun signInWithEmail(email: String, password: String, onResult: (Boolean) -> Unit) {
        try {
            val auth=FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true)
                    } else {
                        onResult(false)
                    }
                }
        }catch (e:Exception){
            Log.e("hatamAuth",e.toString())
        }
    }

    fun isLogin(callBack: (Boolean) -> Unit){
        val auth=FirebaseAuth.getInstance()
        db.collection("RestaurantList").document(auth.currentUser?.email.toString())
            .get().addOnCompleteListener() {
            if (it.result?.exists() == true) {
                if(auth.currentUser != null){
                    callBack(true)
                }else{
                    callBack(false)
                }
            }
        }


    }

    fun createUser(email:String, password:String, onResult: (Boolean) -> Unit){
        try {
            val auth=FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    db.collection("Users").document(it.result?.user?.uid.toString()).set(User(id = auth.uid.toString()))
                        .addOnSuccessListener {
                            onResult(true)
                        }.addOnFailureListener {
                            onResult(false)
                        }
                }else{
                    onResult(false)
                }

            }
        }catch (e:Exception){
            Log.e("hatamAuth",e.toString())
        }

    }

    fun createRestaurant(email:String, password:String,name:String ,imageUri: Uri, onResult: (Boolean) -> Unit){
        try {
            val auth=FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                Log.d("hatamAuthR","create User")
                if (it.isSuccessful) {
                    Log.d("hatamAuthR","create success")

                    uploadImage(imageUri,"RestaurantImage",storageReference){imageUrl->
                        Log.d("hatamAuthR","create imageUrl"+imageUrl.toString())

                        if ("error"!=imageUrl){
                            Log.d("hatamAuthR","image error değil")
                            db.collection("Restaurant").document(it.result?.user?.uid.toString())
                                .set(Restaurant(id = auth.uid.toString(),name=name,imageUrl=imageUrl))
                                .addOnSuccessListener {
                                    db.collection("RestaurantList").document(email).set("email" to email).addOnSuccessListener {
                                        onResult(true)
                                    }.addOnFailureListener(){
                                        onResult(false)
                                    }
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
        }catch (e:Exception){
            Log.e("hatamAuthR",e.toString())
        }


    }

    fun signOut(){
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().signOut()

            }catch (e:Exception){
                Log.e("hatamSignOut",e.toString())
            }
        }
    }

    fun restaurantSignIn(email: String, password: String, onResult: (Boolean) -> Unit) {
       try {
           val authh=FirebaseAuth.getInstance()
           db.collection("RestaurantList").document(email).get().addOnCompleteListener(){
               if(it.result!=null && it.result.exists()){
                   authh.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener { task ->
                           Log.d("hatamLoginVM",email+" "+password)
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
       }catch (e:Exception){
           Log.e("hatamLoginVM",e.toString())
       }


    }
    fun updatePassword(currentPassword: String, newPassword: String, onResult: (String) -> Unit) {
     viewModelScope.launch {
         try {
             val auth=FirebaseAuth.getInstance()
             val user = auth.currentUser
             if (user != null && currentPassword.isNotEmpty() && newPassword.isNotEmpty()) {
                 val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)

                 viewModelScope.launch {
                     user.reauthenticate(credential)
                         .addOnCompleteListener { task ->
                             if (task.isSuccessful) {
                                 user.updatePassword(newPassword)
                                     .addOnCompleteListener { updateTask ->
                                         if (updateTask.isSuccessful) {
                                             onResult("Şifre değiştirildi")
                                         } else {
                                             onResult("Şifre değiştirilemedi: ${updateTask.exception?.message}")
                                         }
                                     }
                             } else {
                                 onResult("Mevcut şifre hatalı: ${task.exception?.message}")
                             }
                         }
                 }
             } else {
                 onResult("Lütfen tüm alanları doldurunuz.")
             }
         }catch (e:Exception){
             Log.e("hatamLoginVM",e.toString())
         }
     }

    }
}