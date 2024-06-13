package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.UserViewModel


@Composable
fun OrderStatusPage(navHostController: NavHostController= rememberNavController(), userViewModel: UserViewModel= viewModel()) {
    userViewModel.getPreparedOrderList()
    val preparedList by userViewModel.preparedOrderList.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Aktif Siparişler",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFFF8742A)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                if (preparedList != null) {
                    items(preparedList!!) { category ->
                        ListItem(category)
                    }
                }

            }
        }
    )
}

@Composable
fun ListItem(orderStatus: OrderedFood) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
//            Image(
//                painter = painterResource(id = orderStatus.imageRes),
//                contentDescription = orderStatus.name,
//                modifier = Modifier.size(300.dp)
//            )

            downladImage(imageUrl = "https://firebasestorage.googleapis.com/v0/b/csefooddelivery.appspot.com/o/food%2Fimage_17177640854250?alt=media&token=ee8761cf-05d0-4886-bb7d-d145b883aa5b")
            Column(modifier = Modifier.padding(top = 25.dp)) {
                Text(
                    text = orderStatus.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = orderStatus.orderedDate!!,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = orderStatus.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.size(32.dp))
Row (){
    Image(
        painter = painterResource(id = R.drawable.baseline_access_time_24),
        contentDescription ="time",
        modifier = Modifier.size(30.dp)
    )
    Text(
        text ="Hazırlanıyor",
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        textAlign = TextAlign.Start
    )
}

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderStatusPAge() {
    OrderStatusPage()
}
