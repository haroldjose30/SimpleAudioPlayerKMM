package dev.haroldjose.simpleaudioplayerkmm.android.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import dev.haroldjose.simpleaudioplayerkmm.android.ui.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview() {
    MyApp()
}
