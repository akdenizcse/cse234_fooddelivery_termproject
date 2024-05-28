package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ui.theme.CardItemBg
import com.example.fooddeliveryproject.ui.theme.IconColor
import com.example.fooddeliveryproject.ui.theme.Orange500

@Composable
fun Header(){
    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        //modifier = Modifier.fillMaxWidth()
        ) {
        BoxWithResource(resId = R.drawable.location, description = "menu")
        Column {
            Row {
                Text(text = "EV")
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "down",
                    modifier = Modifier.size(16.dp),
                    tint = Orange500

                )

            }
            Text(text = "Kepez,Antalya", modifier = Modifier.fillMaxWidth(), color = Color.Gray)
        }




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
        ,contentAlignment = Alignment.CenterEnd
    )
    {
        Icon(

            painter = painterResource(id = resId),
            contentDescription = description,
            modifier = Modifier.size(iconSize!!.dp),
            tint = Orange500,



            )

    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview45() {
    Header()
}