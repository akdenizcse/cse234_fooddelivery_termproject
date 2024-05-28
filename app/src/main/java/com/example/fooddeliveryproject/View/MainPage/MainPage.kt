package com.example.fooddeliveryproject.View.MainPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCard
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCardOrigin
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselItem
import com.example.fooddeliveryproject.View.SpecialWidgets.CategoryCard
import com.example.fooddeliveryproject.View.SpecialWidgets.Header
import com.example.fooddeliveryproject.View.SpecialWidgets.Restaurant
import com.example.fooddeliveryproject.View.SpecialWidgets.Restorants
import com.example.fooddeliveryproject.View.SpecialWidgets.SearchBar
import com.example.fooddeliveryproject.View.SpecialWidgets.TopRestaurantsSection

@Composable
fun MainPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Optional: space between items
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Header()
        }
        item {
            SearchBar()
        }
        item {
            CarouselCardOrigin()
        }
        item {
            CategoryCard()
        }

        item {
            Restorants()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MainPage()
}
