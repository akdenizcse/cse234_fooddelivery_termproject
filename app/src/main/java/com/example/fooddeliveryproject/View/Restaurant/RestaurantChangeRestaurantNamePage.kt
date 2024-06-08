package com.example.fooddeliveryproject.View.Restaurant


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar
import com.example.fooddeliveryproject.ui.theme.orange

@Preview
@Composable
fun RestaurantChangeRestaurantNamePage(navHostController: NavHostController) {
    var newRestaurantName by remember {
        mutableStateOf("")
    }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.arrow_left,"", isClickable = true, navHostController = navHostController)
        }
    ) {
        Surface(contentColor = Color.Black,modifier = Modifier
            .padding(it)
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Column(modifier = Modifier.padding(top = 50.dp, start = 30.dp)) {
                Text(text = "Restoran Adını Değiştir", fontSize = 25.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))

                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Retoran Adı", fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                OutlinedTextField(
                    value = newRestaurantName,
                    onValueChange = {newRestaurantName=it},
                    label = {
                        Text(text = "Yeni Restoran Adı", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Parola Onayı", fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                OutlinedTextField(
                    value = password,
                    onValueChange = {password=it},
                    label = {
                        Text(text = "Lütfen Parolayı onaylayın", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                )

            }
            Column (verticalArrangement = Arrangement.Bottom){
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp, start = 30.dp, end = 30.dp)) {
                    Text(text = "Kaydet", color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                }
            }
        }
    }
}