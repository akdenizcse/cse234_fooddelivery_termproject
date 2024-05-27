package com.example.fooddeliveryproject.Utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fooddeliveryproject.Models.RestaurantBottomNavItem

class RestaurantBottomNavItemList {
    companion object{


        val items = listOf(
            RestaurantBottomNavItem(
                title = "RestaurantHome",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false,
            ),
            RestaurantBottomNavItem(
                title = "RestaurantOrderPage",
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart,
                hasNews = false
            ),
            RestaurantBottomNavItem(
                title = "RestaurantAccount",
                selectedIcon = Icons.Filled.AccountCircle,
                unselectedIcon = Icons.Outlined.AccountCircle,
                hasNews = false,
            )
        )
    }
}