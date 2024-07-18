package com.example.navegacion.presentation.AppNavegacion

//layouts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
//iconos
import androidx.compose.material.icons.Icons //libreria de iconos
import androidx.compose.material.icons.automirrored.rounded.Message //icono mensaje
import androidx.compose.material.icons.rounded.Phone //icono telefono
import androidx.compose.material.icons.rounded.SelfImprovement
//composable
import androidx.compose.runtime.Composable
//funciones mutable stateof
import androidx.compose.runtime.getValue //obtener valor de la variable
import androidx.compose.runtime.mutableStateOf //variable en tiempo real
import androidx.compose.runtime.remember //recolector de variable
import androidx.compose.runtime.setValue //cambiar valor
//modifiers
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

//recursos
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Button //boton
import androidx.wear.compose.material.Icon //icono
import androidx.wear.compose.material.MaterialTheme //tema por defecto
import androidx.wear.compose.material.Text //recurso texto
import com.example.navegacion.R //textos
import androidx.wear.compose.material.AppCard //card
import androidx.wear.compose.material.Chip //minitarjetita
import androidx.wear.compose.material.Switch //cambio en tarjeta togglechip
import androidx.wear.compose.material.ToggleChip //tarjeta de interactiva

@Composable
fun ReusableComponents(){
}

@Composable
fun ButtonExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onNavigateToBasica: () -> Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ){
        Button(
            modifier = Modifier.size(ButtonDefaults.LargeButtonSize),
            onClick = onNavigateToBasica //le asignamos la ruta al boton
        ){
            Icon(
                imageVector = Icons.Rounded.Phone,
                contentDescription = "Triggers phone action",
                modifier = iconModifier
            )
        }

    }
}

@Composable
fun TextExample(
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.device_shape)
    )
}

@Composable
fun CardExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onNavigateToHospital: () -> Unit
){
    AppCard(
        modifier = modifier,
        appImage = {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Message,
                contentDescription = "Triggers open message action",
                modifier = iconModifier
            )
        },
        appName = { Text("Messages") },
        time = { Text("12km") },
        title = { Text("Kim Green") },
        onClick = onNavigateToHospital
    ){
        Text("On my way")
    }
}

@Composable
fun ChipExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    onNavigateToSueldoTrabajador: () -> Unit
){
    Chip(
        modifier = modifier,
        onClick = onNavigateToSueldoTrabajador,
        label = {
            Text(
                "5 minute Meditation",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.SelfImprovement,
                contentDescription = "Triggers meditation action",
                modifier = iconModifier
            )
        }
    )
}

@Composable
fun ToggleChipExample(
    modifier: Modifier = Modifier
){
    var checked by remember { mutableStateOf(true) }
    ToggleChip(
        modifier = modifier,
        checked = checked,
        toggleControl = {
            Switch(
                checked = checked,
                modifier = Modifier.semantics{
                    this.contentDescription = if(checked) "On" else "Off"
                }
            )
        },
        onCheckedChange = {
            checked = it
        },
        label = {
            Text(
                text = "Sound",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )

}