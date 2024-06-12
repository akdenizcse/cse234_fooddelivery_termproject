package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.AuthPages.FadedTextComponent
import com.example.fooddeliveryproject.View.AuthPages.HeadingTextComponent
import com.example.fooddeliveryproject.View.AuthPages.QuantityComponent
import com.example.fooddeliveryproject.View.AuthPages.QuantityComponentCartPage
import com.example.fooddeliveryproject.View.Search.Details

@Composable
@Preview
fun CartPage() {

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

                                IconButton(onClick = { /* Handle navigation */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_left),
                                        contentDescription = "Back",
                                        tint = Color.White,
                                        modifier = Modifier.size(30.dp),
                                    )
                                }

                                Spacer(modifier = Modifier.width(80.dp))
                                Text(text = "Sepet",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = colorResource(id = R.color.white),
                                    style = TextStyle(
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                            }
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth(),
                        backgroundColor = colorResource(id = R.color.orange),
                        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                    )
                }
            ) {


                //CartPageView(it)
                ExistCartView(it)
                //deneme(it)
                //Deneme2(paddingValues = it)


            }












    }

}


@Composable
fun CartPageView(paddingValues: PaddingValues){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(modifier = Modifier.height(200.dp))
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(id = R.drawable.vector),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Sepetin Boş",
            color = colorResource(id = R.color.signUpBlack),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))
        FadedTextComponent(param = "Hemen yemeklere gözat  ve sipariş ver!")
    }

}

@Composable
fun Deneme2(paddingValues: PaddingValues){

    val numbers = listOf(1,2,3,4)

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        //.padding(20.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize(),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                //Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ürünler",
                    modifier = Modifier.padding(20.dp),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorResource(id = R.color.silikMetin)
                )

                Spacer(modifier = Modifier.width(105.dp))
                Text(text = "Fiyat",
                    modifier = Modifier.padding(20.dp),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorResource(id = R.color.silikMetin)
                )

                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Adet",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorResource(id = R.color.silikMetin)
                )
            }

            Surface(
                color = Color.Black,
                //shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp, 3.dp)
                    //.fillMaxSize()
                    .background(Color.White)
                    .padding(start = 5.dp, end = 5.dp),

            ) {}


            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                color = Color.Black,
                //shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxSize()
                    //.size(100.dp, 3.dp)
                    //.fillMaxSize()
                    .background(Color.White)
                    //.padding(start = 5.dp, end = 5.dp),

            ) {


                LazyColumn(
                    Modifier
                        .background(color = colorResource(id = R.color.googleColor)) //renk
                        //.border(BorderStroke(1.dp, Color.Gray))
                ) {
                    items(numbers){number->

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            //.padding(16.dp)
                            .padding(top = 5.dp, bottom = 5.dp),
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Image(
                                        modifier = Modifier
                                            .padding(top = 1.dp, bottom = 1.dp)
                                            .size(70.dp)
                                            .clip(RoundedCornerShape(20.dp)),
                                        painter = painterResource(id = R.drawable.dukkan13),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))
                                    Surface(color = Color.White,  //Tavuk Tantuni renk
                                        modifier = Modifier
                                            .size(125.dp, 55.dp)
                                    ){
                                        Row(modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically)
                                        {
                                            Text(text = "Tavuk Tantuni Dürüm",
                                                modifier = Modifier.align(Alignment.CenterVertically),
                                                color = colorResource(id = R.color.signUpBlack),
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                )
                                            )
                                        }

                                    }

                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(text = "$number TL")


                                    QuantityComponentCartPage()
                                }

                            }


                        }
                    }
                }





            }














        }

    }

}




@Composable
fun ExistCartView(paddingValues: PaddingValues) {

    val numbers = listOf(1, 2, 3, 4)
    val totalPrice = numbers.sumBy { it }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column {
                Text(
                    text = "Toplam Tutar: $totalPrice TL",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorResource(id = R.color.signUpBlack)
                )
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
                ) {
                    Text(
                        text = "Sipariş Ver",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        },
        content = { innerPadding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Ürünler",
                            modifier = Modifier.padding(20.dp),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorResource(id = R.color.silikMetin)
                        )

                        Spacer(modifier = Modifier.width(105.dp))
                        Text(
                            text = "Fiyat",
                            modifier = Modifier.padding(20.dp),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorResource(id = R.color.silikMetin)
                        )

                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Adet",
                            modifier = Modifier.padding(16.dp),
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorResource(id = R.color.silikMetin)
                        )
                    }

                    Surface(
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Color.White)
                            .padding(start = 5.dp, end = 5.dp),
                    ) {}

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                    ) {
                        LazyColumn(
                            Modifier
                                .background(color = colorResource(id = R.color.googleColor))
                        ) {
                            items(numbers) { number ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(110.dp)
                                        .padding(top = 5.dp, bottom = 5.dp),
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Image(
                                                modifier = Modifier
                                                    .padding(top = 1.dp, bottom = 1.dp)
                                                    .size(70.dp)
                                                    .clip(RoundedCornerShape(20.dp)),
                                                painter = painterResource(id = R.drawable.dukkan13),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop
                                            )

                                            Spacer(modifier = Modifier.width(10.dp))
                                            Surface(
                                                color = Color.White,
                                                modifier = Modifier
                                                    .size(125.dp, 55.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = "Tavuk Tantuni Dürüm",
                                                        modifier = Modifier.align(Alignment.CenterVertically),
                                                        color = colorResource(id = R.color.signUpBlack),
                                                        style = TextStyle(
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.Bold,
                                                        )
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(15.dp))
                                            Text(text = "${number} TL")

                                            QuantityComponentCartPage()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
