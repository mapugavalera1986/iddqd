package pe.edu.cibertec.iddqd.ui.screens
import android.annotation.SuppressLint
import android.provider.MediaStore.Video
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import pe.edu.cibertec.iddqd.util.Result
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prueba() {
    val videojuegos = remember{ mutableStateOf(listOf<Videojuego>())}
    val repoVideojuego = VideojuegoRepository()
    var seleccionado = false
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
            TopAppBar(
                title = {
                    Text("My App")
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action here */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) {
        Column {
            Spacer(modifier = Modifier.height(72.dp))
            Text(text = "Mira esto")
            LazyColumn{
                items(videojuegos.value){videojuego->
                    RadioButton(selected = seleccionado, onClick = {
                        seleccionado = true
                        idvideojuego = videojuego.id }
                    )
                    Text("Mira: "+videojuego.nmbr)
                }
            }
            Button(
                onClick = { Toast.makeText(context,"Mira $idvideojuego", Toast.LENGTH_SHORT).show() }
            ) {
                Text("El valor")
            }
        }
    }
}