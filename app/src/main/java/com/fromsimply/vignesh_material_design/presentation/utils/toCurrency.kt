package com.fromsimply.vignesh_material_design.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.text.NumberFormat
import java.util.Currency
import androidx.compose.ui.platform.LocalConfiguration
import java.text.DecimalFormat

@Composable
fun Double.toCurrency(currencyCode: String? = null): String {
    val locale = LocalConfiguration.current.locales[0]

    return try {
        val formatter = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
        val currency = currencyCode?.let { Currency.getInstance(it) } ?: formatter.currency
        formatter.currency = currency

        val symbols = formatter.decimalFormatSymbols
        val currencySymbol = currency.symbol

        val pattern = "#,##0.00"
        val numberFormatter = DecimalFormat(pattern, symbols)
        val formattedNumber = numberFormatter.format(this)
        "$currencySymbol $formattedNumber"
    } catch (e: Exception) {
        "%.2f".format(locale, this)
    }
}