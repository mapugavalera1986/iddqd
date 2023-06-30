package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
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
import pe.edu.cibertec.iddqd.data.model.Motivo
import pe.edu.cibertec.iddqd.data.model.Participante
import pe.edu.cibertec.iddqd.data.model.Reporte
import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.model.Videojuego
import pe.edu.cibertec.iddqd.data.repository.MotivoRepository
import pe.edu.cibertec.iddqd.data.repository.ParticipanteRepository
import pe.edu.cibertec.iddqd.data.repository.ReporteRepository
import pe.edu.cibertec.iddqd.data.repository.TiempoRepository
import pe.edu.cibertec.iddqd.data.repository.VideojuegoRepository
import pe.edu.cibertec.iddqd.util.Result

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarReportes(navController: NavController, dni: String?) {
    val context = LocalContext.current
    val par = remember { mutableStateOf(Participante(0, "", "", "", 0)) }
    val repoParticipantes = ParticipanteRepository()
    val repoReportes = ReporteRepository()
    val repoVideojuego = VideojuegoRepository()
    val repoMotivo = MotivoRepository()
    val repoTiempo = TiempoRepository()
    val reportes = remember { mutableStateOf<List<Reporte>>(emptyList()) }
    val videojuegos = remember { mutableStateOf(listOf<Videojuego>()) }
    val motivos = remember { mutableStateOf(listOf<Motivo>()) }
    val eltiempo = remember { mutableStateOf(listOf<Tiempo>()) }

    repoVideojuego.listarVideojuegos { rvdjg ->
        if (rvdjg is Result.Success) {
            videojuegos.value = rvdjg.data!!
        } else {
            Toast.makeText(context, rvdjg.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    repoMotivo.listarMotivos { rmot ->
        if (rmot is Result.Success) {
            motivos.value = rmot.data!!
        } else {
            Toast.makeText(context, rmot.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    repoTiempo.listarTiempo { tmt ->
        if (tmt is Result.Success) {
            eltiempo.value = tmt.data!!
        } else {
            Toast.makeText(context, tmt.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    if (dni != null) {
        repoParticipantes.obtenerParticipantePorDni(dni) { rpart ->
            if (rpart is Result.Success) {
                par.value = rpart.data?.get(0)!!
                val participanteId = par.value.id
                repoReportes.listarReportesPorParticipante(participanteId) { reportesResult ->
                    if (reportesResult is Result.Success) {
                        reportes.value = reportesResult.data ?: emptyList()
                    } else {
                        Toast.makeText(
                            context,
                            reportesResult.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    val pid = par.value.id.toString()
    val pnmbr = par.value.nmbrs

    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("¡Hola, $pnmbr!") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    actions = {
                        IconButton(onClick = { navController.navigate("Estatus/$dni/$pid/") }) {
                            Icon(Icons.Filled.Info, "Ver estadísticas de reportes")
                        }
                        IconButton(onClick = { navController.navigate("ElegirJuego/$dni/$pid/") }) {
                            Icon(Icons.Filled.Add, "Agregar nuevo reporte")
                        }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            if (reportes.value.isNotEmpty()) {
                Column {
                    reportes.value.forEach { reporte ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                val vdjg = reporte.id_videojuego - 1
                                val mtv = reporte.id_motivo - 1
                                val temp = reporte.id_tiempo - 1
                                Text("${reporte.fecha}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Jugué a ${videojuegos.value[vdjg].nmbr}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("¿El motivo? ${motivos.value[mtv].dscrpcn}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Jugué por ${eltiempo.value[temp].dscrpcn}")
                            }
                        }
                    }
                }
            } else {
                Text("No se encontraron reportes")
            }
        }
    }
}
