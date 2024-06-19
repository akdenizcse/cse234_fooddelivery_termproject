@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.fooddeliveryproject.View.Search

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.View.AuthPages.FadedTextComponent
import com.example.fooddeliveryproject.ViewModel.FoodViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun SearchResultPage(
    navHostController: NavHostController,
    foodVM: FoodViewModel= viewModel(),
    search:String?=null){
    Scaffold(
        topBar = {
        },
        content = { paddingValues ->

            FoodListPage(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),navHostController,foodVM,search)


        }

    )
}


@Composable
fun FoodListPage(modifier: Modifier = Modifier,  navHostController: NavHostController, foodVM: FoodViewModel, serach:String?=null) {
    val context= LocalContext.current
    val foodList by foodVM.foodList.observeAsState()
    if(serach!=null){
        foodVM.searchFood(serach!!)
    }else{
        foodVM.getFoodList()
    }


    var searchText by remember { mutableStateOf("") }
    var onActive by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        androidx.compose.material3.SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            query = searchText ,
            onQueryChange = {
                            searchText = it
            },
            onSearch = {
                try {
                    onActive=false
                    Log.e( "hatam21","dsa"+searchText)
                    if (searchText!=null || searchText !="" ) {
                        navHostController.navigate(StoreScreen.SearchResultScreen.name + "/$searchText")
                    }else{
                        navHostController.navigate(StoreScreen.SearchResultScreen.name)
                    }
                }catch (e:Exception) {
                    navHostController.navigate(StoreScreen.SearchResultScreen.name)

                }

            },
            active =onActive ,
            onActiveChange ={
                onActive=it
            },
            placeholder = {
                Text(text = if(serach!=null)"$serach" else "Ürün Arayın ...")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription ="Ara" )
            },
            trailingIcon = {
                if(onActive){
                    Icon(imageVector = Icons.Default.Clear,
                        contentDescription ="" ,
                        modifier=Modifier.clickable {
                            if(searchText.isNotEmpty()){
                    searchText=""
                }else{
                    onActive=false
                }
            })

                }
            }
        ) {


        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    color = colorResource(id = R.color.detailsPageColor),
//                    //shape = MaterialTheme.shapes.medium,
//                    shape = RoundedCornerShape(40.dp)
//                )
//                .padding(horizontal = 16.dp, vertical = 12.dp),
//        ) {
//            if (searchText.text.isEmpty()) {
//
//                Text(
//                    modifier = Modifier.padding(start = 20.dp),
//                    text = "Search...",
//                    color = Color.Gray,
//                    fontSize = 16.sp
//                )
//            }
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Button(onClick = {},
//                    modifier = Modifier
//                        .width(16.dp)
//                        .height(16.dp),
//                    contentPadding = PaddingValues(),
//                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.detailsPageColor))
//                ){
//                    androidx.compose.material3.Icon(
//                        Icons.Outlined.Search,
//                        contentDescription = "Ara",
//                        tint = colorResource(id = R.color.orange)
//                    )
//                }
//                Spacer(modifier = Modifier.width(6.dp))
//                BasicTextField(
//                    value = searchText,
//                    onValueChange = { searchText = it },
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textStyle = TextStyle(
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Normal
//                    )
//                )
//            }
//        }

        if(foodList!=null){
            if(foodList!!.size>0){
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .background(color = colorResource(id = R.color.googleColor))
                ) {
                    items(foodList!!) { food ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
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

                                    Box(modifier = Modifier
                                        .fillParentMaxHeight()
                                        .padding(start = 10.dp), contentAlignment = Alignment.Center){

                                        downladImage(imageUrl = food.imageUrl, size =80)
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))
                                    Surface(
                                        color = Color.White,
                                        modifier = Modifier
                                            .size(160.dp, 55.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column() {
                                                Text(
                                                    text = food.name,
                                                    //modifier = Modifier.align(Alignment.CenterVertically),
                                                    color = colorResource(id = R.color.signUpBlack),
                                                    style = TextStyle(
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.Bold,
                                                    )
                                                )
                                                Text(
                                                    text = food.description,
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

                                    Spacer(modifier = Modifier.width(10.dp))
                                    Surface(
                                        color = colorResource(id = R.color.white),
                                        //shape = RoundedCornerShape(14.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxSize()
                                            .background(Color.White)
                                            .padding(
                                                start = 5.dp,
                                                end = 5.dp,
                                                bottom = 5.dp,
                                                top = 4.dp
                                            ),

                                        )
                                    {


                                            Spacer(modifier = Modifier.width(2.dp))
                                            Column(modifier = Modifier
                                                .fillMaxWidth()
                                                .height(30.dp), verticalArrangement = Arrangement.Center) {
                                                Row() {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.star4), // Replace with your image resource
                                                        contentDescription = null,
                                                        modifier = Modifier
//                                                            .fillMaxSize()
                                                            .size(18.dp)
                                                            .padding(end = 5.dp)
                                                    )

                                                    Text(text =food.rating.toString() + " ( ${food.ratingCount}+ Yorum)",
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(top = 2.dp),
                                                        color = colorResource(id = R.color.silikMetin),
                                                        style = TextStyle(
                                                            fontSize = 11.sp,
                                                            fontWeight = FontWeight.Normal
                                                        )
                                                    )
                                                }

                                            }
                                            Box(contentAlignment = Alignment.BottomCenter) {

                                                Text(text = food.price.toString()+" TL",
                                                    color = colorResource(id = R.color.black),
                                                    style = TextStyle(
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.Normal
                                                    ),
                                                        modifier=Modifier.padding(bottom = 15.dp, end = 10.dp)
                                                )
                                            }




                                    }



                                }

//                                Surface(
//                                    color = colorResource(id = R.color.white),
//                                    //shape = RoundedCornerShape(14.dp),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .size(100.dp, 45.dp)
//                                        //.fillMaxSize()
//                                        .background(Color.White)
//                                        .padding(
//                                            start = 5.dp,
//                                            end = 5.dp,
//                                            bottom = 5.dp,
//                                            top = 4.dp
//                                        ),
//
//                                    ) {
////
////                                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
////
////                                        Spacer(modifier = Modifier.width(5.dp))
////                                        Text(text = food.price.toString()+" TL",
////                                            color = colorResource(id = R.color.black),
////                                            style = TextStyle(
////                                                fontSize = 14.sp,
////                                                fontWeight = FontWeight.Normal
////                                            ),
//////                                            modifier=Modifier.padding(bottom = 10.dp, end = 10.dp)
////                                        )
////
////                                    }
//
//
//                                }


                            }
                        }
                    }
                }
            }else{
                Toast.makeText(context,"Ürün bulunamadı",Toast.LENGTH_SHORT).show()
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
