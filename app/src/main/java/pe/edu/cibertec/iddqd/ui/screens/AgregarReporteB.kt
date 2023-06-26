package pe.edu.cibertec.iddqd.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import pe.edu.cibertec.iddqd.data.model.Motivo
import pe.edu.cibertec.iddqd.data.repository.MotivoRepository
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme
import pe.edu.cibertec.iddqd.util.Result

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarReporteB(navController: NavController){
    val motivos = remember { mutableStateOf(listOf<Motivo>()) }
    val repoMotivo = MotivoRepository()
    var idmotivo = -1
    val context = LocalContext.current
    repoMotivo.listarMotivos { result ->
        if (result is Result.Success) {
            motivos.value = result.data!!
        } else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    title = { Text("¿Cuál fue tu motivo?") },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.navigate("Agregar_A") }
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
            items(motivos.value) { motivo ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(64.dp),
                    onClick ={
                        Toast.makeText(context,"Mira esto", Toast.LENGTH_SHORT).show()
                        navController.navigate("Agregar_C")
                    }
                ){
                    Text(motivo.dscrpcn)
                }
            }
        }
    }
}