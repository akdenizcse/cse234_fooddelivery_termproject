package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCardOrigin
import com.example.fooddeliveryproject.View.SpecialWidgets.CategoryCard
import com.example.fooddeliveryproject.View.SpecialWidgets.Header
import com.example.fooddeliveryproject.View.SpecialWidgets.Favs
import com.example.fooddeliveryproject.View.SpecialWidgets.Restorants
import com.example.fooddeliveryproject.View.SpecialWidgets.SearchBar

@Composable
fun MainPage() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        item {
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
            Spacer(modifier = Modifier.size(8.dp))
        }
        item {
            Favs()
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
