package com.example.fooddeliveryproject

import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fooddeliveryproject.Utils.RestaurantBottomNavItemList
import com.example.fooddeliveryproject.Utils.restaurantBottomBar
import com.example.fooddeliveryproject.Utils.salesBottomBar
import com.example.fooddeliveryproject.View.Restaurant.RestaurantAccountPage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantHomePage
import com.example.fooddeliveryproject.View.Restaurant.RestaurantOrderPage
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ui.theme.CardItemBg
import com.example.fooddeliveryproject.ui.theme.FoodDeliveryProjectTheme
import com.example.fooddeliveryproject.ui.theme.IconColor
import com.example.fooddeliveryproject.ui.theme.Orange500
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() ,SearchView.OnQueryTextListener{
    lateinit var authVM:AuthenticatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        var isRestaurant : Boolean = true
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryProjectTheme {

                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        bottomBar = {
                            if(isRestaurant)
                            {
                                restaurantBottomBar()
                            }
                            else
                            {
                                restaurantBottomBar()
                            }
                        }
                    ) {
                        it
                        RestaurantOrderPage()
                    }




                    val temp : AuthenticatorViewModel by viewModels()
                    authVM = temp
                }

            }
        }


    }




    @Composable
    fun NavigationRestaurant(){

    }




    @Composable
    fun HomeScreen() {
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, top = 40.dp, end = 17.dp)
            )
            {
                Header()

                Spacer(modifier = Modifier.size(30.dp))
                Text(text = "Kategoriler")
            }
        }

    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        HomeScreen()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

}