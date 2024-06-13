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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.View.AuthPages.LoginPage
import com.example.fooddeliveryproject.View.AuthPages.RestaurantLoginPage
import com.example.fooddeliveryproject.View.AuthPages.RestaurantSignUpPage
import com.example.fooddeliveryproject.View.AuthPages.SignUpPage
import com.example.fooddeliveryproject.View.Pages.AccountPage
import com.example.fooddeliveryproject.View.Pages.AddressPage
import com.example.fooddeliveryproject.View.Pages.CampaignPage
import com.example.fooddeliveryproject.View.Pages.CartPage
import com.example.fooddeliveryproject.View.Pages.CategoriesPage
import com.example.fooddeliveryproject.View.Pages.MainPage
import com.example.fooddeliveryproject.View.Pages.OrderPage
import com.example.fooddeliveryproject.View.Pages.OrderStatusPage
import com.example.fooddeliveryproject.View.Pages.RestaurantPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAddProductPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantChangeRestaurantNamePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantPasswordChangePage
import com.example.fooddeliveryproject.ViewModel.AddressPageViewModel
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel

@Composable
fun RestaurantAppNavigation(authenticatorViewModel: AuthenticatorViewModel,restaurantViewModel: RestaurantViewModel ) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val addressVM:AddressPageViewModel= viewModel()


    Scaffold(
        bottomBar = {
            authenticatorViewModel.isRestaurantUser.value?.let {
                if (it) {
                    if (currentDestination != null) {
                        RestaurnatBottt(navController = navController, currentDestination = currentDestination)
                    }
                }else{
                    if (currentDestination != null) {
                        StoreBot(navController = navController, currentDestination = currentDestination)
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = StoreScreen.LoginScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = RestaurantScreen.RestaurantHomeScreen.name) {
                RestaurantHomePage(navController, restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantOrderedScreen.name) {
                RestaurantOrderPage()
            }
            composable(route = RestaurantScreen.RestaurantProfileScreen.name) {
                RestaurantAccountPage(
                    navController = navController,
                    viewModel = authenticatorViewModel
                )
            }
            composable(route = RestaurantScreen.RestaurantEditProductScreen.name) {
                RestaurantAddProductPage(navController, viewModel = restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantAddProductScreen.name) {
                RestaurantAddProductPage(navController, viewModel = restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantUpdatePasswordScreen.name) {
                RestaurantPasswordChangePage(navController)
            }
            composable(route = RestaurantScreen.RestaurantChangeRestaurantNameScreen.name) {
                RestaurantChangeRestaurantNamePage(navController)
            }
            composable(route = RestaurantScreen.RestaurantLoginScreen.name) {
                LoginPage(authVm = authenticatorViewModel, navHostController = navController)
            }

            composable(route = StoreScreen.LoginScreen.name) {
                LoginPage(navController,authenticatorViewModel)
            }
            composable(route = StoreScreen.HomeScreen.name) {
                MainPage(navController,addressVM)
            }
            composable(route = StoreScreen.OrderedScreen.name) {
                OrderPage()
            }
            composable(route = StoreScreen.CartScreen.name) {
                CartPage()
            }
            composable(route = StoreScreen.CampaignScreen.name) {
                CampaignPage()
            }
            composable(route = StoreScreen.ProfileScreen.name) {
                AccountPage(navController,authenticatorViewModel)
            }

            composable(route = StoreScreen.SignUpPage.name) {
                SignUpPage(navController,authenticatorViewModel)
            }
            composable(route=StoreScreen.RestaurantSignUpPage.name){
                RestaurantSignUpPage(navController,authenticatorViewModel)
            }
            composable(route=StoreScreen.RestaurantLoginScreen.name){
                RestaurantLoginPage(navController,authenticatorViewModel)
            }
            composable(route=StoreScreen.CategoryScreen.name){
                CategoriesPage(navController)
            }
            composable(route=StoreScreen.RestaurantScreen.name){
                RestaurantPage()
            }
            composable(route=StoreScreen.AddressScreen.name){
                AddressPage(navController,addressVM)
            }
            composable(route=StoreScreen.OrderStatusScreen.name){
                OrderStatusPage()
            }


        }
    }
}

//@Composable
//fun StoreAppNavigation() {
//    val navController: NavHostController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    val restaurantViewModel: RestaurantViewModel = viewModel()
//    val authenticatorViewModel: AuthenticatorViewModel = viewModel()
//    Scaffold(
//        bottomBar = {
//            if (currentDestination != null) {
//                StoreBot(navController = navController, currentDestination = currentDestination)
//            }
//        }
//    ) { paddingValues ->
//
//        NavHost(
//            navController = navController,
//            startDestination = StoreScreen.LoginScreen.name,
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            composable(route = StoreScreen.LoginScreen.name) {
//                LoginPage(navController,authenticatorViewModel)
//            }
//            composable(route = StoreScreen.HomeScreen.name) {
//                MainPage(navController)
//            }
//            composable(route = StoreScreen.OrderedScreen.name) {
//                OrderPage()
//            }
//            composable(route = StoreScreen.CartScreen.name) {
//                CartPage()
//            }
//            composable(route = StoreScreen.CampaignScreen.name) {
//                CampaignPage()
//            }
//            composable(route = StoreScreen.ProfileScreen.name) {
//                AccountPage(navController,authenticatorViewModel)
//            }
//
//            composable(route = StoreScreen.SignUpPage.name) {
//                SignUpPage(navController,authenticatorViewModel)
//            }
//            composable(route=StoreScreen.RestaurantSignUpPage.name){
//                RestaurantSignUpPage(navController,authenticatorViewModel)
//            }
//            composable(route=StoreScreen.RestaurantLoginScreen.name){
//                RestaurantLoginPage(navController,authenticatorViewModel)
//            }
//            composable(route=StoreScreen.CategoryScreen.name){
//                CategoriesPage(navController)
//            }
//            composable(route=StoreScreen.RestaurantScreen.name){
//                RestaurantPage()
//            }
//
//
//
//        }
//    }
//}
@Composable
fun StoreBot(navController: NavHostController ,currentDestination:NavDestination){
    val listOfStoreFullScreen = listOf(
        StoreScreen.HomeScreen.name,
        StoreScreen.CartScreen.name,
        StoreScreen.OrderedScreen.name,
        StoreScreen.CampaignScreen.name,
        StoreScreen.ProfileScreen.name
    )
    if (listOfStoreFullScreen.contains(currentDestination?.route)) {
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
}
@Composable
fun RestaurnatBottt(navController: NavHostController ,currentDestination:NavDestination){
    val listOfRestaurantFullScreen = listOf(
        RestaurantScreen.RestaurantAddProductScreen.name,
        RestaurantScreen.RestaurantChangeRestaurantNameScreen.name,
        RestaurantScreen.RestaurantUpdatePasswordScreen.name,
        RestaurantScreen.RestaurantLoginScreen.name
    )
    if (!listOfRestaurantFullScreen.contains(currentDestination?.route)) {
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