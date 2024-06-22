package com.example.fooddeliveryproject.View.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.navigation.StoreScreen

@Composable
fun AccountPage(navigate:NavHostController= rememberNavController(),authVM:AuthenticatorViewModel= viewModel()) {
    Scaffold(

        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_account_circle_24), // Replace with your avatar resource ID
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        AccountOptionItem(
                            iconResId = R.drawable.baseline_access_time_24,
                            text = "Geçmiş Siparişlerim",
                            navigate = navigate
                        )
                        Divider(color = Color.Gray, thickness = 1.dp)
                        AccountOptionItem(
                            iconResId = R.drawable.password_photo,
                            text = "Şifreyi Değiştir",
                            navigate=navigate
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(
                            onClick = {
                                      authVM.signOut()
                                      navigate.navigate(StoreScreen.LoginScreen.name)
                                      },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF8742A)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 48.dp)
                        ) {
                            Text(
                                text = "Çıkış Yap",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun AccountOptionItem(iconResId: Int, text: String,navigate: NavHostController ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                if(text=="Geçmiş Siparişlerim"){
                    navigate.navigate(StoreScreen.OrderStatusScreen.name)
                }else{

                }
            })
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
@Preview(showBackground = true)
@Composable
fun AccountPagePreview() {
    AccountPage()
}
