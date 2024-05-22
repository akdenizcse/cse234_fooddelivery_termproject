package com.example.fooddeliveryproject.ViewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthenticatorViewModel:ViewModel() {

    fun signInWithEmail(email: String, password: String, onResult: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
    }

    fun isLogin(): Boolean{
        if(FirebaseAuth.getInstance().currentUser != null){
            return true
        }else{
            return false
        }

    }

    fun createUser(email:String, password:String, onResult: (Boolean) -> Unit){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                onResult(true)
            }else{
                onResult(false)
            }

        }
    }

    



}