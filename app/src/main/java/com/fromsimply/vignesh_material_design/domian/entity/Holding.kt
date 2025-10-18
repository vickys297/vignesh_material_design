package com.fromsimply.vignesh_material_design.domian.entity


data class HoldingDto(
    val symbol: String, val quantity: Int, val ltp: Double, val avgPrice: Double, val close: Double
)