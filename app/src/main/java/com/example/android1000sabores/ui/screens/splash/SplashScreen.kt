package com.example.android1000sabores.ui.screens.splash

// Importaciones necesarias para la animación y Composable
import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import com.example.android1000sabores.R


/**
 * Muestra el logo de la Splash Screen con una animación "jello-vertical".
 *
 * Esta animación se ejecuta una sola vez cuando el Composable entra en la composición.
 *
 * @param modifier Modificador para aplicar al contenedor de la imagen.
 * @param logoDrawableRes El ID del recurso drawable de tu logo (p.ej., R.drawable.mi_logo).
 */
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    // ¡Importante! Reemplaza esto con el ID de tu propio logo
    logoDrawableRes: Int = R.drawable.logo
) {
    // 1. Definimos la duración total de la animación (CSS: .8s)
    val durationMs = 800

    // 2. Definimos la curva de easing (CSS: cubic-bezier(.6,-.28,.735,.045))
    // Esta curva le da el efecto de "rebote" elástico.
    val customEasing = CubicBezierEasing(0.6f, -0.28f, 0.735f, 0.045f)

    // 3. Creamos un Animatable para cada propiedad que cambia: scaleX y scaleY.
    // Empezamos con un valor de 1f (escala normal).
    val scaleX = remember { Animatable(initialValue = 1f) }
    val scaleY = remember { Animatable(initialValue = 1f) }

    // 4. Usamos LaunchedEffect para iniciar la animación tan pronto como
    // este Composable aparece en pantalla (perfecto para un splash).
    // key1 = true significa que se ejecutará solo una vez.
    LaunchedEffect(key1 = true) {
        // ... (Tu lógica de animación está perfecta, no se toca) ...
        launch {
            scaleX.animateTo(
                targetValue = 1f,
                animationSpec = keyframes {
                    durationMillis = durationMs
                    1.0f at 0 with customEasing
                    0.75f at (durationMs * 0.30).toInt() with customEasing
                    1.25f at (durationMs * 0.40).toInt() with customEasing
                    0.85f at (durationMs * 0.50).toInt() with customEasing
                    1.05f at (durationMs * 0.65).toInt() with customEasing
                    0.95f at (durationMs * 0.75).toInt() with customEasing
                    1.0f at durationMs with customEasing
                }
            )
        }
        launch {
            scaleY.animateTo(
                targetValue = 1f,
                animationSpec = keyframes {
                    durationMillis = durationMs
                    1.0f at 0 with customEasing
                    1.25f at (durationMs * 0.30).toInt() with customEasing
                    0.75f at (durationMs * 0.40).toInt() with customEasing
                    1.15f at (durationMs * 0.50).toInt() with customEasing
                    0.95f at (durationMs * 0.65).toInt() with customEasing
                    1.05f at (durationMs * 0.75).toInt() with customEasing
                    1.0f at durationMs with customEasing
                }
            )
        }
    }

    // 5. Aplicamos los valores de la animación a nuestro Composable
    // ¡AQUÍ ESTÁ EL CAMBIO!
    // Envolvemos la Imagen en un Box que ocupa toda la pantalla y la centra.
    Box(
        modifier = modifier.fillMaxSize(), // <-- Ocupa toda la pantalla
        contentAlignment = Alignment.Center // <-- Centra a su hijo (la Imagen)
    ) {
        Image(
            painter = painterResource(id = logoDrawableRes), // <-- Usamos el parámetro
            contentDescription = "Logo Animado",
            modifier = Modifier // <-- El modifier de la imagen ahora es local
                .size(300.dp) // Define un tamaño para tu logo
                .graphicsLayer {
                    // Actualiza la escala del Composable en cada frame
                    this.scaleX = scaleX.value
                    this.scaleY = scaleY.value
                }
        )
    }
}
/**
 * Función de Previsualización para ver la animación en Android Studio.
 * Simula una Splash Screen centrando el logo.
 */
@Preview(showBackground = true)
@Composable
fun JelloVerticalPreview() {
        SplashScreen(logoDrawableRes = R.drawable.logo)
}