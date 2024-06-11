package com.example.fooddeliveryproject.View.Restaurant

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import com.example.fooddeliveryproject.ui.theme.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar
import com.example.fooddeliveryproject.Utils.CircularIndeterminateProgressBar
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel

@Preview
@Composable
fun RestaurantAddProductPage(navHostController: NavHostController,viewModel: RestaurantViewModel= viewModel()) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    var foodName by remember {
        mutableStateOf("")
    }
    var foodDescription by remember {
        mutableStateOf("")
    }
    var foodImageUrl by remember {
        mutableStateOf("")
    }
    var foodPrice:Int by remember {
        mutableStateOf(0)
    }
    var foodCategory by remember {
        mutableStateOf("")
    }
    if(viewModel.editedFood.value!=null){
        foodName=viewModel.editedFood.value!!.name
        foodDescription=viewModel.editedFood.value!!.description
        foodImageUrl=viewModel.editedFood.value!!.imageUrl
        foodPrice=viewModel.editedFood.value!!.price
        foodCategory=viewModel.editedFood.value!!.category
    }


    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
            )
        }
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }


    Scaffold(
        topBar = {
            if (viewModel.editedFood.value != null) {
                AppBar(imageId = R.drawable.arrow_left,"Ürün Güncelle",isClickable = true,navHostController=navHostController)
            }else{
                AppBar(imageId = R.drawable.arrow_left,"Ürün Ekle",isClickable = true,navHostController=navHostController)

            }

        }
    ) {
        Surface(contentColor = Color.Black,modifier = Modifier
            .padding(it)
            .fillMaxWidth()
            .fillMaxHeight()
            ) {

            Box {
                Column(modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp)
                    , horizontalAlignment = Alignment.CenterHorizontally) {
                    if(bitmap.value!=null) {
                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .clickable {
                                        launcher.launch("image/*")

                                    }
                            )
                        }
                    }else{
                        Image(painter = painterResource(id = R.drawable.upload_image2), contentDescription ="Upload Image", alignment = Alignment.TopCenter , modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                launcher.launch("image/*")
                            })
                    }


                    Spacer(modifier = Modifier.padding(20.dp))
                    OutlinedTextField(value =foodName , onValueChange ={
                        foodName=it
                    } , label = {
                        Text(text = "Ürün ", fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    },)

                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(value =foodDescription , onValueChange ={
                        foodDescription=it
                    } , label = {
                        Text(text = "Ürün Detayı", fontSize = 15.sp,         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    },)


                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(value =if (foodPrice == 0) "" else foodPrice.toString() ,
                        onValueChange ={
                            foodPrice=it.trim().toInt()
                        } , label = {

                            Text(text = "Ürün Fiyatı", fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))


                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(value =foodCategory , onValueChange ={
                        foodCategory=it
                    } , label = {
                        Text(text = "Ürün Kategorisi", fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    },)

                    Spacer(modifier = Modifier.padding(15.dp))
                    Button(onClick = {
                        if (foodName == "" || foodDescription == "" || foodPrice == 0 || foodCategory == "" || imageUri == null) {
                            Toast.makeText(context, "Lutfen tum bilgileri giriniz", Toast.LENGTH_SHORT).show()
                            return@Button
                        }else{
                            isLoading.value=true
                            addProductToDatabase(viewModel.editedFood.value?.id,foodName,foodDescription,foodImageUrl,foodPrice,foodCategory,imageUri,viewModel) { isSuccess ->
                                if (isSuccess) {
                                    Log.d("hatamRestaurantAddProductPage", "Urun Eklendi")
                                    Toast.makeText(context, "Urun Eklendi", Toast.LENGTH_SHORT).show()

                                } else {
                                    Log.d("hatamRestaurantAddProductPage", "Urun Eklenmedi")
                                    Toast.makeText(context, "Urun Eklenemedi", Toast.LENGTH_SHORT).show()
                                    isLoading.value=false

                                }
                            }
                            isLoading.value=false
                        }

                    }, modifier = Modifier.fillMaxWidth(.7f), colors = ButtonDefaults.buttonColors(
                        orange)) {
                        Text(text = "Kaydet", fontSize = 15.sp,         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    }
                }
            }
            CircularIndeterminateProgressBar(isDisplayed = isLoading.value)
        }
    }
}

private fun addProductToDatabase(id: String?,name: String, description: String, image: String, price: Int, category: String,imageUri: Uri?,viewModel: RestaurantViewModel,callback:(Boolean)->Unit) {

    if (id != null) {
        viewModel.updateFood(Food(id = id, name = name, description = description, price = price, category = category, imageUrl = image)){
            callback(it)
        }
    }
    viewModel.addProduct(Food(id=System.currentTimeMillis().toString(),name=name,description=description,price=price,category=category,imageUrl=image),imageUri!!){
        callback(it)
    }


}



