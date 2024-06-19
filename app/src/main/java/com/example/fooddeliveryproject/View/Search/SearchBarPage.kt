package com.example.fooddeliveryproject.View.Search

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryproject.R
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun SearchBarPage(){

    val text = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }
    var items = remember { mutableStateListOf(
        "Döner",
        "Kebap") }

    Row(modifier = androidx.compose.ui.Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween)
    {

        
        Scaffold {



            androidx.compose.material3.SearchBar(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                query = text.value,
                onQueryChange = {
                    text.value = it
                },
                onSearch = {
                    items.add(text.value)
                    active.value = false
                    text.value = ""
                },
                active = active.value,
                onActiveChange = {
                    active.value = it
                },
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if(active.value){
                        Icon(
                            modifier = androidx.compose.ui.Modifier.clickable {
                                if (text.value.isNotEmpty()) {
                                    text.value = ""
                                } else {
                                    active.value = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon"
                        )
                    }
                }
            ) {
                items.forEach{
                    Row(modifier = androidx.compose.ui.Modifier.padding(all = 14.dp))
                    {

                        Icon(
                            modifier = androidx.compose.ui.Modifier.padding(end = 5.dp)
                                .size(18.dp),
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "History Icon"
                        )
                        Text(text = it)
                    }
                }
            }
        }
    }

}



