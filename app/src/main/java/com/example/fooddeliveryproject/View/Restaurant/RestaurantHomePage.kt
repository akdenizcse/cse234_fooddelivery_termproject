package com.example.fooddeliveryproject.View.Restaurant



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar


@Composable
@Preview
fun RestaurantHomePage(navHostController: NavHostController= rememberNavController()) {
    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.fork_and_spoon,"Food Delivery")
        }
    ){
        Surface(modifier = Modifier
            .padding(it)
            .background(Color.White), ) {
            val list= ArrayList<Food>()
            list.add(Food(1,"Kebap","100 gr etli kebap","100",1000,"Et"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            list.add(Food(1,"100","100","100",1000,"100"))
            RestaurantALlProducts(list)

        }

    }
}

@Composable
fun RestaurantALlProducts(list: ArrayList<Food>) {
    LazyColumn(Modifier.background(Color.White)) {
        items(list) { food ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),
                shape = RectangleShape,
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (modifier = Modifier.width(200.dp)){
                        Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = food.description, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row (horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically){
                            Text(text = "${food.price} TL", fontSize = 18.sp,fontWeight = FontWeight.Bold)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Button(
                                    onClick = { /* TODO: Implement add to cart logic */ },
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(30.dp)
                                ) {
                                    Text(text = "Sepete Ekle", fontSize = 12.sp, textAlign = TextAlign.Center)
                                }
                            }
                        }

                    }

                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(width = 0.1.dp, color = Color.LightGray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fork_and_spoon),
                            contentDescription = null

                        )
                    }
                }
            }
        }
    }
}