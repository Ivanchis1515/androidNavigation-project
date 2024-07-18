package com.example.navegacion.presentation.SueldoTrabajador.models

//variables mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue //variable para obtener el valor
import androidx.compose.runtime.setValue //variable para actualizar el valor

class SueldoNuevo {
    //declarando atributos de clase tipo stateof (tipo de datos que cambian)
    //propiedades
    var Aumento by mutableStateOf(0.0) //almacena el aumento de sueldo
    var SueldoConAumento by mutableStateOf(0.0) //almacena el nuevo sueldo

    fun CalcularAumento(sueldo:Double, Cat:Int){
        if(sueldo <= 0) throw IllegalArgumentException("Capture sueldo positivo")
        else{
            when(Cat){
                1 -> Aumento = sueldo * 0.5
                2 -> Aumento = sueldo * 0.10
                3 -> Aumento = sueldo * 0.15
                4 -> Aumento = sueldo * 0.20
                else -> IllegalArgumentException("Categoria no valida, valores entre 1 y 4")
            }
        }
    }

    fun CalcularSueldo(sueldo: Double, Cat:Int){
        if(sueldo <= 0) throw IllegalArgumentException("Capture un sueldo positivo")
        else{
            when(Cat){
                1 -> SueldoConAumento = sueldo + (sueldo * 0.5)
                2 -> SueldoConAumento = sueldo + (sueldo * 0.10)
                3 -> SueldoConAumento = sueldo + (sueldo * 0.14)
                4 -> SueldoConAumento = sueldo + (sueldo * 0.20)
                else -> throw IllegalArgumentException("Categoria no valida, valores entre 1 y 4")
            }
        }
    }
}