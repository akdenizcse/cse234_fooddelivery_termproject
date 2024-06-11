package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.View.Pages.CampaignPage

data class BottomNavigationBar(val imageRes: Int, val title: String)
@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_home_24), contentDescription = null) },
            selected = false,
            label = { Text(text = "Anasayfa", fontSize = 10.sp) },
            onClick = { /* Handle click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_shopping_basket_24), contentDescription = null) },
            selected = false,
            label = { Text(text = "Sepetim", fontSize = 10.sp) },
            onClick = { /* Handle click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_card_giftcard_24), contentDescription = null) },
            selected = false,
            label = { Text(text = "Kampanyalar", fontSize = 10.sp) },
            onClick = { /* Handle click */ }
        )
        BottomNavigationItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_account_circle_24), contentDescription = null) },
            selected = false,
            label = { Text(text = "Hesabım", fontSize = 10.sp) },
            onClick = { /* Handle click */ }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreviw() {
    BottomNavigationBar()
}