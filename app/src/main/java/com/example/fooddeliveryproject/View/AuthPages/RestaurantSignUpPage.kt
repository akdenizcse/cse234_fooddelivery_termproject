package com.example.fooddeliveryproject.View.AuthPages

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fooddeliveryproject.R
import com.example.fooddeliveryproject.ViewModel.AuthenticatorViewModel
import com.example.fooddeliveryproject.ViewModel.UserViewModel
import com.example.fooddeliveryproject.navigation.RestaurantScreen
import com.example.fooddeliveryproject.navigation.StoreScreen
@Preview
@Composable
fun RestaurantSignUpPage(navHostController: NavHostController,authVM:AuthenticatorViewModel,userVM:UserViewModel){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        val context = LocalContext.current

        val name = remember {
            mutableStateOf("")
        }
        val surname = remember {
            mutableStateOf("")
        }
        val restaurantName = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }
        val email = remember {
            mutableStateOf("")
        }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val bitmap = remember { mutableStateOf<Bitmap?>(null) }

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }


        }
        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
            }


        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {


            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White),

            ) {
                if (bitmap.value == null){
                    Image(
                        painter = painterResource(id = R.drawable.resim_yukle2),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                            .clickable {
                            launcher.launch("image/*")
                        }
                    )
                }else{
                Image(
                    bitmap=bitmap.value!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.clickable {
                        launcher.launch("image/*")
                    }

                )
                }
            }



            HeadingTextComponent(param = "Restoran Hesabı Oluştur")
            Spacer(modifier = Modifier.height(15.dp))
            NormalTextComponent(param = "Uygulamaya kaydolmak için bilgilerini gir")

            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(param = "İsim"){
                name.value=it
            }
            Spacer(modifier = Modifier.height(7.dp))
            MyTextField(param = "Soyisim"){
                surname.value=it
            }
            Spacer(modifier = Modifier.height(7.dp))
            MyTextField(param = "Restoran İsmi"){
                restaurantName.value=it
            }
            Spacer(modifier = Modifier.height(7.dp))
            MyTextField(param = "Email"){
                email.value=it
            }
            Spacer(modifier = Modifier.height(7.dp))
            PasswordTextField(param = "Şifre"){
                password.value=it
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Hesap oluşturuluyor", Toast.LENGTH_SHORT).show()
                         if (email.value.isNotEmpty() && password.value.isNotEmpty() && restaurantName.value.isNotEmpty() && surname.value.isNotEmpty() && restaurantName.value.isNotEmpty() && imageUri != null) {
                                authVM.createRestaurant(email.value, password.value,name.value, imageUri = imageUri!!){
                                    if (it){
                                        userVM.setUserType(isRestaurant = true)
                                        Toast.makeText(context, "Hesap oluşturuldu", Toast.LENGTH_SHORT).show()
                                        navHostController.navigate(RestaurantScreen.RestaurantHomeScreen.name)
                                    }else{
                                        Toast.makeText(context, "Hesap oluşturulamadı", Toast.LENGTH_SHORT).show()
                                    }
                                }
                         }else{
                             Toast.makeText(context, "Eksik yerleri doldurun", Toast.LENGTH_SHORT).show()
                         }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                    contentAlignment = Alignment.Center
                ){

                    Text(text = "Kaydol",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)

                }

            }




            Spacer(modifier = Modifier.height(10.dp))
            FadedTextComponent(param = "Kaydol’a tıklayarak CSEFOOD Hizmet Şartlarımızı ve Gizlilik Politikamızı kabul etmiş olursunuz.")
            Spacer(modifier = Modifier.height(30.dp))
            ClickableTextComponent(param1 = "Hesabın var mı? ", param2 = "Giriş Yap",StoreScreen.LoginScreen.name,navHostController)

            
            
            
        }
        
        }

}



