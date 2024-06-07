package com.example.fooddeliveryproject.View.Restaurant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.example.fooddeliveryproject.ui.theme.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.AppBar
@Preview
@Composable
fun RestaurantOrderPage(){


    Scaffold(
        topBar = {
            AppBar(imageId = R.drawable.fork_and_spoon,"Orders")
        }

    ) {
        Surface(modifier = Modifier
            .padding(it)
            .background(Color.White)
        ){


            val list= ArrayList<Food>()
            list.add(Food("1","Kebap","100 gr etli kebap","100",1000,"Et",3))
            list.add(Food("2","100","100","100",1000,"100",2))
            list.add(Food("3","100","100","100",1000,"100"))
            list.add(Food("4","100","100","100",1000,"100"))
            list.add(Food("5","Kebap","100 gr etli kebap","100",1000,"Et",3))
            list.add(Food("6","100","100","100",1000,"100",2))
            list.add(Food("7","100","100","100",1000,"100"))
            val totalPrice = calculate(list)
            Box(modifier = Modifier.fillMaxHeight(.9f)){

                 OrderFoodListDesig(list = list)

                Box(
                    modifier = Modifier

                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(bottom = 10.dp).align(Alignment.BottomCenter)
                        .border(.75.dp, Color.LightGray, RectangleShape),

                    ) {
                    Text(text = "Toplam Gelir:", modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp, bottom = 10.dp, top = 20.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${totalPrice} TL", modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp, bottom = 10.dp, top = 20.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = orange )

                }
            }




        }


    }

}

@Composable
fun OrderFoodListDesig(list: ArrayList<Food>){
    var totalPrice=0



    LazyColumn(Modifier.background(Color.White).fillMaxHeight(.9f)) {
        items(list) { food ->
            val count=food.soldCount
            val price=food.price
            var total = 0
            if (count != null) {
                total =count*price
                totalPrice+=total
            }
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
                    Column (modifier = Modifier.width(230.dp)){
                        Text(text = food.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = food.description, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row (modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(text = "${food.soldCount} Adet", fontSize = 18.sp,fontWeight = FontWeight.Bold)
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {

                                Text(text = "${count} x ${price} = ${total} TL",fontSize = 15.sp, color = orange )
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

fun calculate(list:ArrayList<Food>):Int{
    var total=0
    for (food in list){
        if (food.soldCount!=null)
         total += (food.price*food.soldCount)
    }
    return total
}






