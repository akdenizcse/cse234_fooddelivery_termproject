package com.example.fooddeliveryproject.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage

@Composable
fun RestaurantAppNavigation() {
    val navController:NavHostController= rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
               listOfNavItem.forEach{navItem->
                   NavigationBarItem(
                       selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                       onClick = {
                                 navController.navigate(navItem.route){
                                     popUpTo(navController.graph.findStartDestination().id){
                                         saveState = true
                                     }
                                     launchSingleTop = true
                                     restoreState=true
                                 }
                       },
                       icon = {
                              Icon(imageVector = navItem.icon, contentDescription =null )
                              },
                       label ={
                           Text(text = navItem.label)
                       } )

               }
            }
        }
    ) {paddingValues ->

        NavHost(navController = navController,
            startDestination =RestaurantScreen.HomeScreen.name,
            modifier =Modifier.padding(paddingValues) ){
            composable(route=RestaurantScreen.HomeScreen.name){
                RestaurantHomePage()
            }
            composable(route=RestaurantScreen.OrderedScreen.name){
                RestaurantOrderPage()
            }
            composable(route=RestaurantScreen.ProfileScreen.name){
                RestaurantAccountPage()
            }



        }
    }
}