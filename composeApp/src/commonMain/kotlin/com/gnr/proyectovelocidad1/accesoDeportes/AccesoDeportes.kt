package com.gnr.proyectovelocidad1.accesoDeportes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class AccesoDeportes : Screen {

    @Composable
    override fun Content() {

        //navegacion
        val navigator = LocalNavigator.currentOrThrow

        //contendor principal
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            //Texto de bienvenida

            Text(
                "BIENVENIDO A DEPORTES",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Botones para acceder a los DEPORTES

            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                Box(modifier = Modifier.weight(1f).height(150.dp)) {
                    Button(onClick = {
                        navigator.push(SportLeagueScreen(1, "baseball"))
                    }) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text("BASEBALL")
                        }
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Box (modifier = Modifier.weight(1f).height(150.dp)) {
                    Button(onClick = {
                        navigator.push(SportLeagueScreen(1, "basketball"))
                    }) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text("BASKETBALL")
                        }
                    }
                }
            }
            Row (modifier = Modifier.padding(20.dp)) {
                Box(modifier = Modifier.weight(1f).height(150.dp)) {
                    Button(onClick = {
                        navigator.push(SportLeagueScreen(3, "football"))
                    }) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text("FOOTBALL")
                        }
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}