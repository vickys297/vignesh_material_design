package com.fromsimply.vignesh_material_design.data.Mapper

import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingUI
import com.fromsimply.vignesh_material_design.domian.entity.HoldingDto


object HoldingsMapper {

    fun fromEntityToUI(value: HoldingEntity): HoldingUI {
        return HoldingUI(
            symbol = value.symbol,
            quantity = value.quantity,
            ltp = value.ltp,
            avgPrice = value.avgPrice,
            close = value.close,
            totalPNL = ((value.ltp * value.quantity) - (value.avgPrice * value.quantity))
        )
    }

    fun fromDtoToEntity(value: HoldingDto): HoldingEntity {
        return HoldingEntity(
            symbol = value.symbol,
            quantity = value.quantity,
            ltp = value.ltp,
            avgPrice = value.avgPrice,
            close = value.close,
        )
    }
}