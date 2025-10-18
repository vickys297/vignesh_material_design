package com.fromsimply.vignesh_material_design.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fromsimply.vignesh_material_design.R
import com.fromsimply.vignesh_material_design.data.local.entity.HoldingUI

@Composable
fun HoldingItem(holding: HoldingUI) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .padding(bottom = 8.dp)
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(8.dp))
        Row {
            Text(
                holding.symbol, modifier = Modifier.weight(1f), style = TextStyle.Default.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.ltp), style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    holding.ltp.toCurrency(), style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
            }
        }
        Spacer(modifier = Modifier.padding(18.dp))
        Row {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.net_qty), style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    holding.quantity.toString(), style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.p_l), style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    holding.totalPNL.toCurrency(), style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    ), color = holding.totalPNL.getColorCode()
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewHoldingUI() {
    HoldingItem(
        holding = HoldingUI(
            symbol = "Test Holding",
            quantity = 1213,
            ltp = 1213.00,
            avgPrice = 3432.0,
            close = 134.1,
            totalPNL = 0.00
        )
    )
}