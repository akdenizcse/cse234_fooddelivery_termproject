@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fooddeliveryproject.View.Pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.View.SpecialWidgets.CarouselCardOrigin
import com.example.fooddeliveryproject.View.SpecialWidgets.CategoryCard
import com.example.fooddeliveryproject.View.SpecialWidgets.Header
import com.example.fooddeliveryproject.View.SpecialWidgets.Favs
import com.example.fooddeliveryproject.View.SpecialWidgets.Restorants
import com.example.fooddeliveryproject.View.SpecialWidgets.SearchBarView
import com.example.fooddeliveryproject.ViewModel.AddressPageViewModel
import com.example.fooddeliveryproject.ViewModel.FoodViewModel
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
fun MainPage(navHostController: NavHostController=rememberNavController(),addressPageViewModel: AddressPageViewModel= viewModel(),foodViewModel: FoodViewModel= viewModel(),restaurantViewModel: RestaurantViewModel= viewModel()) {
    Scaffold(
        topBar = {
            Header(navController = navHostController,addressPageViewModel.addressTitle,addressPageViewModel.addressDesc)
        }
    ) { innerPadding ->
        SearchBarView(navHostController)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            item {
                Divider(modifier = Modifier.padding(40.dp))
                CarouselCardOrigin(navHostController)
            }
            item {
                CategoryCard(navHostController)
                Spacer(modifier = Modifier.size(8.dp))
            }
            item {
                Favs(navHostController=navHostController, foodViewModel =foodViewModel)
            }
            item {
                Restorants(navHostController, restaurantViewModel )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MainPage()
}
