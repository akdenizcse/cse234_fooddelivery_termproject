package com.example.fooddeliveryproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfRestaurantNavItem = listOf(
    NavItem(
        label = "Ana Sayfa",
        icon = Icons.Default.Home,
        route = RestaurantScreen.RestaurantHomeScreen.name
    ),
    NavItem(
        label = "Siparişler",
        icon = Icons.Default.ShoppingCart,
        route = RestaurantScreen.RestaurantOrderedScreen.name
    ),
    NavItem(
        label = "Ürün Ekle",
        icon = Icons.Default.Add,
        route = RestaurantScreen.RestaurantAddProductScreen.name
    ),
    NavItem(
        label = "Profil",
        icon = Icons.Default.Person,
        route = RestaurantScreen.RestaurantProfileScreen.name
    )
)


val listOfStoreNavItem = listOf(
    NavItem(
        label = "Ana Sayfa",
        icon = Icons.Default.Home,
        route = StoreScreen.HomeScreen.name),
    NavItem(
        label = "Sepetim",
        icon = Icons.Default.Person,
        route = StoreScreen.OrderedScreen.name
    ),
    NavItem(
        label = "Kampanyalar",//Sembolü değiştir
        icon =  Icons.Default.Person,
        route = StoreScreen.CampaignScreen.name
    ),
    NavItem(
        label = "Hesabım",
        icon = Icons.Default.Person,
        route = StoreScreen.ProfileScreen.name)
)
