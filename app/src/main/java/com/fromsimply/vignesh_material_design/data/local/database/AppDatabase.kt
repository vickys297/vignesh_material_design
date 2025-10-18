package com.fromsimply.vignesh_material_design.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fromsimply.vignesh_material_design.data.local.database.dao.HoldingDAO
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity


@Database(entities = [HoldingEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConvertersHelpers::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun holdingsDao(): HoldingDAO
}


class LocalDatabaseConfig {

    companion object {
        const val databaseName = "app_database"

        fun createDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(
                context = context, klass = LocalDatabase::class.java, name = databaseName
            ).fallbackToDestructiveMigration(dropAllTables = true).build()
        }
    }

}