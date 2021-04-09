package com.amrabdelhameed.photoweatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import androidx.core.content.FileProvider
import com.amrabdelhameed.photoweatherapp.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun getAddress(context: Context?, latitude: Double, longitude: Double): String {
    val geoCoder = Geocoder(context, Locale.getDefault())
    val addresses = geoCoder.getFromLocation(latitude, longitude, 1)
    return addresses[0].getAddressLine(0)
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("hh:mm EEE, MMM dd")
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
fun getFileName(): String = SimpleDateFormat("yyyyMMddHHmmss'.png'").format(Date())

fun shareClick(context: Context, imagePath: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "image/*"
    val myPhotoFileUri = FileProvider.getUriForFile(
        context,
        context.applicationContext.packageName + ".provider",
        File(imagePath)
    )
    intent.putExtra(Intent.EXTRA_STREAM, myPhotoFileUri)
    val chooser = Intent.createChooser(intent, context.getString(R.string.share_via))
    context.startActivity(chooser)
}