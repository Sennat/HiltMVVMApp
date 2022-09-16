package com.project.hiltmvvmapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrition(
    val carbohydrates: Int,
    val protein: Int,
    val fat: Double,
    val calories: Int,
    val sugar: Double
) : Parcelable
