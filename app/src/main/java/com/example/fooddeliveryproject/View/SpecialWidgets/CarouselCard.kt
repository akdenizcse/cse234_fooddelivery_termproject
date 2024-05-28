package com.example.fooddeliveryproject.View.SpecialWidgets


import androidx.compose.foundation.Image
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
import com.example.fooddeliveryproject.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

data class CarouselItem(val imageRes: Int, val title: String)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselCard(items: List<CarouselItem>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = items.size,
        state = pagerState,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
    ) { page ->
        val item = items[page]
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .size(200.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            /*
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
            */
        }
    }
}
@Composable
fun CarouselCardOrigin (){
    Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val items = remember {
                    listOf(
                        CarouselItem(R.drawable.card_1, ""),
                        CarouselItem(R.drawable.card_2, ""),
                        CarouselItem(R.drawable.card_3, ""),
                        CarouselItem(R.drawable.card_4, ""),
                    )
                }
                CarouselCard(items = items)
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview485() {
    CarouselCardOrigin()
}