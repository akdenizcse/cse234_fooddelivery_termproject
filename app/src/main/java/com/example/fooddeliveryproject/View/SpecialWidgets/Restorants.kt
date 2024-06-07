package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R

data class Restaurant(
    val imageRes: Int,
    val name: String,
    val location: String,
    val rating: Float,
    val reviewCount: Int
)

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = restaurant.imageRes),
            contentDescription = restaurant.name,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = restaurant.name,
                color = Color.Black
            )
            Text(
                text = restaurant.location,
                color = Color.Gray
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
                text = "${restaurant.rating}",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TopRestaurantsSection(restaurants: List<Restaurant>) {
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
                style = MaterialTheme.typography.body1.copy(color = Color(0xFFF8742A))
            )
        }

        Column{
            for (restaurant in restaurants) {
                RestaurantItem(restaurant = restaurant)
            }
        }
    }
}

@Composable
fun Restorants() {
    val restaurants = listOf(
        Restaurant(R.drawable.dukkan1, "Çorbacı Şükrü", "Kültür, Antalya", 4.1f, 500),
        Restaurant(R.drawable.dukkan12, "Dürümcü Bedir", "Konyaaltı, Antalya", 3.6f, 400),
        Restaurant(R.drawable.dukkan18, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan3, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan11, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan16, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan13, "Burger King", "Güvercin, Kültür", 4.7f, 150),
    )

    TopRestaurantsSection(restaurants = restaurants)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview45485() {
    Restorants()
}