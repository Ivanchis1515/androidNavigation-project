package com.example.navegacion.presentation.AppNavegacion

//funcion composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
//libreria de diseño ui Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp //pixeles
//tema principal de la funcion
import com.example.navegacion.presentation.theme.NavegacionTheme
import com.google.android.horologist.annotations.ExperimentalHorologistApi
//AppScaffold contendor de aplicacion
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScreenScaffold
//importacion de la columnas responsivas para scroll
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState
import com.google.android.horologist.compose.layout.ScalingLazyColumn
//lista responsiva para scroll
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults

//decoracion "Opt" esto sucede ya que horologist es una api experimental
@OptIn(ExperimentalHorologistApi::class)
@Composable
fun MenuNavegacion(
    //parametros de la funcion
    onNavigateToBasica: () -> Unit,
    onNavigateToHospital: () -> Unit,
    onNavigateToSueldoTrabajador: () -> Unit
){
    /*TODO: Use to library Horologist*/
    NavegacionTheme {
        /*TODO: AppScaffold add timeText default that can be overwritten to the ScreenScaffold */
        /*TODO: ensure timetext is animated correctly when navigating between screens */
        AppScaffold{
            val listState = rememberResponsiveColumnState(
                contentPadding = ScalingLazyColumnDefaults.padding(
                    first = ScalingLazyColumnDefaults.ItemType.SingleButton,
                    last = ScalingLazyColumnDefaults.ItemType.Chip
                )
            )

            //modifiers
            val contentModifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            val iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)

            /*
                [Horologist] ScreenScaffold se usa junto con AppScaffold y agrega un indicador
                de posicion a lista de forma predeterminada
            */
            ScreenScaffold(
                scrollState = listState
            ){
                /*
                    [Horologist]: ScalingLazyColumn aplica relleno a los elementos de la lista para
                    asegurarse de que ningun elemento quede recortado en diferentes tamaños de pantalla
                    y permite el scroll sobre los elementos
                */
                ScalingLazyColumn(
                    columnState = listState
                ){
                    /* Composables comunes */
                    item { ButtonExample(contentModifier, iconModifier, onNavigateToBasica) }
                    item { TextExample(contentModifier) }
                    item { CardExample(contentModifier, iconModifier, onNavigateToHospital) }

                    /* Composables unicos para wear */
                    item { ChipExample(contentModifier, iconModifier, onNavigateToSueldoTrabajador) }
                    item { ToggleChipExample(contentModifier) }
                }
            }
        }
    }
}