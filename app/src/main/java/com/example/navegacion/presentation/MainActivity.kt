package com.example.navegacion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent //funcion contenedora de aplicacion
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen //splashscreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController //navegacion entre paginas
import com.example.navegacion.R
/*TODO: Files outprovider */
import com.example.navegacion.presentation.AppBasica.Basica
import com.example.navegacion.presentation.AppHospital.Hospital
import com.example.navegacion.presentation.AppNavegacion.MenuNavegacion
import com.example.navegacion.presentation.SueldoTrabajador.Sueldo
/*TODO FILES OUTPROVIDER*/
import com.example.navegacion.presentation.theme.NavegacionTheme

//funcion main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

//funcion de contenido
@Composable
fun WearApp() {
    //estructura de navegacion
    val navController = rememberSwipeDismissableNavController()
    //navegacion del dispositivo entre pantallas
    /* TODO Router de la aplicacion se ejecuta Navhost luego busca con startDestination la funcion correspondiente a renderizar */
    SwipeDismissableNavHost(
        //indica aqui el controlador o "navigate" al que se quiere llegar
        navController = navController,
        startDestination = "PantallaScaffold"
    ){
        //aqui vienen las rutas de navegacion de la aplicacion
        composable("PantallaScaffold"){
            //menu de navegacion con las rutas
            /* TODO: Menunavegacion is Main router to the navigate composables */
            MenuNavegacion(
                //menu navegacion tiene la capacidad de 3 funciones para que pueda navegar a las
                //pantallas correspondientes
                onNavigateToBasica = {
                    navController.navigate("PantallaBasica")
                },
                onNavigateToHospital = {
                    navController.navigate("PantallaHospital")
                },
                onNavigateToSueldoTrabajador = {
                    navController.navigate("PantallaSueldo")
                }
            )
        }

        /*TODO: PantallaBasica Screen coming to the MenuNavegacion*/
        composable("PantallaBasica"){
            //al entrar a pantalla basica podemos regresar a la pantalla principal MenuNavegacion
            Basica(
                onNavigateToScaffold = {
                    navController.navigate("PantallaScaffold")
                }
            )
        }

        /*TODO: PantallaHospital screen is coming to the MenuNavegacion*/
        composable("PantallaHospital"){
            //al entrar a pantallaHospital podemos regresar a la pantalla principal MenuNavegacion
            Hospital(
                onNavigateToScaffold = {
                    navController.navigate("PantallaScaffold")
                }
            )
        }

        /*TODO: PantallaSueldo screen is coming to the MenuNavegacion*/
        composable("PantallaSueldo"){
            //al entrar a pantallaSueldo podemos regresar a la pantalla principal MenuNavegacion
            Sueldo(
                onNavigateToScaffold = {
                    navController.navigate("PantallaScaffold")
                }
            )
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}