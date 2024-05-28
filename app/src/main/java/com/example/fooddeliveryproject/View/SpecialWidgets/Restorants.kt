package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier
            .padding(8.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = restaurant.imageRes),
            contentDescription = restaurant.name,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .size(width = 200.dp, height = 200.dp)
        )
        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.h6,
                color = Color.DarkGray
            )
            Text(
                text = restaurant.location,
                color = Color.Gray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Rating Star",
                tint = Color(0xFFF8742A),
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "${restaurant.rating} (${restaurant.reviewCount}+ yorum)",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TopRestaurantsSection(restaurants: List<Restaurant>) {
    Column(
        modifier = Modifier.padding(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "En İyi Restoranlar",
                style = MaterialTheme.typography.h6,
                color = Color.DarkGray
            )
            Text(
                text = "Tümünü Gör",
                style = MaterialTheme.typography.body1.copy(color = Color(0xFFF8742A))
            )
        }

        LazyRow(
            modifier = Modifier.padding(8.dp)
        ) {
            items(restaurants) { restaurant ->
                RestaurantItem(restaurant = restaurant)
            }
        }
    }
}

@Composable
fun Restorants() {
    val restaurants = listOf(
        Restaurant(R.drawable.dukkan9, "Çorbacı Şükrü", "Kültür, Antalya", 4.1f, 500),
        Restaurant(R.drawable.dukkan10, "Dürümcü Bedir", "Konyaaltı, Antalya", 3.6f, 400),
        Restaurant(R.drawable.dukkan3, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan11, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan5, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan6, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.dukkan7, "Burger King", "Güvercin, Kültür", 4.7f, 150),
    )
    TopRestaurantsSection(restaurants = restaurants)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4545() {
    Restorants()
}
