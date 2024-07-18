package com.example.navegacion.presentation.AppHospital.models

class Hospital {
    var _Presupuesto:Double
    var _Departamento:String

    //constructor secundario
    //enviamos los datos como si fuese una funcion
    constructor(Presupuesto:Double, Departamento:String){
        //retoma los datos enviados y los asigna a las propiedades
        this._Presupuesto = Presupuesto
        this._Departamento = Departamento
        //verifica los valores que llegan
        if (Presupuesto < 0) throw IllegalArgumentException("Captura presupuesto positivo") //si es menor a 0 lanza una excepcion
        if(Departamento == "") throw IllegalArgumentException("Selecciona departamento") //si viene vacio lanza una excepcion
    }


    fun CalculaReparto():Double{
        var total  = 0.0
        if(_Departamento == "Pediatria") total = _Presupuesto * 0.50 //calcula para pediatria
        if(_Departamento == "Cardiologia") total = _Presupuesto * 0.20 //calcula para cardiologia
        if(_Departamento == "Urgencias") total = _Presupuesto * 0.30 //calcula para urgencias
        return total //retorna el total
    }
}