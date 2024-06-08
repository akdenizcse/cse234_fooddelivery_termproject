package com.example.fooddeliveryproject.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.View.LoginPage
import com.example.fooddeliveryproject.View.Pages.CampaignPage
import com.example.fooddeliveryproject.View.Pages.CartPage
import com.example.fooddeliveryproject.View.Pages.MainPage
import com.example.fooddeliveryproject.View.Pages.OrderPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAddProductPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantChangeRestaurantNamePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantPasswordChangePage
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel

@Composable
fun RestaurantAppNavigation() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val restaurantViewModel:RestaurantViewModel= viewModel()
    // Log the current route for debugging
    Log.d("hatamNavigation", "Current route: ${currentDestination?.route}")

    Scaffold(
        bottomBar = {
            // Conditionally show the bottom bar
            if (currentDestination?.route != RestaurantScreen.RestaurantAddProductScreen.name) {
                NavigationBar {
                    listOfRestaurantNavItem.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(imageVector = navItem.icon, contentDescription = null)
                            },
                            label = {
                                Text(text = navItem.label)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = RestaurantScreen.RestaurantHomeScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = RestaurantScreen.RestaurantHomeScreen.name) {
                RestaurantHomePage(navController, viewModel = restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantOrderedScreen.name) {
                RestaurantOrderPage()
            }
            composable(route = RestaurantScreen.RestaurantProfileScreen.name) {
                RestaurantAccountPage()
            }
            composable(route = RestaurantScreen.RestaurantEditProductScreen.name) {
                RestaurantAddProductPage(navController,viewModel = restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantAddProductScreen.name) {
                RestaurantAddProductPage(navController,viewModel = restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantUpdatePasswordScreen.name) {
                RestaurantPasswordChangePage()
            }
            composable(route = RestaurantScreen.RestaurantChangeRestaurantNameScreen.name) {
                RestaurantChangeRestaurantNamePage()
            }
        }
    }
}

@Composable
fun StoreAppNavigation() {
    val navController:NavHostController= rememberNavController()
    Scaffold(
        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOfStoreNavItem.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navItem.label)
                        })

                }
            }

        }
    ) {paddingValues ->

        NavHost(navController = navController,
            startDestination =StoreScreen.LoginScreen.name,
            modifier =Modifier.padding(paddingValues) ){
            composable(route=StoreScreen.LoginScreen.name){
                LoginPage()
            }
            composable(route=StoreScreen.HomeScreen.name){
                MainPage()
            }
            composable(route=StoreScreen.OrderedScreen.name){
                OrderPage()
            }
            composable(route=StoreScreen.CartScreen.name){
                CartPage()
            }
            composable(route=StoreScreen.CampaignScreen.name){
                CampaignPage()
            }


        }
    }
}