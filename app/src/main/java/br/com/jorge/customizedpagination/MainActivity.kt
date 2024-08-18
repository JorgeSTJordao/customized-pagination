package br.com.jorge.customizedpagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.jorge.customizedpagination.ui.theme.CustomizedPaginationTheme

//https://www.youtube.com/watch?v=D6Eus3f6U9I&t=1069s

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomizedPaginationTheme {

            }
        }
    }
}
