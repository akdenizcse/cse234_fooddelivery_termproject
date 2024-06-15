package com.example.fooddeliveryproject

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.View.Pages.CartPage
import com.example.fooddeliveryproject.View.Pages.MainPage
import com.example.fooddeliveryproject.View.Search.SearchBarPage
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.RestaurantAppNavigation

import com.example.fooddeliveryproject.ui.theme.FoodDeliveryProjectTheme

class MainActivity : ComponentActivity() ,SearchView.OnQueryTextListener{
    val authenticatorViewModel: AuthenticatorViewModel by viewModels()
    val restaurantViewModel: RestaurantViewModel by viewModels()
    var isRestaurantUser: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryProjectTheme {

                RestaurantAppNavigation(authenticatorViewModel =authenticatorViewModel , restaurantViewModel = restaurantViewModel)

                 
            }
        }
    }











    @Composable
fun ActivityWe() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, top = 20.dp, end = 17.dp)
        )
        {
            MainPage()
        }




}


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
    ActivityWe()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}