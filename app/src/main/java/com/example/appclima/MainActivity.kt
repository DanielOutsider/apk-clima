package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // OBTIENE LOS PARAMETROS QUE LLEGARON DE LA VENTANA ANTERIOR
        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")


        // codifo para validar red
        if(Network.hayRed(this)){
            //Toast.makeText(this, "Si hay Red", Toast.LENGTH_SHORT).show()
            // key 739647d52807445687460956242901
            //solicitudHTTPVolley("http://api.weatherapi.com/v1/current.json?key=739647d52807445687460956242901&q=Lima&aqi=no")
            val baseUrl = "http://api.weatherapi.com/v1/current.json?key=739647d52807445687460956242901"
            val url = "$baseUrl&q=$ciudad&aqi=no"
            solicitudHTTPVolley(url)
        }else{
            Toast.makeText(this, "No hay una conexion a internet", Toast.LENGTH_SHORT).show()
        }

    }

    //Metodo para HTTP Volley
    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->
            try {
                Log.d("solicitudHTTPVolley", response)

                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)

                var tvCiudad = findViewById<TextView>(R.id.tvCiudad)
                var tvGrados = findViewById<TextView>(R.id.tvGrados)
                var tvEstatus = findViewById<TextView>(R.id.tvEstado)

                tvCiudad?.text = ciudad.location?.name
                tvGrados?.text = ciudad.current?.temp_c.toString() + "º"
                tvEstatus?.text = ciudad.current?.condition?.text

                Log.d("ciudad", response)
            }catch (e: Exception){

            }
        }, Response.ErrorListener {  })

        queue.add(solicitud)
    }
}