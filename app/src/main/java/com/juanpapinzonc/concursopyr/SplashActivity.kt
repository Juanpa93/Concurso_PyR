package com.juanpapinzonc.concursopyr

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.juanpapinzonc.concursopyr.databinding.ActivitySplashBinding
import java.sql.Types

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        val preguntasDAO = ConcursoPyR.database.PreguntasDAO()

        binding.tvRegistroAdministrador.visibility = View.GONE
        binding.tilNombre.visibility = View.GONE
        binding.tilContrasena.visibility = View.GONE
        binding.tilConfirmarContrasena.visibility = View.GONE
        binding.rgOpciones.visibility = View.GONE
        binding.btGuardar.visibility = View.GONE
        binding.tvSaludo.visibility = View.GONE
        binding.tilValidarContrasena.visibility = View.GONE
        binding.btContinuarConfiguracion.visibility = View.GONE

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val newUser = preferences.getBoolean(getString(R.string.isNewUser), true)
        val nameUser = preferences.getString(getString(R.string.nameUser), " ")
        val contrasenaUser = preferences.getString(getString(R.string.passwordUser), " ")

        binding.btConfigurar.setOnClickListener {
            if(newUser){
                MaterialAlertDialogBuilder(this)
                    .setTitle("No existe un administrador")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton(R.string.registrar, { dialogInterface, i ->
                        binding.tvInicial.visibility = View.GONE
                        binding.ivInicial.visibility = View.GONE
                        binding.btConfigurar.visibility = View.GONE
                        binding.btComenzar.visibility = View.GONE
                        binding.tvRegistroAdministrador.visibility = View.VISIBLE
                        binding.tilNombre.visibility = View.VISIBLE
                        binding.tilContrasena.visibility = View.VISIBLE
                        binding.tilConfirmarContrasena.visibility = View.VISIBLE
                        binding.rgOpciones.visibility = View.VISIBLE
                        binding.btGuardar.visibility = View.VISIBLE
                    }).show()
            }else{
                //binding.tvInicial.visibility = View.GONE
                binding.ivInicial.visibility = View.GONE
                binding.btConfigurar.visibility = View.GONE
                binding.btComenzar.visibility = View.GONE
                binding.tvSaludo.visibility = View.VISIBLE
                binding.tilValidarContrasena.visibility = View.VISIBLE
                binding.btContinuarConfiguracion.visibility = View.VISIBLE
                val pass = preferences.getString(getString(R.string.passwordUser), "Error")
                val name = "Bienvenido " + preferences.getString(getString(R.string.nameUser), "Error")
                binding.tvSaludo.setText(name)
                binding.btContinuarConfiguracion.setOnClickListener {
                    if(pass ==binding.etValidarContrasena.text.toString()){

                        startActivity(Intent(this, ConfigurarActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.btGuardar.setOnClickListener {
            if(binding.etNombre.text.toString().isNotEmpty() && binding.etContrasena.text.toString().isNotEmpty() && (binding.etContrasena.text.toString() == binding.etConfirmarContrasena.text.toString())){
                preferences.edit().putBoolean(getString(R.string.isNewUser), false).apply()
                preferences.edit().putString(getString(R.string.nameUser), binding.etNombre.text.toString()).apply()
                preferences.edit().putString(getString(R.string.passwordUser), binding.etContrasena.text.toString()).apply()
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                if(binding.rbOpcionCargarPorDefecto.isChecked){
                    cargarPreguntasPorDefecto()
                }
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }else{
                if(binding.etNombre.text.toString().isEmpty()){
                    Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
                }else{
                    if(binding.etContrasena.text.toString().isEmpty()){
                        Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()
                }else {
                        if (binding.etConfirmarContrasena.text.toString().isEmpty()) {
                            Toast.makeText(this, "Repita la contraseña", Toast.LENGTH_SHORT).show()
                        }else{
                            if (binding.etContrasena.text.toString() != binding.etConfirmarContrasena.text.toString()) {
                                Toast.makeText(this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
    binding.btComenzar.setOnClickListener {
        if(newUser){
            Toast.makeText(this, "El juego no tiene asignado un administrador", Toast.LENGTH_SHORT).show()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }

    private fun cargarPreguntasPorDefecto() {
        val preguntasDAO = ConcursoPyR.database.PreguntasDAO()
        var pregunta1 = Preguntas(
            Types.NULL,
            "1",
            "Cuanto es 10 - 9",
            "4",
            "5",
            "1",
            "9",
            "3"
        )
        var pregunta2 = Preguntas(
            Types.NULL,
            "2",
            "Cuanto es 7 - 5",
            "1",
            "2",
            "12",
            "3",
            "2"
        )
        var pregunta3 = Preguntas(
            Types.NULL,
            "3",
            "Cuanto es 21 - 18",
            "2",
            "4",
            "13",
            "3",
            "4"
        )
        var pregunta4 = Preguntas(
            Types.NULL,
            "4",
            "Cuanto es 33 - 29",
            "4",
            "6",
            "3",
            "1",
            "1"
        )
        var pregunta5 = Preguntas(
            Types.NULL,
            "5",
            "Cuanto es 2 + 3",
            "2",
            "5",
            "1",
            "0",
            "2"
        )
        var pregunta6 = Preguntas(
            Types.NULL,
            "6",
            "Cuanto es 2 x 5",
            "4",
            "5",
            "10",
            "9",
            "3"
        )
        var pregunta7 = Preguntas(
            Types.NULL,
            "7",
            "Cuanto es 10 x 9",
            "80",
            "90",
            "9",
            "109",
            "2"
        )
        var pregunta8 = Preguntas(
            Types.NULL,
            "8",
            "Cuanto es 7 x 6",
            "42",
            "55",
            "19",
            "36",
            "1"
        )
        var pregunta9 = Preguntas(
            Types.NULL,
            "9",
            "Cuanto es 5 x 11",
            "11/5",
            "55",
            "511",
            "115",
            "2"
        )
        var pregunta10 = Preguntas(
            Types.NULL,
            "10",
            "Cuanto es 8 x 9",
            "49",
            "72",
            "81",
            "94",
            "2"
        )
        var pregunta11 = Preguntas(
            Types.NULL,
            "11",
            "Cuanto es 27 / 9",
            "4",
            "5",
            "3",
            "9",
            "3"
        )
        var pregunta12 = Preguntas(
            Types.NULL,
            "12",
            "Cuanto es 16 / 2",
            "8",
            "7",
            "11",
            "6",
            "1"
        )
        var pregunta13 = Preguntas(
            Types.NULL,
            "13",
            "Cuanto es 48 / 12",
            "4",
            "7",
            "1",
            "3",
            "1"
        )
        var pregunta14 = Preguntas(
            Types.NULL,
            "14",
            "Cuanto es 54 / 6",
            "8",
            "2",
            "11",
            "9",
            "4"
        )
        var pregunta15 = Preguntas(
            Types.NULL,
            "15",
            "Cuanto es 49 / 7",
            "5",
            "9",
            "6",
            "7",
            "4"
        )
        var pregunta16 = Preguntas(
            Types.NULL,
            "16",
            "Cuanto es 5 + 7 x 5",
            "40",
            "60",
            "54",
            "60",
            "1"
        )
        var pregunta17 = Preguntas(
            Types.NULL,
            "17",
            "Cuanto es 10 - 9 x 3",
            "57",
            "3",
            "17",
            "-17",
            "4"
        )
        var pregunta18 = Preguntas(
            Types.NULL,
            "18",
            "Cuanto es 8 x 2 + 9 x 2 ",
            "34",
            "50",
            "11",
            "18",
            "1"
        )
        var pregunta19 = Preguntas(
            Types.NULL,
            "19",
            "Cuanto es 12 + 5 x 4",
            "68",
            "21",
            "32",
            "33",
            "3"
        )
        var pregunta20 = Preguntas(
            Types.NULL,
            "20",
            "Cuanto es 4 x 7 + 9 ",
            "24",
            "51",
            "43",
            "37",
            "4"
        )
        var pregunta21 = Preguntas(
            Types.NULL,
            "21",
            "Cuanto es 10 - 9 / 3 + 5 x 2",
            "24",
            "51",
            "17",
            "32/3",
            "3"
        )
        var pregunta22 = Preguntas(
            Types.NULL,
            "22",
            "Cuanto es 5 x 6 - 2 + 8 / 2",
            "19",
            "32",
            "23",
            "18",
            "2"
        )
        var pregunta23 = Preguntas(
            Types.NULL,
            "23",
            "Cuanto es 21 / 3 + 12 / 4 - 100 / 10",
            "4",
            "5",
            "0",
            "9",
            "3"
        )
        var pregunta24 = Preguntas(
            Types.NULL,
            "24",
            "Cuanto es 8 + 5 x 7 - 4 x 4",
            "27",
            "21",
            "348",
            "45",
            "1"
        )
        var pregunta25 = Preguntas(
            Types.NULL,
            "25",
            "Cuanto es 10 x 3 + 6 / 2 - 5 x 13 / 5",
            "21",
            "24",
            "15",
            "20",
            "4"
        )

        preguntasDAO.crearPreguntas(pregunta1)
        preguntasDAO.crearPreguntas(pregunta2)
        preguntasDAO.crearPreguntas(pregunta3)
        preguntasDAO.crearPreguntas(pregunta4)
        preguntasDAO.crearPreguntas(pregunta5)
        preguntasDAO.crearPreguntas(pregunta6)
        preguntasDAO.crearPreguntas(pregunta7)
        preguntasDAO.crearPreguntas(pregunta8)
        preguntasDAO.crearPreguntas(pregunta9)
        preguntasDAO.crearPreguntas(pregunta10)
        preguntasDAO.crearPreguntas(pregunta11)
        preguntasDAO.crearPreguntas(pregunta12)
        preguntasDAO.crearPreguntas(pregunta13)
        preguntasDAO.crearPreguntas(pregunta14)
        preguntasDAO.crearPreguntas(pregunta15)
        preguntasDAO.crearPreguntas(pregunta16)
        preguntasDAO.crearPreguntas(pregunta17)
        preguntasDAO.crearPreguntas(pregunta18)
        preguntasDAO.crearPreguntas(pregunta19)
        preguntasDAO.crearPreguntas(pregunta20)
        preguntasDAO.crearPreguntas(pregunta21)
        preguntasDAO.crearPreguntas(pregunta22)
        preguntasDAO.crearPreguntas(pregunta23)
        preguntasDAO.crearPreguntas(pregunta24)
        preguntasDAO.crearPreguntas(pregunta25)
    }
}