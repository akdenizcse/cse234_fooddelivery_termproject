package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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


@Composable
fun RestaurantPage(navigate: NavHostController = rememberNavController(),restaurantVM:RestaurantViewModel= viewModel()) {
//    val restaurants = listOf(
//        Restaurant(R.drawable.dukkan1, "Çorbacı Şükrü", "Kültür, Antalya", 4.1, 500),
//        Restaurant(R.drawable.dukkan6, "Hastayım Döner", "Konyaaltı, Antalya", 3.6, 400),
//        Restaurant(R.drawable.dukkan12, "Burger King", "Güvenen, Kültür", 4.7, 150),
//        Restaurant(R.drawable.dukkan11, "Makarna Yıldızı", "Çıralı, Antalya", 2.8, 200),
//        Restaurant(R.drawable.dukkan13, "Gogalo Döner", "Kültür, Antalya", 4.1, 500),
//        Restaurant(R.drawable.dukkan16, "Asker Abi'nin Yeri", "Konyaaltı, Antalya", 3.6, 400),
//        Restaurant(R.drawable.dukkan18, "McDonalds", "Güvenen, Kültür", 4.7, 150),
//        Restaurant(R.drawable.dukkan11, "Komagene", "Çıralı, Antalya", 2.8, 200)
//    )

    restaurantVM.getRestaurantList()
    val restaurantList by restaurantVM._restaurantList.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Restoranlar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                navigate.popBackStack()

                            }
                        )
                    }
                },
                backgroundColor = Color(0xFFF8742A)
            )
        },
        content = { paddingValues ->
            if (restaurantList!=null){
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    items(restaurantList!!) { restaurant ->
                        RestaurantItem(restaurant)
                    }
                }
            }

        }
    )
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            downladImage(imageUrl = restaurant.imageUrl, size = 80)
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = restaurant.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
//                Text(
//                    text = restaurant.location,
//                    fontSize = 14.sp,
//                    color = Color.Gray
//                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Rating",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${restaurant.starRate}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Text(
                    text = "(${restaurant.commentCount}+ yorum)",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantPagePreview() {
    RestaurantPage()
}
