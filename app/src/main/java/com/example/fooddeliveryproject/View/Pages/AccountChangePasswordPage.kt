package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.AuthPages.ButtonComponent
import com.example.fooddeliveryproject.View.AuthPages.ChangePasswordTextField

@Preview
@Composable
fun AccountChangePassword(){
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

                        Row {


                            Column(modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center)
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.csefoodicon),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = "Şifreyi Değiştir",
                                    //modifier = Modifier.fillMaxWidth(),
                                    color = colorResource(id = R.color.black),
                                    style = TextStyle(
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                            }
                        }

                    },
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
                    backgroundColor = colorResource(id = R.color.orange),
                    contentColor = contentColorFor(backgroundColor = colorResource(id = R.color.googleColor))
                )
            }
        ) {

            changePassword(paddingValues = it)

        }
    }
}



@Composable
fun changePassword(paddingValues: PaddingValues){

    val currentPasswordFocusRequester = remember { FocusRequester() }
    val newPasswordFocusRequester = remember { FocusRequester() }
    val newPasswordAgainFocusRequester = remember { FocusRequester() }

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
                callback = {}
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
            ButtonComponent(param = "Şifreyi Değiştir")



        }

    }



}
