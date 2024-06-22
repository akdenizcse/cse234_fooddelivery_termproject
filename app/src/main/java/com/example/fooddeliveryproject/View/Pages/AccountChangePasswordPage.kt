package com.example.fooddeliveryproject.View.Pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.AuthPages.ButtonComponent
import com.example.fooddeliveryproject.View.AuthPages.ChangePasswordTextField
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.UserViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

@Preview
@Composable
fun AccountChangePassword(navHostController: NavHostController,authViewModel:AuthenticatorViewModel){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.googleColor))
            //.padding(start = 5.dp, end = 5.dp)

    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            IconButton(onClick = { navHostController.popBackStack() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_left),
                                    contentDescription = "Back",
                                    tint = Color.Black,
                                    modifier = Modifier.size
                                        (40.dp)
                                        //.padding(bottom = 5.dp)
                                )
                            }
                            //Spacer(modifier = Modifier.width(15.dp))


                            Column(modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center)
                            {
                                Row {
                                    Image(
                                        painter = painterResource(id = R.drawable.csefoodicon),
                                        contentDescription = null
                                    )
                                    Text(
                                        text = "        ",
                                        //modifier = Modifier.fillMaxWidth(),
                                        color = colorResource(id = R.color.purple_200),
                                        style = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                Spacer(modifier = Modifier.height(15.dp))
                                Row {
                                    Text(
                                        text = "Şifreyi Değiştir",
                                        //modifier = Modifier.fillMaxWidth(),
                                        color = colorResource(id = R.color.black),
                                        style = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = "        ",
                                        //modifier = Modifier.fillMaxWidth(),
                                        color = colorResource(id = R.color.purple_200),
                                        style = TextStyle(
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                }


                            }

                        }

                    },
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
                    backgroundColor = colorResource(id = R.color.orange),
                    contentColor = contentColorFor(backgroundColor = colorResource(id = R.color.googleColor))
                )
            }
        ) {

            changePassword(paddingValues = it,navHostController=navHostController,authViewModel=authViewModel)

        }
    }
}



@Composable
fun changePassword(paddingValues: PaddingValues,navHostController: NavHostController,authViewModel: AuthenticatorViewModel){
    val context= LocalContext.current
    val currentPasswordFocusRequester = remember { FocusRequester() }
    val newPasswordFocusRequester = remember { FocusRequester() }
    val newPasswordAgainFocusRequester = remember { FocusRequester() }
    val currentPass by remember { mutableStateOf("")}
    val newPass by remember { mutableStateOf("")}
    val newPassAgain by remember { mutableStateOf("")}


    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .padding(20.dp),
        shape = RoundedCornerShape(50.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.detailsPageColor))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ChangePasswordTextField(
                param = "Mevcut Şifre",
                last = false,
                focusRequester = currentPasswordFocusRequester,
                onImeAction = {
                              newPasswordFocusRequester.requestFocus()
                },
                callback = {

                }
            )
            Spacer(modifier = Modifier.height(7.dp))
            ChangePasswordTextField(
                param = "Yeni Şifre",
                last = false,
                focusRequester = newPasswordFocusRequester,
                onImeAction = {
                    newPasswordAgainFocusRequester.requestFocus()
                },
                callback = {}
            )
            Spacer(modifier = Modifier.height(7.dp))
            ChangePasswordTextField(
                param = "Yeni Şifre (Tekrar)",
                last = true,
                focusRequester = newPasswordAgainFocusRequester,
                onImeAction = {
                },
                callback = {}
            )

            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    if (newPassAgain==newPass){
                            authViewModel.updatePassword(currentPassword = currentPass,newPass){
                                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                                if(it=="Şifre değiştirildi"){
                                    navHostController.popBackStack()
                                }
                            }
                    }else{
                        Toast.makeText(context,"Yeni sifreler uyusmuyor",Toast.LENGTH_SHORT).show()
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

                    androidx.compose.material3.Text(text = "Sifreyi Değiştir",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)

                }
            }


        }

    }
}


