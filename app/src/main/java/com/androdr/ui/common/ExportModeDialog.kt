package com.androdr.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.androdr.R
import com.androdr.reporting.ExportMode

@Composable
fun ExportModeDialog(
    onDismiss: () -> Unit,
    onConfirm: (ExportMode) -> Unit,
) {
    var selectedMode by remember { mutableStateOf(ExportMode.BOTH) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.export_report_title)) },
        text = {
            Column {
                ExportMode.values().forEach { mode ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedMode == mode,
                                onClick = { selectedMode = mode },
                            )
                            .padding(vertical = 8.dp),
                    ) {
                        RadioButton(
                            selected = selectedMode == mode,
                            onClick = { selectedMode = mode },
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(exportModeLabel(mode))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(selectedMode) }) {
                Text(stringResource(R.string.common_export))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.common_cancel))
            }
        },
    )
}

@Composable
fun exportModeLabel(mode: ExportMode): String = when (mode) {
    ExportMode.TELEMETRY_ONLY -> stringResource(R.string.export_telemetry_only)
    ExportMode.FINDINGS_ONLY -> stringResource(R.string.export_findings_only)
    ExportMode.BOTH -> stringResource(R.string.export_both)
}
