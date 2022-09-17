package com.project.hiltmvvmapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutrition(
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double
) : Parcelable
