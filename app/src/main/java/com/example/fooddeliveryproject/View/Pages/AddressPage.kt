package com.example.fooddeliveryproject.View.Pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.Models.District
import com.example.fooddeliveryproject.Models.Province
import com.example.fooddeliveryproject.ViewModel.AddressPageViewModel

@Composable
fun AddressPage(
    navigate: NavHostController = rememberNavController(),
    addressVM: AddressPageViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            navigate.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_left),
                                contentDescription = "Back",
                                tint = Color.Black,
                                modifier = Modifier.size(48.dp).clickable {
                                    navigate.popBackStack()
                                }
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
                elevation = 16.dp
            )
        }
    ) {
        AddressForm(it, addressVM = addressVM,navigate = navigate)
    }
}

@Composable
fun AddressForm(paddingValues: PaddingValues, addressVM: AddressPageViewModel,navigate: NavHostController) {
    var addressName by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf<Province?>(null) }
    var district by remember { mutableStateOf("") }

    addressVM.getProvinceList()
    val provinceList by addressVM.provinceList.observeAsState()
    var districtList = city?.districts

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
        Spacer(modifier = Modifier.size(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "İL")
            Spacer(modifier = Modifier.size(8.dp))
            if (provinceList != null) {
                DropdownMenuCity(provinceList?.data) { selectedProvince ->
                    city = selectedProvince
                    district = ""
                    districtList?.clear()
                    districtList=selectedProvince.districts
                    Log.d("hatam2", "AddressForm: $districtList $city")
                }
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "İLÇE")
            Spacer(modifier = Modifier.size(8.dp))
            DropdownMenuDistrict(districtList) {
                district = it
            }
            Log.d("hatam", "AddressForm: $districtList")
//            if (districtList != null && districtList.isNotEmpty()) {
//
//            } else {
//                Text(text = "İlçeler Bulunamadı")
//            }
        }

        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Adres") },
            placeholder = { Text("Adresinizi girin") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                addressVM.addressTitle = addressName.text
                addressVM.addressDesc = (city?.name ?: "") +" / " + district
                navigate.popBackStack()
            },
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
fun DropdownMenuCity(provinceList: List<Province>?, callback: (Province) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.clickable { expanded = true }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "Aç/Kapat",
                modifier = Modifier.size(24.dp)
            )
            if (provinceList != null && provinceList.isNotEmpty()) {
                Text(text = provinceList[selectedIndex].name)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            provinceList?.forEachIndexed { index, province ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        callback(province)
                        expanded = false
                    },
                    content = { Text(text = province.name) }
                )
            }
        }
    }
}

@Composable
fun DropdownMenuDistrict(districtList: List<District>?, callback: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.clickable { expanded = true }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "Aç/Kapat",
                modifier = Modifier.size(24.dp)
            )
            if (districtList != null && districtList.isNotEmpty()) {
                Text(text = districtList[selectedIndex].name)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            districtList?.forEachIndexed { index, district ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        callback(district.name)
                        expanded = false
                    },
                    content = { Text(text = district.name) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressPagePreview() {
    AddressPage()
}
