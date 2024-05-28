package com.example.fooddeliveryproject.View.MainPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCard
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCardOrigin
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselItem
import com.example.fooddeliveryproject.View.SpecialWidgets.CategoryCard
import com.example.fooddeliveryproject.View.SpecialWidgets.Header
import com.example.fooddeliveryproject.View.SpecialWidgets.SearchBar



@Composable
fun MainPage() {
    Column (Modifier.fillMaxSize()){
            Header()
            SearchBar()
            CarouselCardOrigin()
            CategoryCard()
    }

}






    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview3() {
     MainPage()

}