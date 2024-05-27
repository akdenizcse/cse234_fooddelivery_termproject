package com.example.fooddeliveryproject.Utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R

@Composable
fun AppBar(imageId: Int,title:String) {
    TopAppBar(backgroundColor = Color.White , contentColor = Color.Black , elevation = 5.dp){
        Row(horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(15.dp))
            Icon(painter = painterResource(id = imageId) , contentDescription ="",
                modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(25.dp))
            Text(text = title, fontSize = 20.sp)
        }
    }
}
