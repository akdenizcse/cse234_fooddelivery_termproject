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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen
@Preview
@Composable
fun SignUpPage(navHostController: NavHostController,viewModel: AuthenticatorViewModel){

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ) {
        val isimFocusRequester = remember { FocusRequester() }
        val soyisimFocusRequester = remember { FocusRequester() }
        val emailFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }

        val name = remember {
            mutableStateOf("")
        }
        val surname = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }
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
            LoginMyTextField(param = "İsim", focusRequester = isimFocusRequester, onImeAction = {
                soyisimFocusRequester.requestFocus()
            }) {
                name.value = it
            }
            Spacer(modifier = Modifier.height(7.dp))
            LoginMyTextField(param = "Soyisim", focusRequester = soyisimFocusRequester, onImeAction = {
                emailFocusRequester.requestFocus()
            }) {
                surname.value = it
            }
            Spacer(modifier = Modifier.height(7.dp))
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

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                          if(name.value.isNotEmpty()&&surname.value.isNotEmpty()&&email.value.isNotEmpty()&&password.value.isNotEmpty()){
                              viewModel.createUser(email.value,password.value){
                                  Toast.makeText(navHostController.context,"Hesap oluşturuldu",Toast.LENGTH_SHORT).show()
                                  navHostController.navigate(StoreScreen.HomeScreen.name)
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

                    Text(text = "Kaydol",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)

                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            FadedTextComponent(param = "Kaydol’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")

            Spacer(modifier = Modifier.height(15.dp))
            ClickableTextComponent(param1 = "Hesabın var mı?", param2 = " Giriş Yap",StoreScreen.LoginScreen.name, navHostController)
            Spacer(modifier = Modifier.height(90.dp))
            ClickableTextComponent(param1 = "", param2 = "Restoran Hesabı Oluştur", StoreScreen.RestaurantSignUpPage.name, navHostController)

        }

        
    }

}


