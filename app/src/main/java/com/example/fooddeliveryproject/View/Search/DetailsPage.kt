package com.example.fooddeliveryproject.View.Search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.Utils.getCurrentFormattedDate
import com.example.fooddeliveryproject.View.AuthPages.ButtonComponent
import com.example.fooddeliveryproject.View.AuthPages.QuantityComponent
import com.example.fooddeliveryproject.ViewModel.UserViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.Json


@Composable
@Preview
fun DetailsPage(food :Food ,navHostController: NavHostController,userVM:UserViewModel) {
   Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            //.padding(10.dp)

    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            IconButton(onClick = { navHostController.popBackStack()}) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_left),
                                    contentDescription = "Back",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = colorResource(id = R.color.detailsPageColor),
                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )
            }
        ) {
            Details(it,food = food,userVM,navHostController=navHostController)
        }



    }
}


@Composable
fun Details(paddingValues: PaddingValues,food:Food,userVM:UserViewModel,navHostController: NavHostController) {
    var count by remember {
        mutableStateOf(1)
    }
    val context= LocalContext.current
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            //.padding(20.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            Spacer(modifier = Modifier.height(120.dp))
            downladImage(imageUrl = food.imageUrl, size = 200)


            Spacer(modifier = Modifier.height(20.dp))
            Text(text = food.name,
                color = colorResource(id = R.color.signUpBlack),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )


            Spacer(modifier = Modifier.height(10.dp))
            Text(text = food.description,
                color = colorResource(id = R.color.signUpBlack),
                style = TextStyle(
                    fontSize = 14.sp,
                )
            )

            Spacer(modifier = Modifier.height(50.dp))
            
            Row (modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically)
            {
                Button(onClick = {
                                 if(count>1){
                                     count--
                                 }
                },
                    modifier = Modifier
                        .heightIn(48.dp),
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                ){
                    androidx.compose.material3.Icon(Icons.Outlined.Remove, contentDescription = "Artır")
                }
                Text(
                    text = count.toString(),
                    modifier = Modifier.padding(10.dp),
                    color = colorResource(id = R.color.signUpBlack),
                    style = TextStyle(
                        fontSize = 14.sp,
                    )
                )
                Button(onClick = {
                                 count++
                },
                    modifier = Modifier
                        .heightIn(48.dp),
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                ){
                    androidx.compose.material3.Icon(Icons.Outlined.Add, contentDescription = "Artır")
                }
                Text(
                    text = "${food.price} X $count = ${food.price * count}₺",
                    modifier = Modifier.padding(30.dp),
                    color = colorResource(id = R.color.signUpBlack),
                    style = TextStyle(
                        fontSize = 14.sp,
                    )
                )

            }

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    val date= getCurrentFormattedDate()
                          val orderFood= OrderedFood(food.id,food.name,food.description,food.imageUrl,food.price,food.category,count,date,false,FirebaseAuth.getInstance().uid)
                            userVM.addToCart(orderFood){
                                if(it){
                                    Toast.makeText(context, "Sepete Eklendi", Toast.LENGTH_SHORT).show()
                                    navHostController.navigate(StoreScreen.HomeScreen.name)
                                }
                            }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .heightIn(48.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                    contentAlignment = Alignment.Center
                ){

                    androidx.compose.material3.Text(text = "Sepete Ekle",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)

                }
            }



        }
    }

}