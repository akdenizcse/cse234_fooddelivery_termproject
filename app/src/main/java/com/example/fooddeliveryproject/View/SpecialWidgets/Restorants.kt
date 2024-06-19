package com.example.fooddeliveryproject.View.SpecialWidgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.Restaurant
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen


@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Row(modifier = Modifier.padding(start = 5.dp, top = 10.dp, end = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.align(Alignment.CenterVertically)){
            downladImage(imageUrl =restaurant.imageUrl , size = 64)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp)
        ) {
            Text(
                text = restaurant.name,
                color = Color.Black
            )
        }
        Row(
            horizontalArrangement = Arrangement.Absolute.Left
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Rating Star",
                tint = Color(0xFFF8742A),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "${restaurant.starRate}",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TopRestaurantsSection(restaurants: ArrayList<Restaurant>?,navHostController: NavHostController= rememberNavController()) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "En İyi Restoranlar",
                fontSize = 18.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Tümünü Gör",
                style = MaterialTheme.typography.body1.copy(color = Color(0xFFF8742A)),
                modifier = Modifier.clickable {
                    navHostController.navigate(StoreScreen.RestaurantScreen.name)
                }
            )
        }

        Column{
            if (restaurants != null) {
                for (restaurant in restaurants) {
                    RestaurantItem(restaurant = restaurant)
                }
            }
        }
    }
}

@Composable
fun Restorants(navHostController: NavHostController= rememberNavController(), restaurantViewModel: RestaurantViewModel= viewModel()) {

    restaurantViewModel.getRestaurantWithCount(5)
    val list by restaurantViewModel._restaurantList.observeAsState()
    TopRestaurantsSection(restaurants = list,navHostController = navHostController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview45485() {
    Restorants()
}