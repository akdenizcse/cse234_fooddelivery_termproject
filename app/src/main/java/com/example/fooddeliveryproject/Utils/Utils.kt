package com.example.fooddeliveryproject.Utils

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.StorageReference

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