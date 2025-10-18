package com.fromsimply.vignesh_material_design.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "USER_HOLDINGS")
data class HoldingEntity(
    @PrimaryKey val symbol: String, val quantity: Int, val ltp: Double, val avgPrice: Double, val close: Double
)

data class HoldingUI(
    val symbol: String, val quantity: Int, val ltp: Double, val avgPrice: Double, val close: Double
)