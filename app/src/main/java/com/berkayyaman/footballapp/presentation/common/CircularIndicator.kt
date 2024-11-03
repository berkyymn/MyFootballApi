package com.berkayyaman.footballapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme
import com.berkayyaman.footballapp.util.Dimens

/**
 * Created by berkayyaman on 03,November,2024
 */

@Composable
fun CircularIndicator(
    isLoading: Boolean
) {

    if (!isLoading) return

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        CircularProgressIndicator(
            modifier = Modifier.width(Dimens.ProgressBarSize),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }


}


@Composable
@Preview
private fun CircularIndicatorPreview() {
    FootballAppTheme {
        Surface {
            CircularIndicator(isLoading = true)
        }
    }
}