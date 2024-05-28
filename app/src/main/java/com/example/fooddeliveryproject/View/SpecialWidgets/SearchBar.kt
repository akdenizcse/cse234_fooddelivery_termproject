package com.example.fooddeliveryproject.View.SpecialWidgets

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R

@Composable
fun SearchBarStyle(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Box (
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceEvenly,
            //modifier = Modifier.fillMaxWidth()

        ){
            Image(
                painterResource(R.drawable.search),
                contentDescription = "",
                Modifier.size(32.dp),
                colorFilter = ColorFilter.tint(Color.LightGray)
            )

            TextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                placeholder = { Text("Ürün Ara...") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.LightGray,
                )


            )
        }
    }


}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }
    Box (
    ) {
        Column(
        ) {
            SearchBarStyle(query) { newQuery ->
                query = newQuery
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview451() {
    SearchBar()
}
