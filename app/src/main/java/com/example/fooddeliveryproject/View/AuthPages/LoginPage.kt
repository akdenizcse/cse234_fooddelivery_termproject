package com.example.fooddeliveryproject.View.AuthPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
fun LoginPage(navHostController: NavHostController= rememberNavController()){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(100.dp))
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
            HeadingTextComponent(param = "Hesabına Giriş Yap")
            Spacer(modifier = Modifier.height(8.dp))
            NormalTextComponent("Uygulamaya giriş yapabilmek için mail adresini gir")
            Spacer(modifier = Modifier.height(25.dp))
            MyTextField(param = "Email")
            Spacer(modifier = Modifier.height(7.dp))
            PasswordTextField(param = "Şifre")

            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(param = "Giriş Yap")
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent(text = "Ya da")
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponentGoogle(param = "Google")
            Spacer(modifier = Modifier.height(10.dp))
            FadedTextComponent(param = "Girş yap’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")

            Spacer(modifier = Modifier.height(25.dp))
            ClickableTextComponent(param1 = "Hesabın yok mu?", " Kaydolmak için tıkla!", StoreScreen.SignUpPage.name, navHostController)

            Spacer(modifier = Modifier.height(70.dp))
            ClickableTextComponent(param1 = "Restoran'ın mı var?", " Restoran Girişi", StoreScreen.SignUpPage.name, navHostController)


        }

    }

}


@Composable
@Preview
fun DefaultPreview0fSignUpScreen(){

    LoginPage()
}

