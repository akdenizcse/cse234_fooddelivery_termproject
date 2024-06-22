package com.example.fooddeliveryproject.View.Restaurant

import android.content.Context
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
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.RestaurantScreen
import com.google.firebase.auth.FirebaseAuth

@Preview
@Composable
fun RestaurantAddProductPage(navHostController: NavHostController,viewModel: RestaurantViewModel= viewModel(),food:Food?=null,isEdit:Boolean=false ) {
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
        foodName= food?.name.toString()
        foodDescription=food?.description.toString()
        foodImageUrl=food?.imageUrl.toString()
        foodPrice= food?.price!!
        foodCategory=food?.category.toString()
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
                            Box {
                                if (food?.imageUrl==imageUri.toString()){
                                    downladImage(imageUrl = food?.imageUrl!!, size = 150, modifier = Modifier.clickable {
                                        launcher.launch("image/*")
                                    })
                                }else{
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
                            }
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
                            try {
                                foodPrice=it.trim().toInt()
                            }catch (e:Exception){

                            }
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
                            if (isEdit && food!=null ) {
                                food.name=foodName
                                food.price=foodPrice
                                food.description=foodDescription
                                food.category=foodCategory
                                if(imageUri.toString()!=food.imageUrl){
                                    viewModel.uploadFoodImage(imageUri = imageUri!!, folderName = "food"){
                                        if (it !="error"){
                                            food.imageUrl = it
                                            updateFood(food = food!!,viewModel, context = context) { isSuccess ->
                                                if (isSuccess) {
                                                    navHostController.navigate(RestaurantScreen.RestaurantHomeScreen.name)
                                                } else {
                                                    isLoading.value=false
                                                }
                                            }
                                        }else{
                                            Toast.makeText(context, "Resim Yuklenmedi", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }else{
                                    updateFood(food = food!!,viewModel, context = context) { isSuccess ->
                                        if (isSuccess) {
                                            navHostController.navigate(RestaurantScreen.RestaurantHomeScreen.name)
                                        } else {
                                            isLoading.value=false
                                        }
                                    }
                                }

                            }else{
                                addProductToDatabase(Food(id = System.currentTimeMillis().toString(),name=foodName,description=foodDescription,price=foodPrice,category=foodCategory,imageUrl=foodImageUrl, restaurantId = FirebaseAuth.getInstance().uid.toString()),viewModel, imageUri = imageUri!!, context = context){
                                    if (it){
                                        navHostController.navigate(RestaurantScreen.RestaurantHomeScreen.name)
                                    }
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



private fun updateFood(food: Food, viewModel: RestaurantViewModel, context: Context, callback: (Boolean) -> Unit){

    viewModel.updateFood(food = food){
        if (it){
            Toast.makeText(context, "Urun Guncellendi", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Urun Guncellenemedi", Toast.LENGTH_SHORT).show()
        }
        callback(it)
    }
}
private fun addProductToDatabase(food: Food,viewModel: RestaurantViewModel,imageUri: Uri,context: Context,callback: (Boolean) -> Unit){
    viewModel.addProduct(food = food, imageUri = imageUri){
        if (it){
            Toast.makeText(context, "Urun Eklendi", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Urun Eklenemedi", Toast.LENGTH_SHORT).show()
        }
        callback(it)
    }
}


