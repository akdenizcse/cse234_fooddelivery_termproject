package com.example.fooddeliveryproject.View

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel

class LoginPage : ComponentActivity()  {

    val authVM : AuthenticatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)



        authVM.signInWithEmail("email","pass"){result->
            if (result){

            }
        }

    }




}