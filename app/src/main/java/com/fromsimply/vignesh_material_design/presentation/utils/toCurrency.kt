package com.fromsimply.vignesh_material_design.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency


fun Double.getColorCode(): Color {
    return if (this > 0) {
        Color(0xFF5CB338)
    } else Color.Red
}


@Composable
fun Double.toCurrency(currencyCode: String = "INR"): String {
    val locale = LocalConfiguration.current.locales[0]

    return try {
        val formatter = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
        val currency = currencyCode.let { Currency.getInstance(it) } ?: formatter.currency
        formatter.currency = currency

        val symbols = formatter.decimalFormatSymbols
        val currencySymbol = currency.symbol
        val pattern = if (currencyCode == "INR") {
            "#,##,##0.00"
        } else {
            "#,##0.00"
        }
        val numberFormatter = DecimalFormat(pattern, symbols)
        val formattedNumber = numberFormatter.format(this)
        "$currencySymbol $formattedNumber"
    } catch (e: Exception) {
        e.printStackTrace()
        "%.2f".format(locale, this)
    }
}