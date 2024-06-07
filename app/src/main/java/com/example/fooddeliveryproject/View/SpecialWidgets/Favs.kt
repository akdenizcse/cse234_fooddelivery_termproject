package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R

data class Favs(
    val imageRes: Int,
    val name: String,
    val location: String,
)

@Composable
fun FavsItem(favs: Favs) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 6.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = favs.imageRes),
            contentDescription = favs.name,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .size(width = 128.dp, height = 128.dp)
        )
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                text = favs.name,
                color = Color.DarkGray
            )
            Text(
                text = favs.location,
                color = Color.Gray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
        }
    }
}

@Composable
fun TopFavsSection(favs: List<Favs>) {
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "En Beğenilen Yemekler",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        LazyRow(
            modifier = Modifier.padding(1.dp)
        ) {
            items(favs) { favsall ->
                FavsItem(favs = favsall)
            }
        }
    }
}

@Composable
fun Favs() {
    val favs = listOf(
        Favs(R.drawable.dukkan9, "Ev Yemeği Menü", "192 TL",),
        Favs(R.drawable.dukkan10, "Enginarlı Salata", "122 TL",),
        Favs(R.drawable.dukkan3, "Kahvaltı Menü", "142 TL",),
        Favs(R.drawable.dukkan11, "Burger King", "165 TL",),
        Favs(R.drawable.dukkan5, "Burger King", "45 TL",),
        Favs(R.drawable.dukkan6, "Burger King", "127 TL",),
        Favs(R.drawable.dukkan7, "Burger King", "69 TL",),
    )
    TopFavsSection(favs = favs)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4545() {
    Favs()
}
