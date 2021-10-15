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

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

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
                binding.tvInicial.visibility = View.GONE
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
}
}