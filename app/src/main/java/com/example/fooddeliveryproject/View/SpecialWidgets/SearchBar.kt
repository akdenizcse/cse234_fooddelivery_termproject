package com.example.fooddeliveryproject.View.SpecialWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R

@Composable
fun SearchBarStyle(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceEvenly,
        //modifier = Modifier.fillMaxWidth()
    ){
        BoxWithResource(resId = R.drawable.search , description ="search")
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search..") }
        )
    }

}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp, top = 20.dp, end = 17.dp)
            )
            {
                Header()
            }

        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                SearchBarStyle(query) { newQuery ->
                    query = newQuery
                }
            }
        }
    )
}

