package com.example.calculadoraimc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaInicio(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }

    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Calculadora IMC",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            label = {
                Text("Ingrese su nombre")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = peso,
            onValueChange = {
                peso = it
            },
            label = {
                Text("Ingrese su peso")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = altura,
            onValueChange = {
                altura = it
            },
            label = {
                Text("Ingrese su altura")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (mensajeError.isNotEmpty()) {

            Text(
                text = mensajeError,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                val pesoNumero = peso.toDoubleOrNull()
                val alturaNumero = altura.toDoubleOrNull()

                if (
                    pesoNumero != null &&
                    alturaNumero != null &&
                    pesoNumero > 0 &&
                    alturaNumero > 0
                ) {

                    mensajeError = ""

                    val imc =
                        pesoNumero / (alturaNumero * alturaNumero)

                    navController.navigate(
                        "resultado/$nombre/${imc}"
                    )

                } else {

                    mensajeError =
                        "Por favor, ingresa valores válidos"
                }
            }
        ) {

            Text("Calcular")
        }
    }
}

@Composable
fun PantallaResultado(
    nombre: String,
    imc: Float,
    navController: NavController
) {

    var categoria = ""
    var colorTexto = Color.Black

    when {
        imc < 18.5 -> {
            categoria = "Bajo peso"
            colorTexto = Color.Red
        }
        imc < 25.0 -> {
            categoria = "Peso normal"
            colorTexto = Color.Green
        }
        imc < 30.0 -> {
            categoria = "Sobrepeso"
            colorTexto = Color.Blue
        }
        else -> {
            categoria = "Obesidad"
            colorTexto = Color.Red
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hola $nombre, tu resultado es:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = String.format("%.1f", imc),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = categoria,
            color = colorTexto,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {

            Text("Volver")
        }
    }
}