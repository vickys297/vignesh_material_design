package com.fromsimply.vignesh_material_design.domian.entity

data class HoldingResponseData(
    val data: UserHolding
) {
}

data class UserHolding(
    val userHolding: List<HoldingDto>
)