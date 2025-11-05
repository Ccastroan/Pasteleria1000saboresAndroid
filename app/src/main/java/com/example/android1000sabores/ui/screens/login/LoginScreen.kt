package com.example.android1000sabores.ui.screens.login


import androidx.compose.runtime.Composable
import android.widget.Toast //mensajes emergentes.
import androidx.compose.foundation.layout.* //diseño.
import androidx.compose.foundation.text.KeyboardOptions //opciones del teclado.
import androidx.compose.material3.*  //componentes de materiales.
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment //alineación.
import androidx.compose.ui.Modifier //Modificar diseño visual.
import androidx.compose.ui.platform.LocalContext //contexto local o estado de la ejercuccion con ciclo de via app y poder mostrar mensaje.
import androidx.compose.ui.text.input.KeyboardType//Controlar el tipo de entrada para el usuario.
import androidx.compose.ui.text.input.PasswordVisualTransformation //ocultar contraseña al escribirla.
import androidx.compose.ui.unit.dp //Controlas el tamaño de los elementos en dp.
import androidx.compose.ui.graphics.Color//Controlar el color de los elementos
import androidx.compose.ui.input.key.Key


@Composable
fun LoginScreen() {
    //variable para obtener en tiempo de ejeccución el contexto de la aplicación.
    val context = LocalContext.current

    //variable para almacenar el nombre de usuario.
    var user by remember { mutableStateOf("") }

    //variable para almacenar la contraseña.
    var pass by remember { mutableStateOf("") }

    //configuracion para organizar los elementos de la panmtalla usando el component column.
    Column(
        modifier = Modifier
            .fillMaxSize() //rellenar toda la pantalla.
            .padding(24.dp),
        verticalArrangement = Arrangement.Center, //centrado vertical.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //componentes tipo Text() para agregar un titulo.
        Text(
            "Iniciar Sesión",
            style = MaterialTheme.typography.headlineSmall,
            color = Color(color = 0xFFB7791D)
        )

        //componente Spacer para agregar un espacio entre los elementos.
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            //variable para el nombre de usuario.
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario", color = Color(color = 0xFF15A0B2)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        //componente tipo OutlinedTextField para ingresar el nombre de usuario.
        OutlinedTextField(
            //variable para la clave de usuario.
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Clave", color = Color(color = 0xFF15A0B2)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        //componente Button para el boton de iniciar sesión.)
        Button(
            onClick = {
                Toast.makeText(context,"Bienvenido $user", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors( Color(color = 0xFF1734D5))
        ) {
            Text("Entrar", color = Color.White)
        }

    }
}