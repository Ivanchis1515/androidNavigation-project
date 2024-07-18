package com.example.navegacion.presentation.SueldoTrabajador

//layouts
import androidx.compose.foundation.layout.Box //caja
import androidx.compose.foundation.layout.Row //renglon
import androidx.compose.foundation.layout.Column //columnas
import androidx.compose.foundation.layout.Spacer //espacios
import androidx.compose.foundation.layout.height //aumenta el alto
import androidx.compose.foundation.layout.fillMaxSize //aumenta el ancho
import androidx.compose.foundation.layout.padding //modifier padding
import androidx.compose.foundation.layout.fillMaxWidth //ocupa el maximo tamaño horizontalmente
import androidx.compose.foundation.layout.size //define el tamaño general
import androidx.compose.foundation.layout.width //tamaño horizontal
import androidx.compose.foundation.layout.Arrangement //disposicion
//funcion composable
import androidx.compose.runtime.Composable //crea funciones renderizables
import androidx.wear.compose.material.MaterialTheme //modifier tema actual del dispositivo
import androidx.wear.compose.material.TimeText //hora actual del sistema
import com.example.navegacion.presentation.theme.NavegacionTheme //tema actual de la aplicacion
import androidx.compose.runtime.remember //recuerda las variables
import androidx.compose.runtime.mutableStateOf //para variables de tipo actualizables
import androidx.compose.runtime.getValue //retoma el valor de la variable mutable
import androidx.compose.runtime.setValue //actualiza el valor de la variable mutable
import androidx.compose.ui.platform.LocalContext //contexto de la aplicacion
import android.widget.Toast //widget toas
//componentes
import androidx.wear.compose.material.Text //texto
import androidx.compose.foundation.text.BasicTextField //textfield sencillo
import androidx.compose.ui.text.TextStyle //estilo del texto del textfield
import androidx.wear.compose.material.Button //boton
import androidx.compose.material3.Card //carta de material3
//modifier extensions
import androidx.compose.ui.Modifier //modifier elements
import androidx.compose.foundation.background //modifier fondo
import androidx.compose.foundation.border //bordes
import androidx.compose.foundation.clickable //evento clickeable
import androidx.compose.foundation.shape.CircleShape //formas
import androidx.compose.foundation.shape.RoundedCornerShape //cuadros redondeados
import androidx.compose.foundation.text.KeyboardOptions //opciones de teclado
import androidx.compose.ui.text.input.KeyboardType //tipo de teclado
import androidx.wear.compose.material.ButtonDefaults //boton con configuracion
import androidx.compose.material3.CardDefaults //carta con configuracion
import androidx.compose.ui.Alignment //modifier alineacion
import androidx.compose.ui.draw.clip //modifica la estructura
import androidx.compose.ui.graphics.Color //colroes
import androidx.compose.ui.text.font.FontFamily //tipo de familia de fuente
import androidx.compose.ui.text.font.FontStyle //estilo de fuente
import androidx.compose.ui.text.font.FontWeight //tipo de letra
import androidx.compose.ui.text.style.TextAlign //alineacion del texto
import androidx.compose.ui.text.style.TextDecoration //decoracion de texto
import androidx.compose.ui.unit.dp //medidas de espacio
import androidx.compose.ui.unit.sp //medidas de letra
//models
import com.example.navegacion.presentation.SueldoTrabajador.models.SueldoNuevo

@Composable
fun Sueldo(
    onNavigateToScaffold: () -> Unit
){
    NavegacionTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ){
            TimeText()
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                CapturaCategoriaSueldo(onNavigateToScaffold) //envia el parametro de pantalla
            }
        }
    }
}

@Composable
fun CapturaCategoriaSueldo(
    onNavigateToScaffold: () -> Unit
){
    var sueldo by remember { mutableStateOf("") } //variable para guardar el sueldo
    var categoria by remember { mutableStateOf("") } //variable que guarda la categoria
    Box(
        modifier = Modifier
            .padding(start = 35.dp, top = 30.dp, end = 35.dp, bottom = 0.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(65.dp))
            .border(width = 2.dp, shape = CircleShape, color = Color.Yellow)
            .background(Color(0xFF91CBFF))
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Capture Sueldo",
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )//text
            Spacer(modifier = Modifier.height(5.dp))
            BasicTextField(
                value = sueldo, //el valor escrito sera el sueldo
                onValueChange = {
                    sueldo = it //el sueldo sera el valor del text
                },
                modifier = Modifier
                    .background(Color.White)
                    .align(alignment = Alignment.CenterHorizontally),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = Color.Red,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            )//basic

            Text(
                text = "Capture Categoria",
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )//text
            Spacer(modifier = Modifier.height(5.dp))
            BasicTextField(
                value = categoria, //el valor escrito sera la categoria
                onValueChange = {
                    categoria = it //la categoria sera el valor del text
                },
                modifier = Modifier
                    .background(Color.White)
                    .align(alignment = Alignment.CenterHorizontally),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = Color.Red,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            )//basic
            NuevoSaldo(sueldo, categoria, onNavigateToScaffold)//se mandan los datos a la funcion consiguiente
        }//colum
    }//box
}

@Composable
fun NuevoSaldo(
    sueldo: String,
    categoria: String,
    onNavigateToScaffold: () -> Unit
) {
    val sueldoNuevo = remember { SueldoNuevo() } //creando una instancia del metodo de la clase sueldo
    val contextToast = LocalContext.current.applicationContext //contexto de la aplicacion
    Spacer(modifier = Modifier.height(5.dp))
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colors.onSurfaceVariant
                ),
                modifier = Modifier
                    .size(width = 85.dp, height = 22.dp)
            ) {
                Text(
                    text = "Aumento:$${sueldoNuevo.Aumento}", //llamar a la propiedad
                    modifier = Modifier
                        .padding(6.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.Blue),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 8.sp
                )
            }//card
        }//row
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colors.onSurfaceVariant
                ),
                modifier = Modifier
                    .size(width = 85.dp, height = 22.dp)
            ) {
                Text(
                    text = "Aumento:$${sueldoNuevo.SueldoConAumento}", //llamar a la propiedad
                    modifier = Modifier
                        .padding(6.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = Color.Blue),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 8.sp
                )
            }//card
        }//row
        Box(
            modifier = Modifier
                .padding(start = 35.dp, top = 3.dp, end = 35.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(15.dp)
                .background(Color(0xFFC1E0FF))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .padding(0.dp, 3.dp)
                        .width(75.dp)
                        .height(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Red,
                        backgroundColor = Color.Yellow
                    ),
                    onClick = {
                        try{
                            var suel = 0.0
                            var cat = 0
                            suel = sueldo.toDouble()
                            cat = categoria.toInt()
                            //llamamos a la "variable metodo" con sus propiedades
                            sueldoNuevo.CalcularAumento(suel, cat)
                            sueldoNuevo.CalcularSueldo(suel, cat)
                        } catch( e: NumberFormatException){
                            Toast.makeText(
                                contextToast,
                                "Sueldo o categoria deben ser valores numericos",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch(e: IllegalArgumentException){
                            Toast.makeText(contextToast, e.message, Toast.LENGTH_LONG).show()
                        }
                    }) {
                    Text(
                        text = "Calcular",
                        fontSize = 8.sp
                    )
                }//button
            }//row
        }//box
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "Regresar a inicio",
                fontSize = 8.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable(onClick = onNavigateToScaffold)
            )
        }
    }//colum
}//funcion