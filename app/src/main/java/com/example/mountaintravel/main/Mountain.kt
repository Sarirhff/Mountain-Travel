package com.example.mountaintravel.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mountain(
    val name : String,
    val image : Int,

):Parcelable
