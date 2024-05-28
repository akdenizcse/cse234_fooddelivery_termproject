package com.example.fooddeliveryproject.View.SpecialWidgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black
            )
            Text(
                text = restaurant.location,
                style = MaterialTheme.typography.body2,
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
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun TopRestaurantsSection(restaurants: List<Restaurant>) {
    Column(
        modifier = Modifier.padding(16.dp)
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

        LazyColumn(
            modifier = Modifier.padding(top = 8.dp)
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
        Restaurant(R.drawable.noodle, "Çorbacı Şükrü", "Kültür, Antalya", 4.1f, 500),
        Restaurant(R.drawable.doner, "Dürümcü Bedir", "Konyaaltı, Antalya", 3.6f, 400),
        Restaurant(R.drawable.hamburgerr, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.hamburgerr, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.hamburgerr, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.hamburgerr, "Burger King", "Güvercin, Kültür", 4.7f, 150),
        Restaurant(R.drawable.hamburgerr, "Burger King", "Güvercin, Kültür", 4.7f, 150),
    )

    Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TopRestaurantsSection(restaurants = restaurants)
            }
        }
@Preview(showBackground = true)
@Composable
fun GreetingPreview4545() {
    Restorants()
}