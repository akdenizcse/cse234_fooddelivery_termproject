package com.example.fooddeliveryproject.View.AuthPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun signUpScreen(){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(150.dp))
            HeadingTextComponent(param = "Hesabına Giriş Yap")
            NormalTextComponent("Uygulamaya giriş yapabilmek için mail adresini gir")
            Spacer(modifier = Modifier.height(15.dp))
            MyTextField(param = "Email")
            PasswordTextField(param = "Şifre")

            Spacer(modifier = Modifier.height(25.dp))
            ClickableTextComponent(param1 = "Hesabın yok mu?", " Kaydolmak için tıkla!")

        }

    }

}


@Composable
@Preview
fun DefaultPreview0fSignUpScreen(){
    signUpScreen()
}

