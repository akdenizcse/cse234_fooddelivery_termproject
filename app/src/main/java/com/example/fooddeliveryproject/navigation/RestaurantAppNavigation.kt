package com.example.fooddeliveryproject.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.AuthPages.LoginPage
import com.example.fooddeliveryproject.View.AuthPages.RestaurantLoginPage
import com.example.fooddeliveryproject.View.AuthPages.RestaurantSignUpPage
import com.example.fooddeliveryproject.View.AuthPages.SignUpPage
import com.example.fooddeliveryproject.View.Pages.AccountChangePassword
import com.example.fooddeliveryproject.View.Pages.AccountPage
import com.example.fooddeliveryproject.View.Pages.AddressPage
import com.example.fooddeliveryproject.View.Pages.CampaignPage
import com.example.fooddeliveryproject.View.Pages.CartPage
import com.example.fooddeliveryproject.View.Pages.CategoriesPage
import com.example.fooddeliveryproject.View.Pages.MainPage
import com.example.fooddeliveryproject.View.Pages.OrderConfirmPage
import com.example.fooddeliveryproject.View.Pages.OrderHistoryPage
import com.example.fooddeliveryproject.View.Pages.OrderPage
import com.example.fooddeliveryproject.View.Pages.OrderStatusPage
import com.example.fooddeliveryproject.View.Pages.RestaurantPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAddProductPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantChangeRestaurantNamePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantPasswordChangePage
import com.example.fooddeliveryproject.View.Search.DetailsPage
import com.example.fooddeliveryproject.View.Search.SearchResultPage
import com.example.fooddeliveryproject.ViewModel.AddressPageViewModel
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.FoodViewModel
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.ViewModel.UserViewModel

@Composable
fun RestaurantAppNavigation( ) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val addressVM:AddressPageViewModel= viewModel()
    val userVM:UserViewModel= viewModel()
    val authenticatorViewModel: AuthenticatorViewModel = viewModel()
    val restaurantViewModel: RestaurantViewModel= viewModel()
    val foodViewModel:FoodViewModel= viewModel()


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
                RestaurantOrderPage(navController,restaurantViewModel)
            }
            composable(route = RestaurantScreen.RestaurantProfileScreen.name) {
                RestaurantAccountPage(
                    navController = navController,
                    viewModel = authenticatorViewModel,
                    restaurantViewModel=restaurantViewModel
                )
            }
            composable(route = RestaurantScreen.RestaurantEditProductScreen.name ) {
                val result = navController.previousBackStackEntry?.savedStateHandle?.get<Food>(key = "editedFood")
                if(result!=null){
                    RestaurantAddProductPage(navController, viewModel = restaurantViewModel, food = result,true)
                }

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
                MainPage(navController,addressVM, foodViewModel = foodViewModel,restaurantViewModel=restaurantViewModel)
            }
            composable(route = StoreScreen.OrderedScreen.name) {
                OrderPage()
            }
            composable(route = StoreScreen.CartScreen.name) {
                CartPage(navController,userVM)
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
                RestaurantPage(navigate = navController, restaurantVM = restaurantViewModel)
            }
            composable(route=StoreScreen.AddressScreen.name){
                AddressPage(navController,addressVM)
            }
            composable(route=StoreScreen.OrderStatusScreen.name){
//                OrderStatusPage()
                OrderHistoryPage(navHostController = navController,  userViewModel = userVM)
            }
            composable(route=StoreScreen.OrderConfirmScreen.name){
                OrderConfirmPage(navController)
            }
            composable(route=StoreScreen.SearchResultScreen.name){
                SearchResultPage(navController, foodVM =foodViewModel)
            }
            composable(route = StoreScreen.SearchResultScreen.name + "/{search}") { backStackEntry ->
                SearchResultPage(
                    navHostController = navController,
                    foodVM = foodViewModel,
                    search = backStackEntry.arguments?.getString("search")
                )
            }
            composable(
                route = StoreScreen.SearchResultScreen.name + "?search={search}&isRestaurantProduct={isRestaurantProduct}",
                arguments = listOf(
                    navArgument("search") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("isRestaurantProduct") {
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { backStackEntry ->
                SearchResultPage(
                    navHostController = navController,
                    foodVM = foodViewModel,
                    search = backStackEntry.arguments?.getString("search") ?: "",
                    isRestaurantProduct = backStackEntry.arguments?.getBoolean("isRestaurantProduct") ?: false
                )
            }

            composable(StoreScreen.DetailsScreen.name){

                val result = navController.previousBackStackEntry?.savedStateHandle?.get<Food>(key = "food")
                if(result!=null){
                    DetailsPage(food = result, navHostController = navController, userVM = userVM)
                }

            }
            composable(route=StoreScreen.ChangePassword.name){
                AccountChangePassword(navHostController = navController, authViewModel =authenticatorViewModel )
            }

        }
    }
}

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
        NavigationBar(
            containerColor = colorResource(id = R.color.white),
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF3D8BFF), Color(0xFF7BAAFF))
                    )
                )
        )
        {
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
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.orange),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = colorResource(id = R.color.orange),
                        unselectedTextColor = Color.Gray
                    )



                )
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
        RestaurantScreen.RestaurantLoginScreen.name,
        StoreScreen.LoginScreen.name
    )
    if (!listOfRestaurantFullScreen.contains(currentDestination?.route)) {
        NavigationBar(
            containerColor = colorResource(id = R.color.white),
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF3D8BFF), Color(0xFF7BAAFF))
                    )
                )
        )
        {
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
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(id = R.color.orange),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = colorResource(id = R.color.orange),
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    }
}