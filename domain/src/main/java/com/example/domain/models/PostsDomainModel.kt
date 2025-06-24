package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PostDomainModel(
    val userId: Int,
    val id:Int,
    val title: String,
    val body: String
): Parcelable
