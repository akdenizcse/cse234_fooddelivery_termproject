package com.example.fooddeliveryproject.View.Restaurant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar

@Preview
@Composable
fun RestaurantPasswordChangePage(){
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newPasswordAgain by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.arrow_left,"")
        }
    ) {
        Surface(contentColor = Color.Black,modifier = Modifier
            .padding(it)
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Column(modifier = Modifier.padding(top = 50.dp, start = 30.dp)) {
                Text(text = "Şifreyi Değiştir", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                )

                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Mevcut Şifre", fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                )
                OutlinedTextField(
                    value = oldPassword,
                    onValueChange = {oldPassword=it},
                    label = {
                        Text(text = "Mevcut Şifre", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Yeni Parola", fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                )
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = {newPassword=it},
                    label = {
                        Text(text = "Lütfen Yeni Şifrenizi Girin", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                )


                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Parola Onayı", fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                )
                OutlinedTextField(
                    value = newPasswordAgain,
                    onValueChange = {newPasswordAgain=it},
                    label = {
                        Text(text = "Lütfen Yeni Şifrenizi Tekrar Girin", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                )

            }
            Column (verticalArrangement = Arrangement.Bottom){
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp, start = 30.dp, end = 30.dp)) {
                    Text(text = "Kaydet", color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal))
                    )
                }
            }
        }
    }

}