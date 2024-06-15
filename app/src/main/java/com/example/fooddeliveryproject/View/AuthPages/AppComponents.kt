package com.example.fooddeliveryproject.View.AuthPages

import android.util.Log
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryproject.R



@Composable
fun HeadingTextComponent(param: String){
    Text(
        text = param,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal

        )
        ,color = colorResource(id = R.color.signUpBlack),
        textAlign = TextAlign.Center

    )
    //Image(painter = painterResource(id = R.drawable.), contentDescription = "Descriptipn")
}



@Composable
fun NormalTextComponent(param: String){
    Text(
        text = param,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal

        )
        ,color = colorResource(id = R.color.signUpBlack),
        textAlign = TextAlign.Center

    )
}


@Composable
fun FadedTextComponent(param: String){
    Text(
        text = param,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal

        )
        ,color = colorResource(id = R.color.silikMetin),
        textAlign = TextAlign.Center

    )
}

@Composable
fun DividerTextComponent(text : String){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){

        Divider(modifier = Modifier
            .weight(1f),
            color = colorResource(id = R.color.silikMetin),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            modifier = Modifier
                .heightIn(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal

            )
            ,color = colorResource(id = R.color.silikMetin),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(8.dp))

        Divider(modifier = Modifier
            .weight(1f),
            color = colorResource(id = R.color.silikMetin),
            thickness = 1.dp
        )

    }
}




@OptIn(ExperimentalMaterial3Api::class)  //?
@Composable
fun MyTextField(param: String,callback: (String) -> Unit){

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraSmall),
        label = { Text(text = param) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //containerColor = colorResource(id = R.color.backgroundField),
            focusedBorderColor = Color.Blue,
            focusedLabelColor = Color.Blue,
            cursorColor = Color.Blue,
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            callback(it)
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)  //?
@Composable
fun PasswordTextField(param: String,callback : (String) -> Unit){

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraSmall),
        label = { Text(text = param) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //containerColor = colorResource(id = R.color.backgroundField),
            focusedBorderColor = Color.Blue,
            focusedLabelColor = Color.Blue,
            cursorColor = Color.Blue,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        value = password.value,
        onValueChange = {
            password.value = it

            callback(it)
        },

        trailingIcon = {

            val iconImage = if(passwordVisible.value) {
                Image(painter = painterResource(id = R.drawable.visible), contentDescription = "Hide")
            } else{
                Image(painter = painterResource(id = R.drawable.not_visible), contentDescription = "Unhide")
            }

            Icon(painter = painterResource(id = R.drawable.visible), contentDescription = "", modifier = Modifier.clickable {
                passwordVisible.value = !passwordVisible.value
            })
        }

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMyTextField(param: String, focusRequester: FocusRequester, onImeAction: () -> Unit, callback: (String) -> Unit) {
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.extraSmall)
            .focusRequester(focusRequester),
        label = { Text(text = param) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //containerColor = colorResource(id = R.color.backgroundField),
            focusedBorderColor = Color.Blue,
            focusedLabelColor = Color.Blue,
            cursorColor = Color.Blue,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onImeAction()
            }
        ),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            callback(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPasswordTextField(
    param: String,
    focusRequester: FocusRequester,
    onImeAction: () -> Unit,
    callback: (String) -> Unit
) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    // Klavye işlemleri için kullanacağımız KeyboardController
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .focusRequester(focusRequester),
        label = { Text(text = param) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            focusedLabelColor = Color.Blue,
            cursorColor = Color.Blue,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        value = password.value,
        onValueChange = {
            password.value = it
            callback(it)
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visible)
            } else {
                painterResource(id = R.drawable.not_visible)
            }
            IconButton(
                onClick = {
                    passwordVisible.value = !passwordVisible.value
                },
                modifier = Modifier
            ) {
                Icon(painter = iconImage, contentDescription = "Şifreyi göster/gizle")
            }
        }
    )
}






@Composable
fun ClickableTextComponent(param1: String, param2: String, destination: String="", navHostController: NavHostController= rememberNavController()){

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
        Text(
            text = param1,
            modifier = Modifier
                .heightIn(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal

            )
            ,color = colorResource(id = R.color.signUpBlack),
            textAlign = TextAlign.Center
        )

        Text(
            text = param2,
            modifier = Modifier
                .heightIn()
                .clickable {
                    navHostController.navigate(destination)
                },
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal

            )
            ,color = colorResource(id = R.color.orange),
            textAlign = TextAlign.Center
        )

    }

}



@Composable
fun ButtonComponent(param: String, ){

    Button(
        onClick = { /*TODO*/ },
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

                Text(text = param,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)

            }

        }


}


@Composable
fun ButtonComponentGoogle(param: String, ){

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.googleColor))
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
            contentAlignment = Alignment.Center
        ){

            Text(text = param,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

        }

    }
}



@Composable
fun QuantityComponent(){

    Row (modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically)
    {

        Button(onClick = {},
            modifier = Modifier
                .heightIn(48.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
        ){
            Icon(Icons.Outlined.Add, contentDescription = "Artır")
        }


        Text(
            text = "1",
            modifier = Modifier.padding(10.dp),
            color = colorResource(id = R.color.signUpBlack),
            style = TextStyle(
                fontSize = 14.sp,
                //fontWeight = FontWeight.Bold,
            )
        )

        Button(onClick = {},
            modifier = Modifier
                .heightIn(48.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
        ){
            Icon(Icons.Outlined.Add, contentDescription = "Artır")
        }


        Text(
            text = "189TL",
            modifier = Modifier.padding(30.dp),
            color = colorResource(id = R.color.signUpBlack),
            style = TextStyle(
                fontSize = 14.sp,
                //fontWeight = FontWeight.Bold,
            )
        )

    }

}

@Composable
fun QuantityComponentCartPage(quantity: Int , isIncrease : (Boolean) -> Unit){
    val count by remember {
        mutableStateOf(quantity)
    }

    Surface(
        color = Color.White,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .size(140.dp, 80.dp)
            //.fillMaxSize()
            .background(Color.White)
            .padding(20.dp)

    ) {
        Row(
            modifier = Modifier.background(colorResource(id = R.color.orange)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            )
        {

            Button(onClick = {
                             isIncrease(false)
            },
                modifier = Modifier
                    .width(36.dp)
                    .height(46.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ){
                Icon(painterResource(id = R.drawable.minus), contentDescription = "Azalt")
            }


            Text(
                text = "$quantity" ,
                modifier = Modifier.padding(10.dp),
                color = colorResource(id = R.color.signUpBlack),
                style = TextStyle(
                    fontSize = 14.sp,
                    //fontWeight = FontWeight.Bold,
                )
            )

            Button(onClick = {
                           isIncrease(true)
            },
                modifier = Modifier
                    .width(36.dp)
                    .height(46.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
            ){
                Icon(Icons.Outlined.Add, contentDescription = "Artır")
            }

        }

    }



}













