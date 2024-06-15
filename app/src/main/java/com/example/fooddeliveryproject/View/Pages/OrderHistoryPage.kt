package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R

data class OrderHistory(
    val imageRes: Int,
    val name: String,
    val tarih: String,
    val urun: String,
)

@Composable
fun OrderHistoryPage(navHostController: NavHostController = rememberNavController()) {
    val categories = listOf(
        OrderHistory(R.drawable.dukkan1, "Çorbacı Şükrü", "29-01-2024", "Mercimek Çorbası"),
        OrderHistory(R.drawable.dukkan6, "Hastayım Döner", "22-01-2023", "Tavuk Döner"),
        OrderHistory(R.drawable.dukkan12, "Burger King", "06-01-2023", "Double King Burger Menu"),
        OrderHistory(R.drawable.dukkan11, "Makarna Yıldızı", "17-08-2022", "Bol Kremalı Makarna"),
        OrderHistory(R.drawable.dukkan13, "Gogalo Döner", "14-05-2019", "Et Döner"),
        )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Geçmiş Siparişler",
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
                            tint = Color.White,
                                    modifier = Modifier
                                    .clickable {
                                navHostController.popBackStack()
                            }
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
                items(categories) { category ->
                    ListItem(category)
                }
            }
        }
    )
}

@Composable
fun ListItem(orderHistory: OrderHistory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = orderHistory.imageRes),
                contentDescription = orderHistory.name,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
    Column {
        Text(
            text = orderHistory.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
        Text(
            text = orderHistory.tarih,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = orderHistory.urun,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderHistoryPagePreview() {
    OrderHistoryPage()
}
