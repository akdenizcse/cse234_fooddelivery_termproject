package com.example.fooddeliveryproject.View.SpecialWidgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ui.theme.orange

data class Category(val imageRes: Int, val title: String)

@Composable
fun CategoryCard(category: Category) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFF1F1F1),
        modifier = Modifier
            .padding(8.dp)
            .size(120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 1.dp)
            )
            Text(
                text = category.title,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun CategoriesSection(categories: List<Category>) {
    Column(

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Aklında Ne Var?",
                color = Color.DarkGray,
                fontSize = 18.sp
            )
            Text(
                text = "Tümünü Gör",
                color = orange ,
                fontSize = 16.sp
            )
        }
        LazyRow(
        ) {
            items(categories) { category ->
                CategoryCard(category = category)
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun CategoryCard() {
    val categories = listOf(
        Category(R.drawable.doner, "Döner"),
        Category(R.drawable.hamburgerr, "Hamburger"),
        Category(R.drawable.pitza, "Pizza"),
        Category(R.drawable.iceceksoguk,"İçeçek"),
        Category(R.drawable.balik, "Balık"),
        Category(R.drawable.fastfood, "Fast Food"),
        Category(R.drawable.kahvaltilik, "Kahvaltılık"),
        Category(R.drawable.tatli, "Tatlı"),
        Category(R.drawable.yemekk, "Çorba"),
        Category(R.drawable.noodle, "Makarna"),
        Category(R.drawable.kahve, "Kahve"),

    )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CategoriesSection(categories = categories)
            }
        }
@Preview(showBackground = true)
@Composable
fun GreetingPreview455() {
    CategoryCard()
}