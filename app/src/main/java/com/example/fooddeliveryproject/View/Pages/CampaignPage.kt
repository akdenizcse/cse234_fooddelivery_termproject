package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
fun CampaignPage(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Kampanyalar",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFFF8742A),
                elevation =1000.dp
            )
        }
    ) { paddingValues ->
        CampaignList(paddingValues,navHostController)
    }
}

@Composable
fun CampaignList(paddingValues: PaddingValues,navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CampaignItem(R.drawable.card_1,navHostController,"algida")
        CampaignItem(R.drawable.card_3,navHostController,"pizza")
        CampaignItem(R.drawable.card_4,navHostController,"pizza")
        CampaignItem(R.drawable.card_2,navHostController,"burger")
    }
}

@Composable
fun CampaignItem(imageRes: Int,navHostController: NavHostController,campaignTitle:String) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        navHostController.navigate(StoreScreen.SearchResultScreen.name + "/${campaignTitle}")

                    }
            )
        }
    }

//@Preview(showBackground = true)
//@Composable
//fun CampaignPagePreview() {
//    CampaignPage(navHostController = rememberNavController())
//}
