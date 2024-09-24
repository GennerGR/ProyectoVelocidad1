package com.gnr.proyectovelocidad1

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.gnr.proyectovelocidad1.pantallaTipoSplash.PantallaTipoSplash
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen = PantallaTipoSplash()) { navigator: Navigator ->
            SlideTransition(navigator)
        }
    }
}