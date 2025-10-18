package com.fromsimply.vignesh_material_design.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun PNLBottomPlaceholder(pnl: Double, expanded: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .clickable(onClick = {
                expanded.value = !expanded.value
            })
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp
            )
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Profit & Loss*",
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                Icon(
                    imageVector = if (expanded.value) {
                        Icons.Default.KeyboardArrowDown
                    } else {
                        Icons.Default.KeyboardArrowUp
                    },
                    tint = MaterialTheme.colorScheme.inverseSurface,
                    contentDescription = "Expand description"
                )
            }
            Text(
                text = pnl.toCurrency(),
                color = pnl.getColorCode()
            )
        }
    }
}


@Composable
fun PNLBottomSheetView(
    currentValue: State<Double>,
    currentTotalInvestmentValue: State<Double>,
    todayPNL: State<Double>
) {
    Box(
        modifier = Modifier.background(
            MaterialTheme.colorScheme.surfaceVariant,
            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Spacer(modifier = Modifier.padding(all = 0.dp))
            Row {
                Text(
                    text = "Current Value*", modifier = Modifier.weight(1f), style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ), color = MaterialTheme.colorScheme.inverseSurface

                )
                Text(
                    text = currentValue.value.toCurrency(),
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }

            Row {
                Text(
                    text = "Total Investment*", modifier = Modifier.weight(1f), style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ), color = MaterialTheme.colorScheme.inverseSurface

                )
                Text(
                    text = currentTotalInvestmentValue.value.toCurrency(),
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }

            Row {
                Text(
                    text = "Today's Profit & Loss*",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    color = MaterialTheme.colorScheme.inverseSurface

                )
                Text(
                    text = todayPNL.value.toCurrency(),
                    color = todayPNL.value.getColorCode()
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.inverseSurface)
        }
    }
}
