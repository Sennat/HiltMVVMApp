package com.project.hiltmvvmapp.model

import android.os.Parcelable
import com.project.hiltmvvmapp.model.Nutrition
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val genus: String,
    val name: String,
    val family: String,
    val order: String,
    val nutritions: Nutrition
) : Parcelable
