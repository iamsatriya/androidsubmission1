package com.satriyawicaksana888.androidsubmission1.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String,
    val name: String,
    val location: String,
    val repository: String,
    val company: String,
    val followers: String,
    val following: String,
    val avatars: Int,
) : Parcelable
