package com.example.navegacion.presentation.AppHospital

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
import androidx.compose.material3.DropdownMenu //lista desplegable
import androidx.compose.material3.DropdownMenuItem //lista desplegable elementos
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
import androidx.compose.ui.graphics.RectangleShape //forma rectangular
import androidx.compose.ui.text.font.FontFamily //tipo de familia de fuente
import androidx.compose.ui.text.font.FontStyle //estilo de fuente
import androidx.compose.ui.text.font.FontWeight //tipo de letra
import androidx.compose.ui.text.style.TextAlign //alineacion del texto
import androidx.compose.ui.text.style.TextDecoration //decoracion de texto
import androidx.compose.ui.unit.dp //medidas de espacio
import androidx.compose.ui.unit.sp //medidas de letra
//models
import com.example.navegacion.presentation.AppHospital.models.Hospital //clase para hacer los calulos

@Composable
fun Hospital(
    onNavigateToScaffold: () -> Unit
){
    NavegacionTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            TimeText()//hora del sistema
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CapturaPresupuesto()
                ListaDesplegable()
                RepartoMuestraMonto(onNavigateToScaffold) //envia el parametro de pantalla
            }
        }
    }
}

//array de valores (guardan los datos de la interfaz)
var valoresInterfaz: Array<String> = arrayOf(
    "", //aqui se almacena el monto
    "" //aqui se almacena el departamento
)

@Composable
fun CapturaPresupuesto(){
    var textFieldvalue by remember { mutableStateOf("") } //guarda el valor del textfield

    Box(
        modifier = Modifier
            .padding(start = 35.dp, top = 30.dp, end = 35.dp, bottom = 0.dp)
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(50.dp))
            .border(width = 2.dp, shape = CircleShape, color = Color.Yellow)
            .background((Color(0xFF91C8FF)))
    ) {
        Column {
            Text(
                text = "Capture monto",
                fontSize = 10.sp,
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
                value = textFieldvalue, //el valor sera el de la variable
                onValueChange = {
                    //cuando cambie
                    textFieldvalue = it //el valor de la variable sera el ingresado
                    valoresInterfaz[0] = textFieldvalue //guarda el valor en la primera posicion del array
                },
                modifier = Modifier
                    .background(Color.White)
                    .align(alignment = Alignment.CenterHorizontally),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = Color.Red,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )//TextStyle
            )//BasicTextField
        }//column
    }//box
}

@Composable
fun ListaDesplegable(){
    var mExpanded by remember { mutableStateOf(false) } //variable para abrir la lista
    val mCites = listOf("Pediatria", "Cardiologia", "Urgencias") //array de elementos
    var mSelectedText by remember { mutableStateOf("") } //variable para atrapar el texto

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(4.dp))
        Button(
            modifier = Modifier
                .padding(0.dp, 1.dp)
                .width(75.dp)
                .height(20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black, backgroundColor = Color(0xFFFFFFDE)
            ),
            onClick = {
                mExpanded = true //al hacer click cambia el valor de la variable
            }
        ) {
            Text(
                text = "Departamento",
                fontSize = 10.sp
            )
        }//button
        DropdownMenu(
            expanded = mExpanded, //verifica si la variable esta en true, expande la lista
            onDismissRequest = { mExpanded = false }, //si se preciona en cualquier lado cierra la lista y cambia el estado
            modifier = Modifier
                .fillMaxWidth()
        ) {
            mCites.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            item,
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    },
                    onClick = {
                        mSelectedText = item //el texto sera igual al elemento seleccionado
                        mExpanded = false //cambia el estado de la lista
                        valoresInterfaz[1] = mSelectedText //guarda el valor en la segunda posicion del array
                    },
                    modifier = Modifier
                        .height(15.dp)
                        .padding(horizontal = 35.dp, 0.dp)
                )
            }//forEch
        }//dropmenu
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = mSelectedText, //el texto sera el elemento elegido de la lista
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun RepartoMuestraMonto(
    onNavigateToScaffold: () -> Unit //recibe la ruta de pantalla
){
    var repartoT by remember { mutableStateOf("") } //variable para asignar el reparto
    val contextToast = LocalContext.current.applicationContext //contexto de la aplicacion

    Spacer(modifier = Modifier.height(10.dp))
    Column {
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
                    .size(85.dp, height = 30.dp),
            ) {
                Text(
                    text = "Asignacion:$$repartoT", //usa la variable para contener el resultado
                    modifier = Modifier
                        .padding(6.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.Blue,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 8.sp
                    )
                )//text
            }//card
        }//row
        Box(
            modifier = Modifier
                .padding(start = 35.dp, top = 10.dp, end = 35.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(17.dp)
                .background(Color(0xFFC1E0FF))
                .border(width = 1.dp, shape = RectangleShape, color = Color.Black)
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
                        .height(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Red,
                        backgroundColor = Color.Yellow
                    ),
                    onClick = {
                        try {
                            //Aqui se hara uso del constructor de la clase
                            //la captura y pulsaciones en la interfaz se enviaran a la clase
                            //a traves de su constructor, estos valores se almacenaran
                            //previamente en el arreglo
                            val presupuesto = valoresInterfaz[0].toDouble() //toma el presupuesto del primer elemento del array
                            val departamento = valoresInterfaz[1] //toma el departamento del segundo valor del array
                            val hospital = Hospital(presupuesto, departamento)//importa la clase y manda los datos al constructor
                            repartoT = hospital.CalculaReparto().toString() //asigna la respuesta del metodo
                        }
                        catch (e:NumberFormatException){
                            Toast.makeText(
                                contextToast,
                                "Presupuesto es un valor numerico",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: IllegalArgumentException){
                            Toast.makeText(contextToast, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }) {
                    Text(text = "Calcular",
                        fontSize = 10.sp)
                }
            }//row
        }//box
        Spacer(Modifier.size(3.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "Regresar a inicio",
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable(onClick = onNavigateToScaffold)
            )
        }
    }//colum
}