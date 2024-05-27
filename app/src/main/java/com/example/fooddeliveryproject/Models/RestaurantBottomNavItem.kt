package com.example.fooddeliveryproject.Models

import androidx.compose.ui.graphics.vector.ImageVector

data class RestaurantBottomNavItem(val title: String,
                                   val selectedIcon: ImageVector,
                                   val unselectedIcon: ImageVector,
                                   val hasNews: Boolean,
                                   val badgeCount: Int? = null)



