package com.rubik.programaresistencia

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.rubik.programaresistencia.databinding.ActivityInicioBinding
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.app_bar_inicio.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.String

class InicioActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInicioBinding

    val cantidadBandas = arrayOf("4 Bandas","5 Bandas","6 Bandas")

    val banda1 = arrayOf("Ninguno","Negro 0","Marrón 1","Rojo 2","Naranja 3","Amarillo 4","Verde 5","Azul 6","Violeta 7","Gris 8","Blanco 9")
    val banda2 = arrayOf("Ninguno","Negro 0","Marrón 1","Rojo 2","Naranja 3","Amarillo 4","Verde 5","Azul 6","Violeta 7","Gris 8","Blanco 9")
    val banda3 = arrayOf("Ninguno","Negro 0","Marrón 1","Rojo 2","Naranja 3","Amarillo 4","Verde 5","Azul 6","Violeta 7","Gris 8","Blanco 9")

    val multiplicador = arrayOf("Ninguno","Negro x1 Ω","Marrón x10 Ω","Rojo x100 Ω","Naranja x1K Ω","Amarillo x10K Ω","Verde x100K Ω","Azul x1M Ω","Violeta x10M Ω","Gris x100M Ω","Blanco x1G Ω","Dorado x0,1 Ω","Plateado x0,01 Ω")
    val tolerancia = arrayOf("Ninguno","Marrón ±1%","Rojo ±2%","Verde ±0,5%","Azul ±0,25%","Violeta ±0,1%","Gris ±0,05%","Dorado ±5%","Plateado ±10%")
    val PPM = arrayOf("Ninguno","Marrón 100 ppm","Rojo 50 ppm","Naranja 15 ppm","Amarillo 25 ppm","Azul 10 ppm","Violeta 5 ppm")

    //Numeros para hacer calculos
    var num_cantidad_bandas = 0

    var num_banda1 = 999
    var num_banda2 = 999
    var num_banda3 = 999

    var num_multiplicador : Float = 0F
    var num_tolerancia : Float = 0F
    var num_ppm = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(app_bar_inicio.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_inicio)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        texto_resultados.isVisible = false
        texto_resultados2.isVisible = false
        texto_resultados3.isVisible = false
        botonVolver.isVisible = false

        val arrayAdapterCantidadBandas = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,cantidadBandas)
        val arrayAdapterBandas1 = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,banda1)
        val arrayAdapterBandas2 = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,banda2)
        val arrayAdapterBandas3 = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,banda3)
        val arrayAdapterMultiplicador = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,multiplicador)
        val arrayAdapterTolerancia = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,tolerancia)
        val arrayAdapterPPM = ArrayAdapter(this@InicioActivity,android.R.layout.simple_spinner_dropdown_item,PPM)

        spinerCantidadBandas.adapter = arrayAdapterCantidadBandas
        spinerBanda1.adapter = arrayAdapterBandas1
        spinerBanda2.adapter = arrayAdapterBandas2
        spinerBanda3.adapter = arrayAdapterBandas3
        spinerMultiplicador.adapter = arrayAdapterMultiplicador
        spinerTolerancia.adapter = arrayAdapterTolerancia
        spinerPPM.adapter = arrayAdapterPPM

        spinerCantidadBandas.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var CANTIDADbandas = spinerCantidadBandas.selectedItemPosition

                if (CANTIDADbandas == 0){
                    text_PPM.isVisible = false
                    spinerPPM.isVisible = false

                    text_banda3.isVisible = false
                    spinerBanda3.isVisible = false

                    num_cantidad_bandas = 4

                }else if (CANTIDADbandas == 1){
                    text_PPM.isVisible = false
                    spinerPPM.isVisible = false

                    text_banda3.isVisible = true
                    spinerBanda3.isVisible = true

                    num_cantidad_bandas = 5
                }else{
                    text_PPM.isVisible = true
                    spinerPPM.isVisible = true

                    text_banda3.isVisible = true
                    spinerBanda3.isVisible = true

                    num_cantidad_bandas = 6
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerBanda1.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var color = spinerBanda1.selectedItemPosition

                when (color) {
                    0 -> print("0")
                    1 -> num_banda1 = 0
                    2 -> num_banda1 = 1
                    3 -> num_banda1 = 2
                    4 -> num_banda1 = 3
                    5 -> num_banda1 = 4
                    6 -> num_banda1 = 5
                    7 -> num_banda1 = 6
                    8 -> num_banda1 = 7
                    9 -> num_banda1 = 8
                    10 -> num_banda1 = 9

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerBanda2.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var color = spinerBanda2.selectedItemPosition

                when (color) {
                    0 -> print("0")
                    1 -> num_banda2 = 0
                    2 -> num_banda2 = 1
                    3 -> num_banda2 = 2
                    4 -> num_banda2 = 3
                    5 -> num_banda2 = 4
                    6 -> num_banda2 = 5
                    7 -> num_banda2 = 6
                    8 -> num_banda2 = 7
                    9 -> num_banda2 = 8
                    10 -> num_banda2 = 9

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerBanda3.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var color = spinerBanda3.selectedItemPosition

                when (color) {
                    0 -> print("0")
                    1 -> num_banda3 = 0
                    2 -> num_banda3 = 1
                    3 -> num_banda3 = 2
                    4 -> num_banda3 = 3
                    5 -> num_banda3 = 4
                    6 -> num_banda3 = 5
                    7 -> num_banda3 = 6
                    8 -> num_banda3 = 7
                    9 -> num_banda3 = 8
                    10 -> num_banda3 = 9

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerMultiplicador.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var mult = spinerMultiplicador.selectedItemPosition

                when (mult) {
                    0 -> print("0")
                    1 -> num_multiplicador = 1F
                    2 -> num_multiplicador = 10F
                    3 -> num_multiplicador = 100F
                    4 -> num_multiplicador = 1000F
                    5 -> num_multiplicador = 10000F
                    6 -> num_multiplicador = 100000F
                    7 -> num_multiplicador = 1000000F
                    8 -> num_multiplicador = 10000000F
                    9 -> num_multiplicador = 100000000F
                    10 -> num_multiplicador = 1000000000F
                    11 -> num_multiplicador = 0.1F
                    12 -> num_multiplicador = 0.01F

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerTolerancia.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var tolerancia = spinerTolerancia.selectedItemPosition

                when (tolerancia) {
                    0 -> print("0")
                    1 -> num_tolerancia = 1F
                    2 -> num_tolerancia = 2F
                    3 -> num_tolerancia = 0.5F
                    4 -> num_tolerancia = 0.25F
                    5 -> num_tolerancia = 0.1F
                    6 -> num_tolerancia = 0.05F
                    7 -> num_tolerancia = 5F
                    8 -> num_tolerancia = 10F


                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinerPPM.onItemSelectedListener=object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, vista: View?, posicion: Int, id: Long) {
                var PPM = spinerPPM.selectedItemPosition

                when (PPM) {
                    0 -> print("0")
                    1 -> num_ppm = 100
                    2 -> num_ppm = 50
                    3 -> num_ppm = 15
                    4 -> num_ppm = 25
                    5 -> num_ppm = 10
                    6 -> num_ppm = 5
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        botonCalcular.setOnClickListener(){

            if (num_cantidad_bandas == 4 && num_banda1 != 999 && num_banda2 != 999 && num_multiplicador != 0F && num_tolerancia != 0F){
                text_cantidadBandas.isVisible = false
                text_banda1.isVisible = false
                text_banda2.isVisible = false
                text_banda3.isVisible = false
                text_multiplicador.isVisible = false
                text_tolerancia.isVisible = false
                text_PPM.isVisible = false
                view_azul.isVisible = false

                spinerCantidadBandas.isVisible = false
                spinerBanda1.isVisible = false
                spinerBanda2.isVisible = false
                spinerBanda3.isVisible = false
                spinerMultiplicador.isVisible = false
                spinerTolerancia.isVisible = false
                spinerPPM.isVisible = false
                botonVolver.isVisible = true
                texto_resultados.isVisible = true
                texto_resultados2.isVisible = true
                texto_resultados3.isVisible = false

                botonCalcular.isVisible = false

                var numero_banda = (num_banda1.toString()+num_banda2.toString())
                var numero_banda_final = numero_banda.toInt() * num_multiplicador

                var tipo_multiplicador = "K"

                when (num_multiplicador) {
                    1F -> {
                        tipo_multiplicador = ""

                    }
                    10F -> {
                        tipo_multiplicador = ""

                    }
                    100F -> {
                        tipo_multiplicador = ""

                    }
                    1000F -> {
                        tipo_multiplicador = "K"

                    }
                    10000F -> {
                        tipo_multiplicador = "K"

                    }
                    100000F -> {
                        tipo_multiplicador = "K"

                    }
                    1000000F -> {
                        tipo_multiplicador = "M"

                    }
                    10000000F -> {
                        tipo_multiplicador = "M"

                    }
                    100000000F -> {
                        tipo_multiplicador = "M"

                    }
                    1000000000F -> {
                        tipo_multiplicador = "G"

                    }
                    0.1F -> {
                        tipo_multiplicador = ""

                    }
                    0.01F -> {
                        tipo_multiplicador = ""

                    }
                }

                texto_resultados.text = ("Resultado: | ${numero_banda_final} Ω |")
                var minTolerancia = numero_banda_final - ((numero_banda_final * (num_tolerancia)) / 100)
                var maxTolerancia = numero_banda_final + ((numero_banda_final * (num_tolerancia)) / 100)
                val num_finalSE = String.format("%.0f", numero_banda_final)

                val num_minSE = String.format("%.0f", minTolerancia)
                val num_maxSE = String.format("%.0f", maxTolerancia)
                texto_resultados.text = ("${num_finalSE} Ω")
                texto_resultados2.text = ("Max: ${num_maxSE} Ω | Min: ${num_minSE} Ω")
                texto_resultados3.text = ("PPM: ${num_ppm}ppm")
            } else if (num_cantidad_bandas == 5 && num_banda1 != 999 && num_banda2 != 999 && num_banda3 != 999 && num_multiplicador != 0F && num_tolerancia != 0F){
                text_cantidadBandas.isVisible = false
                text_banda1.isVisible = false
                text_banda2.isVisible = false
                text_banda3.isVisible = false
                text_multiplicador.isVisible = false
                text_tolerancia.isVisible = false
                text_PPM.isVisible = false
                view_azul.isVisible = false

                spinerCantidadBandas.isVisible = false
                spinerBanda1.isVisible = false
                spinerBanda2.isVisible = false
                spinerBanda3.isVisible = false
                spinerMultiplicador.isVisible = false
                spinerTolerancia.isVisible = false
                spinerPPM.isVisible = false

                texto_resultados.isVisible = true
                texto_resultados2.isVisible = true
                botonVolver.isVisible = true
                texto_resultados3.isVisible = false

                botonCalcular.isVisible = false

                var numero_banda = (num_banda1.toString()+num_banda2.toString()+num_banda3.toString())
                var numero_banda_final = numero_banda.toFloat() * num_multiplicador

                var tipo_multiplicador = "K"

                when (num_multiplicador) {
                    1F -> {
                        tipo_multiplicador = ""

                    }
                    10F -> {
                        tipo_multiplicador = ""

                    }
                    100F -> {
                        tipo_multiplicador = ""

                    }
                    1000F -> {
                        tipo_multiplicador = "K"

                    }
                    10000F -> {
                        tipo_multiplicador = "K"

                    }
                    100000F -> {
                        tipo_multiplicador = "K"

                    }
                    1000000F -> {
                        tipo_multiplicador = "M"

                    }
                    10000000F -> {
                        tipo_multiplicador = "M"

                    }
                    100000000F -> {
                        tipo_multiplicador = "M"

                    }
                    1000000000F -> {
                        tipo_multiplicador = "G"

                    }
                    0.1F -> {
                        tipo_multiplicador = ""

                    }
                    0.01F -> {
                        tipo_multiplicador = ""

                    }
                }

                texto_resultados.text = ("Resultado: | ${numero_banda_final} Ω |")
                var minTolerancia = numero_banda_final - ((numero_banda_final * (num_tolerancia)) / 100)
                var maxTolerancia = numero_banda_final + ((numero_banda_final * (num_tolerancia)) / 100)
                val num_finalSE = String.format("%.0f", numero_banda_final)

                val num_minSE = String.format("%.0f", minTolerancia)
                val num_maxSE = String.format("%.0f", maxTolerancia)
                texto_resultados.text = ("${num_finalSE} Ω")
                texto_resultados2.text = ("Max: ${num_maxSE} Ω | Min: ${num_minSE} Ω")
                texto_resultados3.text = ("PPM: ${num_ppm}ppm")
            } else if (num_cantidad_bandas == 6 && num_banda1 != 999 && num_banda2 != 999 && num_banda3 != 999 && num_multiplicador != 0F && num_tolerancia != 0F && num_ppm != 0){
                text_cantidadBandas.isVisible = false
                text_banda1.isVisible = false
                text_banda2.isVisible = false
                text_banda3.isVisible = false
                text_multiplicador.isVisible = false
                text_tolerancia.isVisible = false
                text_PPM.isVisible = false
                view_azul.isVisible = false

                spinerCantidadBandas.isVisible = false
                spinerBanda1.isVisible = false
                spinerBanda2.isVisible = false
                spinerBanda3.isVisible = false
                spinerMultiplicador.isVisible = false
                spinerTolerancia.isVisible = false
                spinerPPM.isVisible = false
                botonVolver.isVisible = true
                texto_resultados.isVisible = true
                texto_resultados2.isVisible = true
                texto_resultados3.isVisible = true

                botonCalcular.isVisible = false

                var numero_banda = (num_banda1.toString()+num_banda2.toString()+num_banda3.toString())
                var numero_banda_final = numero_banda.toFloat() * num_multiplicador

                var tipo_multiplicador = "K"

                when (num_multiplicador) {
                    1F -> {
                        tipo_multiplicador = ""

                    }
                    10F -> {
                        tipo_multiplicador = ""

                    }
                    100F -> {
                        tipo_multiplicador = ""

                    }
                    1000F -> {
                        tipo_multiplicador = "K"

                    }
                    10000F -> {
                        tipo_multiplicador = "K"

                    }
                    100000F -> {
                        tipo_multiplicador = "K"

                    }
                    1000000F -> {
                        tipo_multiplicador = "M"

                    }
                    10000000F -> {
                        tipo_multiplicador = "M"

                    }
                    100000000F -> {
                        tipo_multiplicador = "M"

                    }
                    1000000000F -> {
                        tipo_multiplicador = "G"

                    }
                    0.1F -> {
                        tipo_multiplicador = ""

                    }
                    0.01F -> {
                        tipo_multiplicador = ""

                    }
                }



                var minTolerancia = numero_banda_final - ((numero_banda_final * (num_tolerancia)) / 100)
                var maxTolerancia = numero_banda_final + ((numero_banda_final * (num_tolerancia)) / 100)

                val num_finalSE = String.format("%.0f", numero_banda_final)

                val num_minSE = String.format("%.0f", minTolerancia)
                val num_maxSE = String.format("%.0f", maxTolerancia)
                texto_resultados.text = ("${num_finalSE} Ω")
                texto_resultados2.text = ("Max: ${num_maxSE} Ω | Min: ${num_minSE} Ω")
                texto_resultados3.text = ("PPM: ${num_ppm}ppm")

            }else{
                Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
            }


        }

        botonVolver.setOnClickListener(){
            text_cantidadBandas.isVisible = true
            text_banda1.isVisible = true
            text_banda2.isVisible = true

            text_multiplicador.isVisible = true
            text_tolerancia.isVisible = true

            view_azul.isVisible = true
            spinerCantidadBandas.isVisible = true
            spinerBanda1.isVisible = true
            spinerBanda2.isVisible = true

            spinerMultiplicador.isVisible = true
            spinerTolerancia.isVisible = true

            botonVolver.isVisible = false

            texto_resultados.isVisible = false
            texto_resultados2.isVisible = false
            texto_resultados3.isVisible = false

            botonCalcular.isVisible = true

            if (num_cantidad_bandas == 4){
                text_banda3.isVisible = false
                text_PPM.isVisible = false
                spinerBanda3.isVisible = false
                spinerPPM.isVisible = false
            } else if (num_cantidad_bandas == 5){
                text_banda3.isVisible = true
                text_PPM.isVisible = false
                spinerBanda3.isVisible = true
                spinerPPM.isVisible = false

            }else if (num_cantidad_bandas == 6){
                text_banda3.isVisible = true
                text_PPM.isVisible = true
                spinerBanda3.isVisible = true
                spinerPPM.isVisible = true
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.inicio, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_inicio)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}