package com.example.fooddeliveryproject.Utils

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.fooddeliveryproject.R
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun uploadImage(imageUri: Uri, folderName: String,storageReference: StorageReference, calllback: (String) -> Unit) {
    val imageNamePrefix = "image_${System.currentTimeMillis()}"
    var uploadedCount = 0
    val imageName = "$imageNamePrefix${uploadedCount++}"
    val imageRef: StorageReference = storageReference.child("$folderName/$imageName")

    if (imageUri != null) {
        imageRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri->
                    calllback(uri.toString())
                }
            }
            .addOnFailureListener { exception ->
                calllback("error")
                Log.e("hatamVM", "error= "+exception.toString())
                exception.printStackTrace()
            }
    }
}
@Composable
fun downladImage(imageUrl:String,size:Int=150) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.foodplaceholder),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(size.dp)
    )
}
fun getCurrentFormattedDate(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

