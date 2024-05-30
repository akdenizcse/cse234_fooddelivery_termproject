package com.example.fooddeliveryproject.View.AuthPages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun signUpScreen(){
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
    ) {
        NormalTextComponent("Hesabına Giriş Yap")
    }

}


@Composable
@Preview
fun DefaultPreview0fSignUpScreen(){
    signUpScreen()
}

