package com.example.fooddeliveryproject.View.Search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.AuthPages.FadedTextComponent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun SearchResultPage(){
    Scaffold(
        topBar = {
        },
        content = { paddingValues ->


            SearchBar(modifier = Modifier.padding(paddingValues).fillMaxSize())


        }

    )
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {



    var searchText by remember { mutableStateOf(TextFieldValue("Çiğköfte")) }

    Column(
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.detailsPageColor),
                    //shape = MaterialTheme.shapes.medium,
                    shape = RoundedCornerShape(40.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            if (searchText.text.isEmpty()) {

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Search...",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {},
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.detailsPageColor))
                ){
                    androidx.compose.material3.Icon(
                        Icons.Outlined.Search,
                        contentDescription = "Artır",
                        tint = colorResource(id = R.color.orange)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }



        val numbers = listOf(1,2,3,4)
        LazyColumn(
            Modifier
                .fillMaxSize()
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
                                    .size(185.dp, 55.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column() {
                                        Text(
                                            text = "Komagene",
                                            //modifier = Modifier.align(Alignment.CenterVertically),
                                            color = colorResource(id = R.color.signUpBlack),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                            )
                                        )
                                        Text(
                                            text = "Konyaaltı, Antalya",
                                            //modifier = Modifier.align(Alignment.CenterVertically),
                                            color = colorResource(id = R.color.signUpBlack),
                                            style = TextStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Normal,
                                            )
                                        )
                                    }






                                }
                            }


                            Spacer(modifier = Modifier.width(18.dp))
                            Surface(
                                color = colorResource(id = R.color.white),
                                //shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(100.dp, 45.dp)
                                    //.fillMaxSize()
                                    .background(Color.White)
                                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 4.dp),

                                )
                            {
                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    Surface(
                                        color = colorResource(id = R.color.white),
                                        //shape = RoundedCornerShape(14.dp),
                                        modifier = Modifier
                                            .size(10.dp, 9.dp)
                                            .background(Color.White)

                                        ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.star4), // Replace with your image resource
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                //.size(200.dp)
                                                .padding(bottom = 1.dp)
                                        )

                                    }

                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(text = "4.1(500+ Yorum))",
                                        modifier = Modifier.fillMaxWidth(),
                                        color = colorResource(id = R.color.silikMetin),
                                        style = TextStyle(
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )

                                }


                            }



                        }

                        Surface(
                            color = colorResource(id = R.color.white),
                            //shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(100.dp, 45.dp)
                                //.fillMaxSize()
                                .background(Color.White)
                                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 4.dp),

                            ) {

                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {


                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Mega Çiğköfte",
                                    color = colorResource(id = R.color.black),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )

                            }

                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {


                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "110TL",
                                    color = colorResource(id = R.color.black),
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )

                            }


                        }


                    }
                }
            }
        }





    }
}


@Composable
fun eray(paddingValues: PaddingValues){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {





        Spacer(modifier = Modifier.height(250.dp))



        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Sonuç Bulunamadı",
            color = colorResource(id = R.color.signUpBlack),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
            )
        )

        Spacer(modifier = Modifier.height(10.dp))
        FadedTextComponent(param = "Yeniden Aramayı Deneyebilirsiniz")
    }

}


/*
@Composable
@Preview
fun NotFound(){

    var searchText by remember { mutableStateOf(TextFieldValue("Çiğköfte")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = colorResource(id = R.color.purple_200),
                                //shape = MaterialTheme.shapes.medium,
                                shape = RoundedCornerShape(40.dp)
                            )
                            .padding(top = 12.dp, bottom = 12.dp),
                    ) {
                        if (searchText.text.isEmpty()) {


                            Text(
                                modifier = Modifier.padding(start = 20.dp),
                                text = "Search...",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Spacer(modifier = Modifier.width(15.dp))
                            androidx.compose.material3.Icon(
                                Icons.Outlined.Search,
                                contentDescription = "Artır",
                                tint = colorResource(id = R.color.orange)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            BasicTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                    }
                },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                backgroundColor = colorResource(id = R.color.black),
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
            )
        }
    ) {

        eray(it)

    }

}

 */
