package com.example.fooddeliveryproject.Utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage

@Composable
fun restaurantBottomBar() {
    val navController= rememberNavController()
    val contex= LocalContext.current.applicationContext
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val items=RestaurantBottomNavItemList.items
    BottomNavigation {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, restaurantBottomNavItem ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { /*TODO*/
                    selectedItemIndex = index
                    if (currentRoute != restaurantBottomNavItem.title){
                        navController.navigate(restaurantBottomNavItem.title)
                    }



                },
                icon = {
                    Icon(
                        imageVector =  if (index== selectedItemIndex){
                            restaurantBottomNavItem.selectedIcon
                        }else{
                            restaurantBottomNavItem.unselectedIcon
                        },
                        contentDescription = restaurantBottomNavItem.title
                    )
                }
            )

        }
    }


}



fun salesBottomBar() {
    // TODO
}