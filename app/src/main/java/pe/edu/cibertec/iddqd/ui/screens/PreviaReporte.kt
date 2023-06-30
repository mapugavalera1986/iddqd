package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
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
import pe.edu.cibertec.iddqd.data.model.Motivo
import pe.edu.cibertec.iddqd.data.model.Reporte
import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.model.Videojuego
import pe.edu.cibertec.iddqd.data.repository.MotivoRepository
import pe.edu.cibertec.iddqd.data.repository.ReporteRepository
import pe.edu.cibertec.iddqd.data.repository.TiempoRepository
import pe.edu.cibertec.iddqd.data.repository.VideojuegoRepository
import pe.edu.cibertec.iddqd.util.Result
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviaReporte(
    navController: NavController,
    dni: String?,
    pid: String?,
    vid: String?,
    mid: String?,
    tid: String?
) {
    val context = LocalContext.current
    val elVidejuego = remember { mutableStateOf(Videojuego(0, "", 0)) }
    val elMotivo = remember { mutableStateOf(Motivo(0, "")) }
    val elTiempo = remember { mutableStateOf(Tiempo(0, "", 0)) }
    val repoVideojuego = VideojuegoRepository()
    val repoMotivo = MotivoRepository()
    val repoTiempo = TiempoRepository()
    val repoReporte = ReporteRepository()

    if (vid != null) {
        repoVideojuego.obtenerVideojuegoPorId(vid.toInt()) { result ->
            if (result is Result.Success) {
                elVidejuego.value = result.data!![0]
            } else {
                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (mid != null) {
        repoMotivo.obtenerMotivoPorId(mid.toInt()) { result ->
            if (result is Result.Success) {
                elMotivo.value = result.data!![0]
            } else {
                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (tid != null) {
        repoTiempo.obtenerTiempoPorId(tid.toInt()) { result ->
            if (result is Result.Success) {
                elTiempo.value = result.data!![0]
            } else {
                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    val estaFecha = SimpleDateFormat("dd/MM/yyyy").format(Date())

    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("Datos que reportaste") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.navigate("Reportes/$dni/") }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Empezar de nuevo",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp)
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            Text("Videojuego: ${elVidejuego.value.nmbr}")
            Text("Motivo: ${elMotivo.value.dscrpcn}")
            Text("Tiempo: ${elTiempo.value.dscrpcn}")
            Text("Fecha: $estaFecha")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = {
                    val reporte = Reporte(
                        id = 0,
                        id_participante = pid?.toInt() ?: 0,
                        id_videojuego = vid?.toInt() ?: 0,
                        id_motivo = mid?.toInt() ?: 0,
                        id_tiempo = tid?.toInt() ?: 0,
                        fecha = estaFecha
                    )

                    repoReporte.crearReporte(
                        id = reporte.id,
                        id_participante = reporte.id_participante,
                        id_videojuego = reporte.id_videojuego,
                        id_motivo = reporte.id_motivo,
                        id_tiempo = reporte.id_tiempo,
                        fecha = reporte.fecha
                    ) { result ->
                        if (result is Result.Success) {
                            Toast.makeText(
                                context,
                                "Reporte guardado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                context,
                                "Error al guardar el reporte",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navController.navigate("Reportes/${dni}/")
                    }
                }) {
                    Text(
                        text = "Registrar partida",
                    )
                    Modifier.width(32.dp)
                }
            }
        }
    }
}
