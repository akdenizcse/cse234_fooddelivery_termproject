package com.example.fooddeliveryproject.View.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.fooddeliveryproject.View.AuthPages.ButtonComponent
import com.example.fooddeliveryproject.View.AuthPages.QuantityComponent


@Composable
@Preview
fun DetailsPage(){
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
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(15.dp))

                            Image(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .padding(2.dp),
                                painter = painterResource(id = R.drawable.dukkan13),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                            Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {

                                Text(text = "Komagene",
                                    color = colorResource(id = R.color.signUpBlack),
                                    style = TextStyle(
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )

                                Text(text = "Konyaaltı, Antalya",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = colorResource(id = R.color.signUpBlack),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                    )
                                )
                            }

                        }
                    },
                    modifier = Modifier.height(80.dp)
                        .fillMaxWidth(),
                    backgroundColor = colorResource(id = R.color.detailsPageColor),
                    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
                )
            }
        ) {
            Details(it)
        }



    }
}


@Composable
fun Details(paddingValues: PaddingValues){

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
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(20.dp)),
                painter = painterResource(id = R.drawable.dukkan13),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )


            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Komagene",
                color = colorResource(id = R.color.signUpBlack),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )


            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "125 gr. çiğ köfte, çift lavaş,5 çeşit garnitür",
                color = colorResource(id = R.color.signUpBlack),
                style = TextStyle(
                    fontSize = 14.sp,
                )
            )

            Spacer(modifier = Modifier.height(50.dp))
            QuantityComponent()



            Spacer(modifier = Modifier.height(170.dp))
            ButtonComponent(param = "Sepete Ekle")




        }
    }

}