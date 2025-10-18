package com.fromsimply.vignesh_material_design

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fromsimply.vignesh_material_design.data.local.database.LocalDatabase
import com.fromsimply.vignesh_material_design.data.local.database.dao.HoldingDAO
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.fromsimply.vignesh_material_design", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class RoomDBTest {
    private lateinit var database: LocalDatabase
    private lateinit var holdingDao: HoldingDAO

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, LocalDatabase::class.java
        ).build()
        holdingDao = database.holdingsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun SunCurrentValuePositive() = runBlocking {
        val value1 = HoldingEntity(
            symbol = "Holding 1", quantity = 2, ltp = 10.0, avgPrice = 370.1, close = 290.00
        )
        val value2 = HoldingEntity(
            symbol = "Holding 2", quantity = 2, ltp = 10.0, avgPrice = 1245.45, close = 1103.05
        )

        holdingDao.insertAll(listOf(value2, value1))

        val sumOfCurrentValue = holdingDao.getCurrentValueSum()

        val sum = sumOfCurrentValue.first()
        assertEquals(40.00, sum, 0.01)
    }


    @Test
    fun SunCurrentValueNegative() = runBlocking {
        val value1 = HoldingEntity(
            symbol = "Holding 1", quantity = 2, ltp = -10.0, avgPrice = 370.1, close = 290.00
        )
        val value2 = HoldingEntity(
            symbol = "Holding 2", quantity = 2, ltp = -10.0, avgPrice = 1245.45, close = 1103.05
        )

        holdingDao.insertAll(listOf(value2, value1))

        val sumOfCurrentValue = holdingDao.getCurrentValueSum()

        val sum = sumOfCurrentValue.first()
        assertEquals(-40.00, sum, 0.01)
    }

    @Test
    fun SunCurrentTotalInvestment() = runBlocking {
        val value1 = HoldingEntity(
            symbol = "Holding 1", quantity = 2, ltp = -10.0, avgPrice = 20.0, close = 290.00
        )
        val value2 = HoldingEntity(
            symbol = "Holding 2", quantity = 2, ltp = -10.0, avgPrice = 20.0, close = 1103.05
        )

        holdingDao.insertAll(listOf(value2, value1))

        val sumOfCurrentValue = holdingDao.getTotalInvestmentSum()

        val sum = sumOfCurrentValue.first()
        assertEquals(80.00, sum, 0.01)
    }


    @Test
    fun SumTodaysPNL() = runBlocking {
        val value1 = HoldingEntity(
            symbol = "Holding 1", quantity = 2, ltp = 10.0, avgPrice = 20.0, close = 2.00
        )
        val value2 = HoldingEntity(
            symbol = "Holding 2", quantity = 2, ltp = 10.0, avgPrice = 20.0, close = 2.00
        )

        holdingDao.insertAll(listOf(value2, value1))

        val sumOfCurrentValue = holdingDao.getTodayPNL()

        val sum = sumOfCurrentValue.first()
        assertEquals(-32.00, sum, 0.01)
    }


}