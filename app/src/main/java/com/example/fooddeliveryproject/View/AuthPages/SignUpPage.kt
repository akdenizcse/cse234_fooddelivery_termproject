package com.example.fooddeliveryproject.View.AuthPages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R

@Composable
fun SignUpPage(){

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ) {
        
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(50.dp))
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White),
                shape = RoundedCornerShape(10.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.csefoodicon),
                    contentDescription = null

                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            HeadingTextComponent(param = "Hesap Oluştur")
            Spacer(modifier = Modifier.height(10.dp))
            NormalTextComponent(param = "Uygulamaya Kaydolmak için bilgilerini gir")

            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(param = "İsim")
            Spacer(modifier = Modifier.height(7.dp))
            MyTextField(param = "Soyisim")
            Spacer(modifier = Modifier.height(7.dp))
            MyTextField(param = "Email")
            Spacer(modifier = Modifier.height(7.dp))
            PasswordTextField(param = "Şifre")

            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(param = "Kaydol")
            Spacer(modifier = Modifier.height(15.dp))
            FadedTextComponent(param = "Kaydol’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")

            Spacer(modifier = Modifier.height(15.dp))
            ClickableTextComponent(param1 = "Hesabın var mı?", param2 = " Giriş Yap")
            Spacer(modifier = Modifier.height(90.dp))
            ClickableTextComponent(param1 = "", param2 = "Restoran Hesabı Oluştur")

        }

        
    }

}


@Composable
@Preview
fun DefaultPreview0fSignUpPage(){

    SignUpPage()
}