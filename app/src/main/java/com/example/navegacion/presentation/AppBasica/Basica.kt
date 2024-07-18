package com.example.navegacion.presentation.AppBasica

//layouts
import androidx.compose.foundation.background //fondo
import androidx.compose.foundation.clickable //texto tipo <a></a>
import androidx.compose.foundation.layout.Box //caja
import androidx.compose.foundation.layout.Column //columnas
import androidx.compose.foundation.layout.Spacer //espaciado
import androidx.compose.foundation.layout.fillMaxSize //maximo tamaño
import androidx.compose.foundation.layout.fillMaxWidth //maximo tamaño ancho
import androidx.compose.foundation.layout.height //alto del elemento
import androidx.compose.foundation.layout.padding //padding del elemento
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment //alineacion
//componentes
import androidx.compose.foundation.text.BasicTextField //textfield sencillo
import androidx.compose.foundation.text.KeyboardOptions //propiedades para el textfiedl
import androidx.compose.material3.DropdownMenu //lista desplegable
import androidx.compose.material3.DropdownMenuItem //lista desplegable elementos
import androidx.compose.ui.text.input.KeyboardType //teclado de tipo
import androidx.wear.compose.material.Button //boton
import androidx.wear.compose.material.ButtonDefaults //boton por defecto
import androidx.wear.compose.material.Text //texto
//funcion composable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf //cambia variables en tiempo real
import androidx.compose.runtime.getValue //obten el valor
import androidx.compose.runtime.setValue //modifica el valor
import androidx.compose.runtime.remember //recuerda variables remember
import androidx.compose.ui.text.font.FontWeight //fuente de letra
import com.example.navegacion.presentation.theme.NavegacionTheme //tema de la aplicacion
//modificables de la aplicacion
import androidx.compose.ui.Modifier //modifier
import androidx.compose.ui.graphics.Color //colores
import androidx.compose.ui.platform.LocalContext //contexto de la aplicacion
import android.widget.Toast //widget toas
import androidx.compose.ui.text.TextStyle //estilo del texto
import androidx.compose.ui.text.font.FontFamily //tipo de familia
import androidx.compose.ui.text.font.FontStyle //tipo de fuente
import androidx.compose.ui.text.style.TextAlign //alineacion del texto
import androidx.compose.ui.text.style.TextDecoration //decoraciones?
import androidx.compose.ui.unit.dp //unidades de espacio
import androidx.compose.ui.unit.sp //unidades de agrandamiento
import androidx.wear.compose.material.TimeText //horario en la parte superior

@Composable
fun Basica(
    onNavigateToScaffold: () -> Unit
){
    NavegacionTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF75BAFF)),
            contentAlignment = Alignment.Center
        ){
            TimeText()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextoBoton()
                ListaDesplegable(onNavigateToScaffold)
            }
        }
    }
}

@Composable
fun TextoBoton(){
    var textFieldValue by remember { mutableStateOf("") }
    var res by remember { mutableStateOf("Resultado") }
    val contextToast = LocalContext.current.applicationContext

    Spacer(modifier = Modifier.height(20.dp))
    Text(
        "Capture un valor",
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
        color = Color.Blue
    )
    Spacer(modifier = Modifier.height(5.dp))
    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
        },
        modifier = Modifier
            .background(Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            color = Color.Red,
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    Button(
        modifier = Modifier
            .size(width = 50.dp, height = 30.dp),
        onClick = {
            res = textFieldValue
            Toast.makeText(contextToast, "resp: $res", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red,
            backgroundColor = Color.Yellow
        )
    ){
        Text(
            "Aceptar",
            fontSize = 10.sp
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Text(
        text = res,
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ListaDesplegable(
    onNavigateToScaffold: () -> Unit
){
    var mExpanded by remember { mutableStateOf(false) }
    val mCities = listOf("Delhi", "Mumbai", "Cheenai", "kolkata")
    var mSelectedText by remember { mutableStateOf("") }
    Spacer(Modifier.size(4.dp))
    Button(
        modifier = Modifier
            .padding(0.dp, 1.dp)
            .width(40.dp)
            .height(14.dp),
        onClick= {
            mExpanded = true
        }
    ){
        Text(
            "Menu",
            fontSize = 10.sp
        )
    }
    DropdownMenu(
        expanded = mExpanded, //verifica el estado del boton para expandir la lista
        onDismissRequest = { mExpanded = false }, //se presiona en cualquiero otro lado cierra la lista
        modifier = Modifier
            .fillMaxWidth()
    ){
        mCities.forEach{item ->
            DropdownMenuItem(
                text = {
                    Text(
                        item,
                        modifier = Modifier,
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                },
                onClick = {
                    mSelectedText = item //selecciona el elemento que fue presionado
                    mExpanded = false  //cierra la lista
                },
                modifier = Modifier
                    .height(20.dp)
                    .padding(horizontal = 35.dp, 0.dp)
            )
        }
    }
    Spacer(Modifier.size(4.dp))
    Text(
        text = mSelectedText, //la opcion seleccioanda colocala como texto
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
    )
    Spacer(Modifier.size(4.dp))
    Text(
        text = "Regresar",
        fontSize = 10.sp,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .clickable(onClick = onNavigateToScaffold) //texto clickeable para regresar a la pantalla principal
    )
}