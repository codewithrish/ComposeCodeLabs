package app.codewithtish.composecodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.codewithtish.composecodelabs.ui.theme.ComposeCodeLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodeLabsTheme {
                Home()
            }
        }
    }
}