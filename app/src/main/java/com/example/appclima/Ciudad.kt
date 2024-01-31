package com.example.appclima

/*class Ciudad(location:ArrayList<Location>, current:ArrayList<Current>)  {

    //var nombre:String = ""
    var location:ArrayList<Location>? = null
    var current:ArrayList<Current>? = null

    init {
       //this.nombre = nombre
        this.location = location
        this.current = current
    }
}*/
data class Ciudad(
    val location: Location?,
    val current: Current?
)