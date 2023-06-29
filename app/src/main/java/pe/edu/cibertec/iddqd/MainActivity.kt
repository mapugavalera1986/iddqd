package pe.edu.cibertec.iddqd

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.iddqd.ui.screens.Iniciar
import pe.edu.cibertec.iddqd.ui.screens.ListarReportes
import pe.edu.cibertec.iddqd.ui.screens.AgregarJuego
import pe.edu.cibertec.iddqd.ui.screens.AgregarMotivo
import pe.edu.cibertec.iddqd.ui.screens.AgregarTiempo
import pe.edu.cibertec.iddqd.ui.screens.PreviaReporte
import pe.edu.cibertec.iddqd.ui.screens.ReportarGeneral
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReportarVideojuegosTheme {
                Scaffold {
                    NavigationComponent()
                }
            }
        }
    }
}

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Iniciar") {
        composable("Iniciar") {
            Iniciar(navController)
        }
        composable("Reportes/{dni}/",

        ) {
            ListarReportes(navController, it.arguments?.getString("dni"))
        }
        composable("ElegirJuego/{dni}/{pid}/") {
            AgregarJuego(navController,
                it.arguments?.getString("dni"),
                it.arguments?.getString("pid")
            )
        }
        composable("ElegirMotivo/{dni}/{pid}/{vid}/") {
            AgregarMotivo(
                navController,
                it.arguments?.getString("dni"),
                it.arguments?.getString("pid"),
                it.arguments?.getString("vid")
            )
        }
        composable("ElegirTiempo/{dni}/{pid}/{vid}/{mid}/") {
            AgregarTiempo(navController,
                it.arguments?.getString("dni"),
                it.arguments?.getString("pid"),
                it.arguments?.getString("vid"),
                it.arguments?.getString("mid")
            )
        }
        composable("Previsualizar/{dni}/{pid}/{vid}/{mid}/{tid}/"){
            PreviaReporte(navController,
                it.arguments?.getString("dni"),
                it.arguments?.getString("pid"),
                it.arguments?.getString("vid"),
                it.arguments?.getString("mid"),
                it.arguments?.getString("tid")
            )
        }

        composable("Estatus/{dni}/{pid}/") {
            ReportarGeneral(navController,
                it.arguments?.getString("dni"),
                it.arguments?.getString("pid")
            )
        }
    }
}



@Composable
fun HomeScreen(navController: NavController) {
    Text(text = "Si puedes ver esto, créeme, no todo está perdido.")
}
