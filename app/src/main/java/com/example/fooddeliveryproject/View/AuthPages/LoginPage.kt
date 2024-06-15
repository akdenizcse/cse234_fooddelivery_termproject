package com.example.fooddeliveryproject.View.AuthPages

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen

@Preview(showBackground = true)
@Composable
fun LoginPage(
    navHostController: NavHostController = rememberNavController(),
    authVm: AuthenticatorViewModel
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)

    ) {
        val emailFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }

        val password = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
            LoginMyTextField(param = "Email", focusRequester = emailFocusRequester, onImeAction = {
                passwordFocusRequester.requestFocus()
            }) {
                email.value = it
            }
            Spacer(modifier = Modifier.height(7.dp))
            LoginPasswordTextField(
                param = "Şifre",
                focusRequester = passwordFocusRequester,
                onImeAction = {
                },
                callback = {
                    password.value = it
                }
            )
            Log.d("hatamLog", " sda" + password.value)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                        authVm.signInWithEmail(email.value, password.value) {
                            Log.d(
                                "hatamSignInWithEmail",
                                "it.toString()" + email.value + password.value
                            )
                            if (it) {
                                authVm.setUserType(false)
                                navHostController.navigate(StoreScreen.HomeScreen.name) {
                                    popUpTo(StoreScreen.LoginScreen.name) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    navHostController.context,
                                    "Giriş Yapılamadı",
                                    Toast.LENGTH_SHORT
                                ).show()
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Giriş Yap",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))
//            DividerTextComponent(text = "Ya da")
//            Spacer(modifier = Modifier.height(20.dp))
//            ButtonComponentGoogle(param = "Google")
            Spacer(modifier = Modifier.height(10.dp))
            FadedTextComponent(param = "Girş yap’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")

            Spacer(modifier = Modifier.height(25.dp))
            ClickableTextComponent(
                param1 = "Hesabın yok mu?",
                " Kaydolmak için tıkla!",
                StoreScreen.SignUpPage.name,
                navHostController
            )

            Spacer(modifier = Modifier.height(20.dp))
            ClickableTextComponent(
                param1 = "Restoran'ın mı var?",
                " Restoran Girişi",
                StoreScreen.RestaurantLoginScreen.name,
                navHostController
            )


        }

    }

}



