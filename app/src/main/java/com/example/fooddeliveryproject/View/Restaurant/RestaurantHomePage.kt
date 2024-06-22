package com.example.fooddeliveryproject.View.Restaurant


import android.util.Log
import com.example.fooddeliveryproject.ui.theme.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar
import com.example.fooddeliveryproject.Utils.CircularIndeterminateProgressBar
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.RestaurantScreen
import kotlinx.coroutines.delay


@Composable
@Preview
fun RestaurantHomePage(navHostController: NavHostController= rememberNavController(),restaurantViewModel: RestaurantViewModel) {

    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.fork_and_spoon,"Food Delivery")
        }
    ){
        restaurantViewModel.editedFood.value=null
        Surface(modifier = Modifier
            .padding(it)
            .background(Color.White), ) {
            restaurantViewModel.getRestaurantFoodList()
            val foodList by restaurantViewModel.restaurantFoodList.observeAsState()
            val isLoading = remember { mutableStateOf(false) }


            isLoading.value=true

            CircularIndeterminateProgressBar(isLoading.value)
            if (foodList!=null){
                isLoading.value=false
                RestaurantALlProducts(foodList!!,navHostController,restaurantViewModel)
            }else{
                LaunchedEffect(key1 = Unit) {
                    delay(1000)
                    isLoading.value=false
                }

            }
        }

    }
}

@Composable
fun RestaurantALlProducts(list: ArrayList<Food>,navHostController: NavHostController,viewModel:RestaurantViewModel) {
    val navController= navHostController

        LazyColumn(Modifier.background(Color.White)) {
            items(list) { food ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White),
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column (modifier = Modifier.width(200.dp)){
                            Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = food.description, fontSize = 14.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Row (horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically){
                                Text(text = "${food.price} TL", fontSize = 18.sp,fontWeight = FontWeight.Bold)
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Button(
                                        onClick = {
                                            try {
                                                viewModel.editedFood.value = food
                                                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                                    key = "editedFood",
                                                    value = food
                                                )
                                                navController.navigate(RestaurantScreen.RestaurantEditProductScreen.name)
                                                Log.e("hatam","tıklandı"+food.name)
                                            }catch (e:Exception){
                                                Log.e("hatamClick",e.toString())
                                            }
                                        },
                                        modifier = Modifier
                                            .width(100.dp)
                                            .height(30.dp), colors = ButtonDefaults.buttonColors(orange)
                                    ) {
                                        Text(text = "Düzenle", fontSize = 12.sp, textAlign = TextAlign.Center)
                                    }
                                }
                            }

                        }

                        Surface(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.White),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(width = 0.1.dp, color = Color.LightGray)
                        ) {
                            downladImage(imageUrl = food.imageUrl)
                        }
                    }
                }
            }
        }


}