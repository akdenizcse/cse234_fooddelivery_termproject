package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen
import com.example.fooddeliveryproject.ui.theme.CardItemBg
import com.example.fooddeliveryproject.ui.theme.IconColor
import com.example.fooddeliveryproject.ui.theme.Orange500

@Composable
fun Header(navController: NavHostController = rememberNavController(), addressTitle: String = "", addressDesc: String = "") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(1f)
                .clickable {
                    navController.navigate(StoreScreen.AddressScreen.name)
                }
        ) {
            BoxWithResource(resId = R.drawable.location, description = "menu")
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = addressTitle)
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = "down",
                        modifier = Modifier.size(16.dp),
                        tint = Orange500
                    )
                }
                Text(text = addressDesc, color = Color.Gray)
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
            contentDescription = "aktif",
            modifier = Modifier
                .clickable {
                    navController.navigate(StoreScreen.OrderStatusScreen.name)
                }
                .size(36.dp)
                .padding(start = 5.dp),
            tint = Orange500

        )
    }
}

@Composable
fun BoxWithResource(
    resId: Int,
    description: String,
    bgColor: Color? = CardItemBg,
    iconColor: Color? = IconColor,
    boxSize: Int? = 40,
    iconSize: Int? = 29
) {
    Box(
        modifier = Modifier
            .size(boxSize!!.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor!!)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = description,
            modifier = Modifier.size(iconSize!!.dp),
            tint = Orange500
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview45() {
    Header()
}
