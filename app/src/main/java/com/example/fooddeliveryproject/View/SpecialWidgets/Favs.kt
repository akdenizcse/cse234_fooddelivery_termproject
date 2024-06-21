package com.example.fooddeliveryproject.View.SpecialWidgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Utils.downladImage
import com.example.fooddeliveryproject.ViewModel.FoodViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen
import kotlinx.serialization.json.Json

@Composable
fun FavsItem(favs: Food,navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clickable {
                try {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "food",
                        value = favs
                    )
                    navHostController.navigate(StoreScreen.DetailsScreen.name)

                }catch (e:Exception){
                    Log.d("hatamFavsItem",e.toString())
                }
                    },
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        downladImage(imageUrl = favs.imageUrl, size = 100 )
        Column(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Text(
                text = favs.name,
                color = Color.DarkGray
            )
            Text(
                text = favs.description,
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
fun TopFavsSection(favs: ArrayList<Food>?,navHostController: NavHostController) {
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
            Text(
                text = "Tümünü Gör",
                style = MaterialTheme.typography.body1.copy(color = Color(0xFFF8742A)),
                modifier = Modifier.clickable {
                    navHostController.navigate(StoreScreen.SearchResultScreen.name)
                }
            )
        }
        LazyRow(
            modifier = Modifier.padding(1.dp)
        ) {
            if (favs != null) {
                items(favs.toList()) { favsall ->
                    FavsItem(favs = favsall,navHostController)
                }
            }
        }
    }
}

@Composable
fun Favs(navHostController: NavHostController ,foodViewModel: FoodViewModel,) {

    foodViewModel.getRandomFoodItems(5)
    val list by foodViewModel.foodList.observeAsState()
    TopFavsSection(favs = list ,navHostController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4545() {
    Favs(rememberNavController(), viewModel())
}
