@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fooddeliveryproject.View.SpecialWidgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
fun ModernSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text("Ürün Ara...", color = Color.Gray, fontSize = 16.sp) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SearchBarView(navHostController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var onActive by remember {
        mutableStateOf(false)
    }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        query = searchText ,
        onQueryChange = {
            searchText = it
        },
        onSearch = {
            try {
                onActive=false
                Log.e( "hatam212","dsaActive"+searchText)
                if (searchText!=null || searchText !="" ) {
                    navHostController.navigate(StoreScreen.SearchResultScreen.name + "/$searchText")
                }else{
                    navHostController.navigate(StoreScreen.SearchResultScreen.name)
                }
            }catch (e:Exception) {
                Log.d("hatam213","hataOnSearch : "+e.toString())
                navHostController.navigate(StoreScreen.SearchResultScreen.name)
            }
        },
        active =onActive ,
        onActiveChange ={
            onActive=it
        },
        placeholder = {
            Text(text = "Ürün Arayın ...")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription ="Ara" )
        },trailingIcon = {
            if(onActive){
                Icon(imageVector = Icons.Default.Close,
                    contentDescription ="" ,
                    modifier=Modifier.clickable {
                        if(searchText.isNotEmpty()){
                            searchText=""
                        }else{
                            onActive=false
                        }
                    })

            }
        }
    ) {
        Log.d("hatam214","dsa"+searchText)
    }

}




//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview() {
//    SearchBar()
//}
