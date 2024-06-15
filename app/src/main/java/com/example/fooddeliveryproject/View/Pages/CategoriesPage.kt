package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen

data class Category(val imageRes: Int, val name: String)

@Composable
fun CategoriesPage(navHostController: NavHostController = rememberNavController()) {
    val categories = listOf(
        Category(R.drawable.doner, "Döner"),
        Category(R.drawable.hamburgerr, "Hamburger"),
        Category(R.drawable.pitza, "Pizza"),
        Category(R.drawable.iceceksoguk, "İçeçek"),
        Category(R.drawable.balik, "Balık"),
        Category(R.drawable.fastfood, "Fast Food"),
        Category(R.drawable.kahvaltilik, "Kahvaltılık"),
        Category(R.drawable.tatli, "Tatlı"),
        Category(R.drawable.yemekk, "Çorba"),
        Category(R.drawable.noodle, "Makarna"),
        Category(R.drawable.kahve, "Kahve"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kategoriler",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(StoreScreen.HomeScreen.name) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFFF8742A)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(category)
                }
            }
        }
    )
}

@Composable
fun CategoryItem(category: Category) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPagePreview() {
    CategoriesPage()
}
