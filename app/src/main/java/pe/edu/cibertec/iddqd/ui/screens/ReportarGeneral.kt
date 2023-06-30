package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.cibertec.iddqd.data.model.Reporte
import pe.edu.cibertec.iddqd.data.model.Tiempo
import pe.edu.cibertec.iddqd.data.repository.ReporteRepository
import pe.edu.cibertec.iddqd.data.repository.TiempoRepository
import pe.edu.cibertec.iddqd.data.repository.VideojuegoRepository
import pe.edu.cibertec.iddqd.util.Result

@Composable
fun contarRegistros(num: Int): String {
    val reportes = remember { mutableStateOf<List<Reporte>>(emptyList()) }
    ReporteRepository().listarReportesPorParticipante(num){rpta ->
        if(rpta is Result.Success){
            reportes.value = rpta.data!!
        }
    }
    return "${reportes.value.size}"
}
@Composable
fun contarHorasJuego(num: Int): String {
    var resultado = ""
    val reportes = remember {mutableStateOf(listOf<Reporte>()) }
    ReporteRepository().listarReportesPorParticipante(num){rpta ->
        if(rpta is Result.Success){
            reportes.value = rpta.data!!
        }
    }
    val eltiempo = remember { mutableStateOf(listOf<Tiempo>()) }
    TiempoRepository().listarTiempo {temp ->
        if(temp is Result.Success){
            eltiempo.value = temp.data!!
        }
    }
    var sumarTiempo = 0.0
    reportes.value.forEach{ reporte ->
        val temp = reporte.id_tiempo-1
        sumarTiempo += eltiempo.value[temp].minutos
    }
    var minutos = (sumarTiempo%60).toInt()
    var horas = ((sumarTiempo-minutos)/60).toInt()
    resultado = if(minutos>0){
        "$horas horas y $minutos minutos"
    }
    else{
        "$horas horas"
    }
    return resultado
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportarGeneral(navController: NavController, dni: String?, pid: String?) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("Información personal") },
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
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp, 16.dp)
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            Text("Tu información sobre cómo has utilizado los videojuegos.")
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .width(640.dp)
                    .height(320.dp)
                    .padding(16.dp)
            ) {
                Box(Modifier.padding(16.dp)){
                    Text("Partidas registradas hasta ahora",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Box(Modifier.padding(16.dp)){
                    Text(contarRegistros(pid!!.toInt()),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp)
                }
                Box(Modifier.padding(16.dp)){
                    Text("Minutos en total",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                }
                Box(Modifier.padding(16.dp)){
                    Text(contarHorasJuego(pid!!.toInt()),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { navController.navigate("Iniciar") }) {
                    Text(text = "Salir (necesitarás volver a ingresar)")
                }
            }
        }
    }
}