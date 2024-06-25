package com.example.fooddeliveryproject.View.SpecialWidgets


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

data class CarouselItem(val imageRes: Int, val title: String)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard(items: List<CarouselItem>,navHostController: NavHostController) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = items.size,
        state = pagerState,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
    ) { page ->
        val item = items[page]
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .size(200.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navHostController.navigate(StoreScreen.SearchResultScreen.name + "/${item.title}")
                    }
            )
        }
    }
}
@Composable
fun CarouselCardOrigin (navHostController: NavHostController){
    Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val items = remember {
                    listOf(
                        CarouselItem(R.drawable.card_1, "Hamburger"),
                        CarouselItem(R.drawable.card_2, "Hamburge"),
                        CarouselItem(R.drawable.card_3, "Pizza"),
                        CarouselItem(R.drawable.card_4, "Pizza"),
                    )
                }
                CarouselCard(items = items,navHostController)
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview485() {
    CarouselCardOrigin(rememberNavController())
}