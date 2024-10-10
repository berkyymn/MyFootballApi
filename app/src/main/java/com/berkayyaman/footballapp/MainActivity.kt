package com.berkayyaman.footballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.berkayyaman.footballapp.ui.theme.FootballAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FootballAppTheme {
                Text(text = "Txt", modifier = Modifier.fillMaxSize())
            }
        }
    }
}
