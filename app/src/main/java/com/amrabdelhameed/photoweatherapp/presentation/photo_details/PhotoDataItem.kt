package com.amrabdelhameed.photoweatherapp.presentation.photo_details

import android.os.Parcel
import android.os.Parcelable

class PhotoDataItem(
    var id: Int,
    var path: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoDataItem> {
        override fun createFromParcel(parcel: Parcel): PhotoDataItem {
            return PhotoDataItem(parcel)
        }

        override fun newArray(size: Int): Array<PhotoDataItem?> {
            return arrayOfNulls(size)
        }
    }
}