package com.gnr.proyectovelocidad1.accesoDeportes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gnr.proyectovelocidad1.network.NetworkUtils.httpClient
import com.gnr.proyectovelocidad1.network.model.League
import com.gnr.proyectovelocidad1.network.model.LeagueResponse
import com.gnr.proyectovelocidad1.viewDetailsLeague.ViewDetailsLeague
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class SportLeagueScreen(private val v: Int, private val deporte: String) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        var leagueList by remember { mutableStateOf<List<League>>(emptyList()) }
        var loading by remember { mutableStateOf(true) }

        LaunchedEffect(deporte) {
            getLeagues ( deporte, v) { leagues ->
                leagueList = leagues
                loading = false
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.Black)
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(leagueList) { League: League ->
                        Box(modifier = Modifier.padding(8.dp).fillMaxWidth().height(150.dp)) {
                            Button(onClick = {
                                navigator.push(ViewDetailsLeague())
                            }) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(League.name ?: "Unknown", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navigator.pop()
            }) {
                Text("Regresar")
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

private fun getLeagues(deporte: String, v:Int, onSeccessResponse: (List<League>) -> Unit) {
    if(deporte.isBlank()) return
    val url = "https://v$v.$deporte.api-sports.io/leagues"

    CoroutineScope((Dispatchers.IO)).launch {
        try {
            println("Requesting leagues from: $url")

            val response = httpClient.get(url) {
                header("x-rapidapi-key", "afe5fc65fe2756c7390d4ec0224c23f3")
            }.body<LeagueResponse>()

            val leagues = response.response.map { it.toLeague()}

            if (leagues.isNotEmpty()) {
                onSeccessResponse(leagues)
            } else {
                println("No leagues found from $deporte")
                onSeccessResponse(emptyList())
            }
        } catch (e: Exception) {
            println("Error fetching leagues from $deporte: ${e.message}")
            onSeccessResponse(emptyList())
        }
    }
}