package com.example.fooddeliveryproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class RestaurantNavItem(
    val label:String,
    val icon: ImageVector,
    val route:String
)

val listOfNavItem = listOf(
    RestaurantNavItem(label = "Ana Sayfa", icon = Icons.Default.Home, route = RestaurantScreen.HomeScreen.name),
    RestaurantNavItem(label = "Siparişler", icon = Icons.Default.ShoppingCart, route = RestaurantScreen.OrderedScreen.name),
    RestaurantNavItem(label = "Profil", icon = Icons.Default.Person, route = RestaurantScreen.ProfileScreen.name)
)