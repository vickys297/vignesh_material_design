package com.fromsimply.vignesh_material_design.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PNLBottomSheetView() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row {
            Text(
                text = "Current Value*", modifier = Modifier.weight(1f), style = TextStyle(
                    fontWeight = FontWeight.Thin, fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            )
            Text(text = "1000".toDouble().toCurrency())
        }

        Row {
            Text(
                text = "Total Investment*", modifier = Modifier.weight(1f), style = TextStyle(
                    fontWeight = FontWeight.Thin, fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            )
            Text(text = "1000".toDouble().toCurrency())
        }

        Row {
            Text(
                text = "Today's Profit & Loss*", modifier = Modifier.weight(1f), style = TextStyle(
                    fontWeight = FontWeight.Thin, fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            )
            Text(text = "1000".toDouble().toCurrency())
        }
    }
}

@Preview
@Composable
fun PreViewPNLBottomSheetView() {
    PNLBottomSheetView()
}