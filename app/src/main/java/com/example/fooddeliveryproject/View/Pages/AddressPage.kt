package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddeliveryproject.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold

@Composable
fun AddressPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /* Handle navigation */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "Back",
                                tint = Color.Black,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(R.drawable.baseline_home_24),
                            contentDescription = "Home",
                            tint = Color(0xFFF8742A),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Adres Seçiniz",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        }
    ) {
        AddressForm(it)
    }
}

@Composable
fun AddressForm(paddingValues: PaddingValues) {
    var addressName by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = addressName,
            onValueChange = { addressName = it },
            label = { Text("Adres Adı (Ev, iş vb.)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row( modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(
        value = city,
        onValueChange = { city = it },
        label = { Text("İl") },
        placeholder = { Text("İl Seçin") },
        )
            DropdownMenu()
        }


        Row( modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("İlçe") },
                placeholder = { Text("İlçe Seçin") },
            )
            DropdownMenu()
        }
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Adres") },
            placeholder = { Text("Adresinizi girin") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { /* Handle save action */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF8742A)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Adresi Kaydet", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun DropdownMenu(){
        val countryList = listOf("", "Antalya", "Bursa", "Edirne", "İstanbul")

        var control by remember { mutableStateOf(value = false) }

        var countryIndex by remember { mutableStateOf(value = 0) }

                Box {
                        // Seçilen ülkenin adını gösteren metin
                        Text(text = countryList[countryIndex])
                        // Dropdown menü simgesi
                        Image(
                            painter = painterResource(id = R.drawable.arrow_down),
                            contentDescription = "Aç/Kapat",
                            modifier = Modifier.size(32.dp)

                        )


                    // Dropdown menü
                    if (control) {
                        DropdownMenu(
                            expanded = control,
                            onDismissRequest = { control = false }
                        ) {
                            // Ülkeler listesini her bir öğeye dönüştür
                            countryList.forEachIndexed { index, country ->
                                DropdownMenuItem(
                                    onClick = {
                                        // Seçilen ülkeyi güncelle
                                        countryIndex = index

                                        // Dropdown menüyü kapat
                                        control = false
                                    },
                                    content = { Text(text = country) }
                                )
                            }
                        }
                    }
                }
            }








@Preview(showBackground = true)
@Composable
fun AddressPagePreview() {
    AddressPage()
}
