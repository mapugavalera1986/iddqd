package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.repository.TiempoRepository
import pe.edu.cibertec.iddqd.util.Result

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarTiempo(
    navController: NavController,
    dni: String?,
    pid: String?,
    vid: String?,
    mid: String?
) {
    val eltiempo = remember { mutableStateOf(listOf<Tiempo>()) }
    val repoTiempo = TiempoRepository()
    val context = LocalContext.current
    repoTiempo.listarTiempo { result ->
        if (result is Result.Success) {
            eltiempo.value = result.data!!
        } else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("¿Cuánto tiempo fue esta vez?") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.navigate("ElegirTiempo/$dni/$pid/$vid/") }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        }
    ) {
        //Spacer(Modifier.padding(80.dp,80.dp,80.dp,80.dp))
        LazyColumn(Modifier.padding(0.dp, 72.dp, 0.dp, 0.dp)) {
            items(eltiempo.value) { tiempo ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(64.dp),
                    onClick = {
                        val eltiempo = tiempo.id.toString()
                        //Toast.makeText(context, "DNI: $dni, N: $pid, Videojuego: $vid "+
                        //", motivo $mid y tiempo: $eltiempo", Toast.LENGTH_SHORT).show()
                        navController.navigate("Previsualizar/$dni/$pid/$vid/$mid/$eltiempo/")
                    }
                ) {
                    Box(Modifier.padding(16.dp)) {
                        Text(tiempo.dscrpcn)
                    }
                }
            }
        }
    }
}