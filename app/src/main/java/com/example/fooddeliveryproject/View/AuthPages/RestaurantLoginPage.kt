package com.example.fooddeliveryproject.View.AuthPages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen
@Preview
@Composable
fun RestaurantLoginPage(navHostController: NavHostController= rememberNavController(),authVm: AuthenticatorViewModel,) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)

    ) {
        val password = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }

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
            HeadingTextComponent(param = "Restoran Hesabına Giriş Yap")
            Spacer(modifier = Modifier.height(8.dp))
            NormalTextComponent("Uygulamaya giriş yapabilmek için mail adresini gir")
            Spacer(modifier = Modifier.height(25.dp))
            MyTextField(param = "Email"){
                email.value = it
            }
            Spacer(modifier = Modifier.height(7.dp))
            PasswordTextField(param = "Şifre"){
                password.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                        authVm.restaurantSignIn(email.value,password.value) {
                            if (it) {
                                authVm.setUserType(true)
                                navHostController.navigate(StoreScreen.HomeScreen.name) {
                                    popUpTo(StoreScreen.LoginScreen.name) {
                                        inclusive = true
                                    }
                                }
                            }else{
                                Toast.makeText(navHostController.context, "Giriş Yapılamadı", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Giriş Yap",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)

                }

            }

            Spacer(modifier = Modifier.height(20.dp))
//            DividerTextComponent(text = "Ya da")
//            Spacer(modifier = Modifier.height(20.dp))
//            ButtonComponentGoogle(param = "Google")
            Spacer(modifier = Modifier.height(10.dp))
            FadedTextComponent(param = "Girş yap’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")

            Spacer(modifier = Modifier.height(25.dp))
            ClickableTextComponent(param1 = "Restoran Hesabın yok mu?", " Kaydolmak için tıkla!", StoreScreen.RestaurantSignUpPage.name, navHostController)

            Spacer(modifier = Modifier.height(20.dp))
            ClickableTextComponent(param1 = "Müşeteri misiniz?", " Müşteri Girişi", StoreScreen.LoginScreen.name, navHostController)


        }

    }

}



