package com.example.appclima
/*
class Location(name:String) {

    var name:String = ""

    init {
        this.name = name
    }
}*/

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Long,
    val localtime: String
)