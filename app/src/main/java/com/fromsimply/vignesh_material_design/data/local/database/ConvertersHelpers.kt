package com.fromsimply.vignesh_material_design.data.local.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@ProvidedTypeConverter
class ConvertersHelpers() {


    private val gson = Gson()

    @TypeConverter
    fun fromHoldingEntityList(list: List<HoldingEntity>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toHoldingEntityList(json: String): List<HoldingEntity> {
        return gson.fromJson(json, object : TypeToken<List<HoldingEntity>>() {}.type)
    }


}