package com.example.fooddeliveryproject.View.Restaurant

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.fooddeliveryproject.ui.theme.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar
@Preview
@Composable
fun RestaurantAddProductPage() {
    var productInfo:Food by remember {
        mutableStateOf(Food(-1,"","","",0,"",-1))
    }
    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.fork_and_spoon,"Restoran Hesabım")
        }
    ) {
        Surface(contentColor = Color.Black,modifier = Modifier
            .padding(it)
            .fillMaxWidth()
            .fillMaxHeight()
            ) {
            Column(modifier = Modifier.background(Color.White  )
                .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.upload_image2), contentDescription ="Upload Image", alignment = Alignment.TopCenter , modifier = Modifier.size(150.dp))

                Spacer(modifier = Modifier.padding(20.dp))
                OutlinedTextField(value =productInfo.name , onValueChange ={
                    productInfo.name=it
                } , label = {
                    Text(text = "Ürün Adı", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                },)

                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(value =productInfo.description , onValueChange ={
                    productInfo.description=it
                } , label = {
                    Text(text = "Ürün Detayı", fontSize = 15.sp,         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                },)


                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(value =if (productInfo.price == 0) "" else productInfo.price.toString() ,
                    onValueChange ={
                    productInfo.price=it.toInt()
                } , label = {

                    Text(text = "Ürün Fiyatı", fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))


                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(value =productInfo.category , onValueChange ={
                    productInfo.category=it
                } , label = {
                    Text(text = "Ürün Kategorisi", fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                },)

                Spacer(modifier = Modifier.padding(15.dp))
                Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(.7f), colors = ButtonDefaults.buttonColors(
                    orange)) {
                    Text(text = "Kaydet", fontSize = 15.sp,         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    
                }


            }

        }
    }
}