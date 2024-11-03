package com.berkayyaman.footballapp.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme

/**
 * Created by berkayyaman on 03,November,2024
 */

@Composable
fun ErrorDialog(
    errorMessage: String
) {

    val shouldShowDialog = remember(key1 = errorMessage) {
        mutableStateOf(true)
    }

    if (errorMessage.isNotEmpty() && shouldShowDialog.value){
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = {
                Text(
                    text = "Error"
                )
            },
            text = {
                Text(
                    text = errorMessage
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "OK",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        )
    }
}

@Composable
@Preview
private fun ErrorDialogPreview(){
    FootballAppTheme {
        Surface {
            ErrorDialog("Değer Bulunamadı")
        }
    }
}