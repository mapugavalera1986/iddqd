package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.iddqd.data.model.Participante
import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.repository.ParticipanteRepository
import pe.edu.cibertec.iddqd.util.Result
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarReportes(navController: NavController, dni: String?) {
    val par = remember { mutableStateOf(Participante(0, "", "", "", 0)) }
    val reportes = remember { mutableStateOf<List<Reporte>>(emptyList()) }
    val context = LocalContext.current
    val repoParticipantes = ParticipanteRepository()
    val repoReportes = ReporteRepository()

    if (dni != null) {
        repoParticipantes.obtenerParticipantePorDni(dni) { result ->
            if (result is Result.Success) {
                par.value = result.data?.get(0)!!
                val participanteId = par.value.id
                repoReportes.listarReportesPorParticipante(participanteId) { reportesResult ->
                    if (reportesResult is Result.Success) {
                        reportes.value = reportesResult.data ?: emptyList()
                    } else {
                        Toast.makeText(context, reportesResult.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
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
                        IconButton(onClick = { navController.navigate("Estatus/$pid/") }) {
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
                                Text("ID: ${reporte.id}")
                                Text("ID Participante: ${reporte.id_participante}")
                                Text("ID Videojuego: ${reporte.id_videojuego}")
                                Text("ID Motivo: ${reporte.id_motivo}")
                                Text("Fecha: ${reporte.fecha}")
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
