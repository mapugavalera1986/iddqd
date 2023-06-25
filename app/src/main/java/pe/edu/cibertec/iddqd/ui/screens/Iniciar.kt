package pe.edu.cibertec.iddqd.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.iddqd.data.repository.ParticipanteRepository
import pe.edu.cibertec.iddqd.ui.theme.ReportarVideojuegosTheme

@Composable
fun Iniciar(navController: NavController){
    val dni = remember{
        mutableStateOf(TextFieldValue())
    }
    val context = LocalContext.current
    val repoParticipante = ParticipanteRepository()
    //INICIO de los elementos gráficos
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar sesión con tu DNI")
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            label = { Text("DNI") },
            value = dni.value,
            onValueChange = {
                dni.value = it
            },
            leadingIcon = { Icon(Icons.Default.Lock, null) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .width(160.dp)
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            onClick = {
                navController.navigate("Reportes")
            }
        ) {
            Text("Ingresar")
        }
    }
    //FIN de los elementos gráficos
}

@Preview(showBackground = true)
@Composable
fun IniciarPreview(){
    ReportarVideojuegosTheme {
        Iniciar(navController = rememberNavController())
    }
}