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
        var premio = preferences.getInt(getString(R.string.premio), 0)
        var contador = preferences.getInt((getString(R.string.contador)),1)
        val preguntaDAO = ConcursoPyR.database.PreguntasDAO()
        var opcSeleccionada = ""
        var valorAleatorio = valorRandom(1..6)
        var premioGuardar = 0

        var GanadorDAO = ConcursoPyR.database2.GanadoresDAO()

        binding.ivTrofeo.visibility = View.GONE
        binding.tvFelicitacionGanador.visibility = View.GONE
        binding.tilNombreCampeon.visibility = View.GONE
        binding.tilDocumentoCampeon.visibility = View.GONE
        binding.btContinuarSalonDeGanadores.visibility = View.GONE
        binding.tvPuntajeGanador.visibility = View.GONE

        when(nivel){
            1 -> binding.tvPremioJugador.setText("$premio xp")
            2 -> binding.tvPremioJugador.setText("$premio xp")
            3 -> binding.tvPremioJugador.setText("$premio xp")
            4 -> binding.tvPremioJugador.setText("$premio xp")
            5 -> binding.tvPremioJugador.setText("$premio xp")
        }

        if(nivel!=6) {
            binding.tvNivelJugador.setText("Nivel $nivel")
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
                            binding.llDatosJugador.visibility = View.GONE
                            binding.tvPuntajeGanador.visibility = View.VISIBLE
                            binding.ivTrofeo.visibility = View.VISIBLE
                            binding.tvFelicitacionGanador.visibility = View.VISIBLE
                            binding.tilNombreCampeon.visibility = View.VISIBLE
                            binding.tilDocumentoCampeon.visibility = View.VISIBLE
                            binding.btContinuarSalonDeGanadores.visibility = View.VISIBLE
                            binding.tvPuntajeGanador.setText("100000 xp")
                            preferences.edit().putInt(getString(R.string.nivel), 1).apply()
                            premioGuardar = premio
                            preferences.edit().putInt(getString(R.string.premio), 0).apply()

                        }
                    }
                }
            }
        }

        binding.btRetirarseJuego.setOnClickListener {
            if(nivel != 1){    binding.tvPreguntaUno.visibility = View.GONE
                binding.ivPreguntaUno.visibility = View.GONE
                binding.tvRespuestaUno.visibility = View.GONE
                binding.tvRespuestaDos.visibility = View.GONE
                binding.tvRespuestaTres.visibility = View.GONE
                binding.tvRespuestaCuatro.visibility = View.GONE
                binding.ivRespuestaUno.visibility = View.GONE
                binding.ivRespuestaDos.visibility = View.GONE
                binding.ivRespuestaTres.visibility = View.GONE
                binding.ivRespuestaCuatro.visibility = View.GONE
                binding.llDatosJugador.visibility = View.GONE
                binding.tvPuntajeGanador.visibility = View.VISIBLE
                binding.ivTrofeo.visibility = View.VISIBLE
                binding.tilNombreCampeon.visibility = View.VISIBLE
                binding.tilDocumentoCampeon.visibility = View.VISIBLE
                binding.btContinuarSalonDeGanadores.visibility = View.VISIBLE
                binding.tvPuntajeGanador.setText("$premio xp")
                premioGuardar = premio
                preferences.edit().putInt(getString(R.string.nivel), 1).apply()
                preferences.edit().putInt(getString(R.string.premio), 0).apply()
            }else{
                Toast.makeText(this, "Hasta luego...", Toast.LENGTH_SHORT).show()
                finish()
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
                    var esCampeon : Boolean = false
                    if(premioGuardar == 100000) esCampeon = true
                    if(ganadorBuscar == null){
                        val ganador = Ganadores(
                            Types.NULL,
                            binding.etNombreCampeon.text.toString(),
                            binding.etDocumentoCampeon.text.toString(),
                            premioGuardar.toString(),
                            esCampeon,
                            contador
                        )
                        preferences.edit().putInt(getString(R.string.contador), contador + 1).apply()
                        GanadorDAO.crearGanadores(ganador)
                        startActivity(Intent(this, SalonGanadoresActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "El documento ya existe", Toast.LENGTH_SHORT).show()
                    }
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
              preferences.edit().putInt(getString(R.string.premio), Math.pow(10.0, nivel.toDouble()).toInt()).apply()
              startActivity(Intent(this, MainActivity::class.java))
              finish()
          }else{
              Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
              startActivity(Intent(this, SplashActivity::class.java))
              finish()

          }
      }
        binding.ivRespuestaDos.setOnClickListener {
            opcSeleccionada = "2"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                preferences.edit().putInt(getString(R.string.premio), Math.pow(10.0, nivel.toDouble()).toInt()).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
        binding.ivRespuestaTres.setOnClickListener {
            opcSeleccionada = "3"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                preferences.edit().putInt(getString(R.string.premio), Math.pow(10.0, nivel.toDouble()).toInt()).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
        binding.ivRespuestaCuatro.setOnClickListener {
            opcSeleccionada = "4"
            val opcCorrecta = pregunta.opcionCorrecta
            if(opcSeleccionada == opcCorrecta){
                Toast.makeText(this, "CORRECTO", Toast.LENGTH_SHORT).show()
                preferences.edit().putInt(getString(R.string.nivel), nivel+1).apply()
                preferences.edit().putInt(getString(R.string.premio), Math.pow(10.0, nivel.toDouble()).toInt()).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "INCORRECTO", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
    }
    fun valorRandom(valores: IntRange) : Int {
        var r = Random()
        var valorRandom = r.nextInt(valores.last - valores.first) + valores.first
        return valorRandom
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Hasta luego...", Toast.LENGTH_SHORT).show()
        val preferences = getPreferences(Context.MODE_PRIVATE)
        preferences.edit().putInt(getString(R.string.nivel), 1).apply()
        preferences.edit().putInt(getString(R.string.premio), 0).apply()
        finish()
    }

    override fun onStop() {
        super.onStop()
        val preferences = getPreferences(Context.MODE_PRIVATE)
        preferences.edit().putInt(getString(R.string.nivel), 1).apply()
        preferences.edit().putInt(getString(R.string.premio), 0).apply()
    }
}
