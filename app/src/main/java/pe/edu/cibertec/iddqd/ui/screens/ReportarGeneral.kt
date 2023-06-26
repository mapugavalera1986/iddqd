package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme
import pe.edu.cibertec.iddqd.util.Dummy

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportarGeneral(navController: NavController){
    val dummy = Dummy()
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
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp)
        ) {
            Spacer(modifier = Modifier.height(72.dp))
            Text("Aquí van algunos reportes generales. "
                + "No te olvides de cambiarlos cuando todo esté listo.")
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.width(400.dp).height(300.dp).padding(16.dp)
            ){
                Text("Tiempo total con videojuegos:")
                Text("Motivo principal:")
                Text("Juego que utilizas más:")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ReportarGeneralPreview(){
    ReportarVideojuegosTheme {
        AgregarReporte(navController = rememberNavController())
    }
}