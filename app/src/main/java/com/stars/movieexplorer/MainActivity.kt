package com.stars.movieexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stars.movieexplorer.presentation.navigation.AppNavHost
import com.stars.movieexplorer.ui.theme.MovieExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieExplorerTheme {
                AppNavHost()
            }
        }
    }
}