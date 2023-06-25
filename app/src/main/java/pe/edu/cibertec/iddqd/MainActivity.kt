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
import pe.edu.cibertec.iddqd.ui.screens.ListarReportesPart
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
fun NavigationComponent(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            Iniciar(navController)
        }
        composable("Reportes"){
            ListarReportesPart(navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    Text(text = "Si puedes ver esto, créeme, no todo está perdido.")
}