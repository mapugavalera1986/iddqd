package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.iddqd.data.model.Videojuego
import pe.edu.cibertec.iddqd.data.repository.VideojuegoRepository
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme
import pe.edu.cibertec.iddqd.util.Dummy
import pe.edu.cibertec.iddqd.util.Result

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarReporte(navController: NavController) {
    val dummy = Dummy()
    val videojuegos = remember { mutableStateOf(listOf<Videojuego>()) }
    val repoVideojuego = VideojuegoRepository()
    var idvideojuego = -1
    val context = LocalContext.current
    repoVideojuego.listarVideojuegos { result ->
        if (result is Result.Success) {
            videojuegos.value = result.data!!
        } else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("¿Cuál jugaste esta vez?") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.navigate("Reportes") }
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
        LazyColumn (Modifier.padding(0.dp,72.dp,0.dp,0.dp)){
            items(videojuegos.value) { videojuego ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(64.dp),
                    onClick ={
                        dummy.idVideojuego = videojuego.id
                        navController.navigate("Agregar_B")
                    }
                ){
                    Text(videojuego.nmbr)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgregarReportePreview(){
    ReportarVideojuegosTheme {
        AgregarReporte(navController = rememberNavController())
    }
}