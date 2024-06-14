package com.example.fooddeliveryproject.View.Restaurant
import com.example.fooddeliveryproject.ui.theme.*
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.RestaurantViewModel
import com.example.fooddeliveryproject.navigation.RestaurantScreen

@Preview
@Composable
fun RestaurantAccountPage(navController: NavHostController,viewModel: AuthenticatorViewModel,restaurantViewModel: RestaurantViewModel) {
   restaurantViewModel.getRestaurantInfo()
    Scaffold(
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Column(verticalArrangement = Arrangement.Top , modifier = Modifier.padding(top = 140.dp)) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
//                    Image(painter = painterResource(id = R.drawable.fork_and_spoon), contentDescription = "profile photo", modifier = Modifier.size(150.dp).background(Color.White, CircleShape))
                    restaurantViewModel.restaurant.value?.let { it1 -> downladImage(imageUrl = it1.imageUrl) }
                }
                Divider(modifier = Modifier.padding(top = 50.dp),color = Color.White)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                    .clickable {
                        navController.navigate(RestaurantScreen.RestaurantChangeRestaurantNameScreen.name)
                    }) {
                    Image(painter = painterResource(id = R.drawable.profile_photo), contentDescription = "profile photo", modifier = Modifier
                        .size(70.dp)
                        .background(Color.White, CircleShape))
                    Spacer(modifier = Modifier.size(30.dp))
                    Text(text = "Restoran Adını Değiştir", modifier = Modifier
                        .align(Alignment.CenterVertically), fontSize = 18.sp,         fontFamily = FontFamily(
                        Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)
                    ) )
                    Text(text = ">", modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 30.dp), fontSize = 18.sp )
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp), color = Color.LightGray)


                Divider(modifier = Modifier.padding(top = 50.dp), color = Color.White)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
                    .clickable {
                        navController.navigate(RestaurantScreen.RestaurantUpdatePasswordScreen.name)
                    }) {
                    Image(painter = painterResource(id = R.drawable.password_photo), contentDescription = "profile photo", modifier = Modifier
                        .size(70.dp)
                        )
                    Spacer(modifier = Modifier.size(30.dp))
                    Text(text = "Şifreni Değiştir", modifier = Modifier
                        .align(Alignment.CenterVertically), fontSize = 18.sp ,         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                    Text(text = ">", modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 95.dp), fontSize = 18.sp )
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp), color = Color.LightGray)

                Button(onClick = {
                                 viewModel.signOut()
                                 navController.navigate(RestaurantScreen.RestaurantLoginScreen.name)
                                 }, modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.CenterHorizontally)
                    , colors = ButtonDefaults.buttonColors(
                        orange) ) {
                    Text(text = "Çıkış Yap",         fontFamily = FontFamily(Font(R.font.popins_regular, style = FontStyle.Normal, weight = FontWeight.Normal)))
                }



            }
        }

    }

}