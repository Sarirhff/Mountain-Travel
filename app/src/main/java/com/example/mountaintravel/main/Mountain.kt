package com.example.mountaintravel.main

import android.os.Parcel
import android.os.Parcelable

data class Mountain(
    val name : String,
    val image : Int,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mountain> {
        override fun createFromParcel(parcel: Parcel): Mountain {
            return Mountain(parcel)
        }

        override fun newArray(size: Int): Array<Mountain?> {
            return arrayOfNulls(size)
        }
    }
}
