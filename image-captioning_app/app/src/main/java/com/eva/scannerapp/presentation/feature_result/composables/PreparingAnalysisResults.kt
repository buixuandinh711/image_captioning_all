package com.eva.scannerapp.presentation.feature_result.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eva.scannerapp.R
import com.eva.scannerapp.ui.theme.ScannerAppTheme

@Composable
fun PreparingAnalysisResults(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.spacedBy(4.dp)
	) {

		LinearProgressIndicator(
			color = MaterialTheme.colorScheme.secondary,
			trackColor = MaterialTheme.colorScheme.surfaceVariant
		)
	}
}

@PreviewLightDark
@Composable
private fun PreparingAnalysisResultsPreview() = ScannerAppTheme {
	Surface {
		PreparingAnalysisResults(modifier = Modifier.padding(8.dp))
	}
}