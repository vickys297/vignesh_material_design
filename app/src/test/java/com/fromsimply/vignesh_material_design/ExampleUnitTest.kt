package com.fromsimply.vignesh_material_design

import androidx.compose.ui.graphics.Color
import com.fromsimply.vignesh_material_design.presentation.utils.getColorCode
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    //    Color Code test
    @Test
    fun DoubleColorText1() {
        val input = 234234.00.getColorCode()
        assertEquals(Color.Green, input)
    }

    @Test
    fun DoubleColorText2() {
        val input = (-234234.00).getColorCode()
        assertEquals(Color.Red, input)
    }



}