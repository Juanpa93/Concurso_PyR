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
import com.juanpapinzonc.concursopyr.databinding.ActivityMainBinding
import java.sql.Types
import java.util.*

class MainActivity : AppCompatActivity() {
     private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val nivel = preferences.getInt(getString(R.string.nivel), 1)
        var contador = preferences.getInt((getString(R.string.contador)),1)

        val preguntaDAO = ConcursoPyR.database.PreguntasDAO()
        var opcSeleccionada = ""
        var valorAleatorio = valorRandom(1..6)

        var GanadorDAO = ConcursoPyR.database2.GanadoresDAO()

        binding.ivTrofeo.visibility = View.GONE
        binding.tvFelicitacionGanador.visibility = View.GONE
        binding.tilNombreCampeon.visibility = View.GONE
        binding.tilDocumentoCampeon.visibility = View.GONE
        binding.btContinuarSalonDeGanadores.visibility = View.GONE

        if(nivel!=6) {
            Toast.makeText(this, "Nivel $nivel", Toast.LENGTH_LONG).show()
        }
        if(nivel==2) {
            valorAleatorio += 5
        }else{
            if(nivel == 3){
                valorAleatorio += 10
            }else{
                if(nivel == 4){
                    valorAleatorio += 15
                }else{
                    if(nivel == 5){
                        valorAleatorio += 20
                    }else{
                        if(nivel == 6){
                            binding.tvPreguntaUno.visibility = View.GONE
                            binding.ivPreguntaUno.visibility = View.GONE
                            binding.tvRespuestaUno.visibility = View.GONE
                            binding.tvRespuestaDos.visibility = View.GONE
                            binding.tvRespuestaTres.visibility = View.GONE
                            binding.tvRespuestaCuatro.visibility = View.GONE
                            binding.ivRespuestaUno.visibility = View.GONE
                            binding.ivRespuestaDos.visibility = View.GONE
                            binding.ivRespuestaTres.visibility = View.GONE
                            binding.ivRespuestaCuatro.visibility = View.GONE
                            binding.ivTrofeo.visibility = View.VISIBLE
                            binding.tvFelicitacionGanador.visibility = View.VISIBLE
                            binding.tilNombreCampeon.visibility = View.VISIBLE
                            binding.tilDocumentoCampeon.visibility = View.VISIBLE
                            binding.btContinuarSalonDeGanadores.visibility = View.VISIBLE
                            preferences.edit().putInt(getString(R.string.nivel), 1).apply()
                        }
                    }
                }
            }
        }

        binding.btContinuarSalonDeGanadores.setOnClickListener {
            if(binding.etNombreCampeon.text.isNullOrEmpty()){
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }else{
                if(binding.etNombreCampeon.text.isNullOrEmpty()){
                    Toast.makeText(this, "Ingrese un documento", Toast.LENGTH_SHORT).show()
                }else{
                    val ganadorBuscar =GanadorDAO.buscarGanadores(binding.etDocumentoCampeon.text.toString())

                    if(ganadorBuscar == null){
                        val ganador = Ganadores(
                            Types.NULL,
                            binding.etNombreCampeon.text.toString(),
                            binding.etDocumentoCampeon.text.toString(),
                            "50000",
                            true,
                            contador
                        )
                        preferences.edit().putInt(getString(R.string.contador), contador + 1).apply()
                        GanadorDAO.crearGanadores(ganador)
                    }else{
                        MaterialAlertDialogBuilder(this)
                            .setTitle("El documento ya existe")
                            .setNegativeButton("Cancelar", null)
                            .setPositiveButton(R.string.actualizar, { dialogInterface, i ->
                                Toast.makeText(this, "En construcción", Toast.LENGTH_SHORT).show()
                            }).show()
                    }
                    startActivity(Intent(this, SalonGanadoresActivity::class.java))
                    finish()
                    }
            }



        }

        val pregunta = preguntaDAO.buscarPreguntas(valorAleatorio.toString())
        if(pregunta != null){
            binding.tvPreguntaUno.setText(pregunta.pregunta)
            binding.tvRespuestaUno.setText(pregunta.opcionUno)
            binding.tvRespuestaDos.setText(pregunta.opcionDos)
            binding.tvRespuestaTres.setText(pregunta.opcionTres)
            binding.tvRespuestaCuatro.setText(pregunta.opcionCuatro)
            //Toast.makeText(this, "Pregunta ${pregunta.numeroPregunta} opc correcta ${pregunta.opcionCorrecta}", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Pregunta no existe", Toast.LENGTH_SHORT).show()
        }
      binding.ivRespuestaUno.setOnClickListener {
          opcSeleccionada = "1"
          var opcCorrecta = pregunta.opcionCorrecta
          if(opcSeleccionada == opcCorrecta){
              Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
              preferences.edit().putInt(getString(R.string.nivel), nivel + 1).apply()
              startActivity(Intent(this, MainActivity::class.java))
              finish()
          }else{
              Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
          }
      }
        binding.ivRespuestaDos.setOnClickListener {
            opcSeleccionada = "2"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivRespuestaTres.setOnClickListener {
            opcSeleccionada = "3"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivRespuestaCuatro.setOnClickListener {
            opcSeleccionada = "4"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun valorRandom(valores: IntRange) : Int {
        var r = Random()
        var valorRandom = r.nextInt(valores.last - valores.first) + valores.first
        return valorRandom
    }
}