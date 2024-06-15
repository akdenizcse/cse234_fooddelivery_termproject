package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
@Preview(showBackground = true)
fun OrderConfirmPage(navHostController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.navigate(StoreScreen.HomeScreen.name)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left), // Replace with your back icon resource
                            contentDescription = null
                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        }
    ) {

        OrderConfirm(paddingValues = it)

    }
}


@Composable
fun OrderConfirm(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.confirm), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom=16.dp)
        )

        Text(
            text = "Siparişiniz alındı!",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Siparişiniz hazırlandıktan sonra size ulaştırılacak!",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }
}


        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
        Image(
                painter = painterResource(id = R.drawable.csefoodicon), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Siparişiniz alındı!",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Siparişiniz hazırlandıktan sonra size ulaştırılacak!",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center
            )
         */