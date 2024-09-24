package com.gnr.proyectovelocidad1.pantallaTipoSplash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gnr.proyectovelocidad1.accesoDeportes.AccesoDeportes
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

class PantallaTipoSplash : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SplashScreen(onTimeOut = { navigator.push(AccesoDeportes()) })
        }
    }

    @Composable
    fun SplashScreen(onTimeOut: () -> Unit) {
        LaunchedEffect(Unit) {
            delay(500)
            onTimeOut()
        }
        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.Black)
                Spacer(modifier = Modifier.height(15.dp))
                Text("Cargando deportes...")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewSplashScreen() {
        SplashScreen(onTimeOut = { })
    }
}