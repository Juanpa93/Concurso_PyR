package com.juanpapinzonc.concursopyr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.juanpapinzonc.concursopyr.databinding.ActivityConfigurarBinding
import java.sql.Types

class ConfigurarActivity : AppCompatActivity() {

    private lateinit var binding2 : ActivityConfigurarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityConfigurarBinding.inflate(layoutInflater)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding2.root)

        val preguntasDAO = ConcursoPyR.database.PreguntasDAO()

        binding2.btGuardar.setOnClickListener {
            val opcPregunta = encontrarPreguntaSeleccionada()
            val preguntaBuscar = preguntasDAO.buscarPreguntas(opcPregunta)
            val opcionCorrecta = encontrarRespuestaSeleccionada()
            if(opcionCorrecta!="100" && opcPregunta!="0" && binding2.etPreguntaUno.text.toString().isNotEmpty()){
                if(preguntaBuscar!= null){
                    MaterialAlertDialogBuilder(this)
                        .setTitle("La pregunta ya existe")
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton(R.string.actualizar, { dialogInterface, i ->
                            val pregunta = Preguntas(
                                Types.NULL,
                                opcPregunta,
                                binding2.etPreguntaUno.text.toString(),
                                binding2.etRespuestaUno.text.toString(),
                                binding2.etRespuestaDos.text.toString(),
                                binding2.etRespuestaTres.text.toString(),
                                binding2.etRespuestaCuatro.text.toString(),
                                opcionCorrecta
                            )

                            preguntasDAO.actualizarPreguntas(pregunta)
                            Toast.makeText(this, "Pregunta actualizada satisfactoriamente", Toast.LENGTH_SHORT).show()
                        }).show()

                }else{
                    val opcionCorrecta = encontrarRespuestaSeleccionada()
                    val pregunta = Preguntas(
                        Types.NULL,
                        opcPregunta,
                        binding2.etPreguntaUno.text.toString(),
                        binding2.etRespuestaUno.text.toString(),
                        binding2.etRespuestaDos.text.toString(),
                        binding2.etRespuestaTres.text.toString(),
                        binding2.etRespuestaCuatro.text.toString(),
                        opcionCorrecta
                    )
                    preguntasDAO.crearPreguntas(pregunta)
                    Toast.makeText(this, "Pregunta creada satisfactoriamente", Toast.LENGTH_SHORT).show()
                }
                limpiarRadiobuttons()
            }else{
                if(opcPregunta=="0") {
                    Toast.makeText(this, "Seleccione una pregunta", Toast.LENGTH_SHORT).show()
                }else{
                    if(binding2.etPreguntaUno.text.toString().isEmpty()){
                        Toast.makeText(this, "Ingrese el texto de la pregunta", Toast.LENGTH_SHORT).show()
                    }else{
                        if(opcionCorrecta=="100") Toast.makeText(this, "Seleccione la respuesta correcta", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding2.btVerPregunta.setOnClickListener {
            val opcPregunta = encontrarPreguntaSeleccionada()
            val preguntaBuscar = preguntasDAO.buscarPreguntas(opcPregunta)
            if(preguntaBuscar!= null){
                if(opcPregunta !="0"){
                    binding2.etPreguntaUno.setText(preguntaBuscar.pregunta)
                    binding2.etRespuestaUno.setText(preguntaBuscar.opcionUno)
                    binding2.etRespuestaDos.setText(preguntaBuscar.opcionDos)
                    binding2.etRespuestaTres.setText(preguntaBuscar.opcionTres)
                    binding2.etRespuestaCuatro.setText(preguntaBuscar.opcionCuatro)
                    when(preguntaBuscar.opcionCorrecta){
                        "1" -> binding2.rbRUno.isChecked = true
                        "2" -> binding2.rbRDos.isChecked = true
                        "3" -> binding2.rbRTres.isChecked = true
                        "4" -> binding2.rbRCuatro.isChecked = true
                    }
                    Toast.makeText(this, "La pregunta $opcPregunta ahora esta visible", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "La pregunta $opcPregunta no se ha creado aún", Toast.LENGTH_SHORT).show()
                }
                limpiarRadiobuttons()
            } else{
                Toast.makeText(this, "Seleccione una pregunta", Toast.LENGTH_SHORT).show()
            }
                }



        binding2.rbPregunta1.setOnClickListener {
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta2.setOnClickListener {
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta3.setOnClickListener {
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta4.setOnClickListener {
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta5.setOnClickListener {
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta6.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta7.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta8.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta9.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta10.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta11.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta12.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta13.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta14.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta15.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat4()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta16.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta17.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta18.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta19.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta20.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat5()
        }
        binding2.rbPregunta21.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
        }
        binding2.rbPregunta22.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
        }
        binding2.rbPregunta23.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
        }
        binding2.rbPregunta24.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
        }
        binding2.rbPregunta25.setOnClickListener {
            limpiarRadioButtomsCat1()
            limpiarRadioButtomsCat2()
            limpiarRadioButtomsCat3()
            limpiarRadioButtomsCat4()
        }

    }

    private fun encontrarRespuestaSeleccionada(): String {
        var opcionCorrecta = "100"
        if(binding2.rbRUno.isChecked){
            opcionCorrecta = "1"
        }else {
            if (binding2.rbRDos.isChecked) {
                opcionCorrecta = "2"
            } else {
                if (binding2.rbRTres.isChecked) {
                    opcionCorrecta = "3"
                } else {
                    if (binding2.rbRCuatro.isChecked) {
                        opcionCorrecta = "4"
                    }
                }
            }
        }
        return  opcionCorrecta
    }


    private fun limpiarRadioButtomsCat1(){
        binding2.rgPrimeraCategoria.clearCheck()
    }
    private fun limpiarRadioButtomsCat2(){
        binding2.rgSegundaCategoria.clearCheck()
    }
    private fun limpiarRadioButtomsCat3(){
        binding2.rgTerceraCategoria.clearCheck()
    }
    private fun limpiarRadioButtomsCat4(){
        binding2.rgCuartaCategoria.clearCheck()
    }
    private fun limpiarRadioButtomsCat5(){
        binding2.rgQuintaCategoria.clearCheck()
    }
    private fun limpiarRadiobuttons() {
        binding2.rbPregunta1.isChecked = false
        binding2.rbPregunta2.isChecked = false
        binding2.rbPregunta3.isChecked = false
        binding2.rbPregunta4.isChecked = false
        binding2.rbPregunta5.isChecked = false
        binding2.rbPregunta6.isChecked = false
        binding2.rbPregunta7.isChecked = false
        binding2.rbPregunta8.isChecked = false
        binding2.rbPregunta9.isChecked = false
        binding2.rbPregunta10.isChecked = false
        binding2.rbPregunta11.isChecked = false
        binding2.rbPregunta12.isChecked = false
        binding2.rbPregunta13.isChecked = false
        binding2.rbPregunta14.isChecked = false
        binding2.rbPregunta15.isChecked = false
        binding2.rbPregunta16.isChecked = false
        binding2.rbPregunta17.isChecked = false
        binding2.rbPregunta18.isChecked = false
        binding2.rbPregunta19.isChecked = false
        binding2.rbPregunta20.isChecked = false
        binding2.rbPregunta21.isChecked = false
        binding2.rbPregunta22.isChecked = false
        binding2.rbPregunta23.isChecked = false
        binding2.rbPregunta24.isChecked = false
        binding2.rbPregunta25.isChecked = false
    }

    private fun encontrarPreguntaSeleccionada() :String {
        var opcPregunta : String = "0"
        if(binding2.rbPregunta1.isChecked) opcPregunta = "1"
        if(binding2.rbPregunta2.isChecked) opcPregunta = "2"
        if(binding2.rbPregunta3.isChecked) opcPregunta = "3"
        if(binding2.rbPregunta4.isChecked) opcPregunta = "4"
        if(binding2.rbPregunta5.isChecked) opcPregunta = "5"
        if(binding2.rbPregunta6.isChecked) opcPregunta = "6"
        if(binding2.rbPregunta7.isChecked) opcPregunta = "7"
        if(binding2.rbPregunta8.isChecked) opcPregunta = "8"
        if(binding2.rbPregunta9.isChecked) opcPregunta = "9"
        if(binding2.rbPregunta10.isChecked) opcPregunta = "10"
        if(binding2.rbPregunta11.isChecked) opcPregunta = "11"
        if(binding2.rbPregunta12.isChecked) opcPregunta = "12"
        if(binding2.rbPregunta13.isChecked) opcPregunta = "13"
        if(binding2.rbPregunta14.isChecked) opcPregunta = "14"
        if(binding2.rbPregunta15.isChecked) opcPregunta = "15"
        if(binding2.rbPregunta16.isChecked) opcPregunta = "16"
        if(binding2.rbPregunta17.isChecked) opcPregunta = "17"
        if(binding2.rbPregunta18.isChecked) opcPregunta = "18"
        if(binding2.rbPregunta19.isChecked) opcPregunta = "19"
        if(binding2.rbPregunta20.isChecked) opcPregunta = "20"
        if(binding2.rbPregunta21.isChecked) opcPregunta = "21"
        if(binding2.rbPregunta22.isChecked) opcPregunta = "22"
        if(binding2.rbPregunta23.isChecked) opcPregunta = "23"
        if(binding2.rbPregunta24.isChecked) opcPregunta = "24"
        if(binding2.rbPregunta25.isChecked) opcPregunta = "25"
        return opcPregunta
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}