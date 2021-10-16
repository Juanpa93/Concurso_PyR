package com.juanpapinzonc.concursopyr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juanpapinzonc.concursopyr.databinding.ActivitySalonGanadoresBinding

class SalonGanadoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalonGanadoresBinding
    private lateinit var ganadoresAdapter: GanadoresAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalonGanadoresBinding.inflate(layoutInflater)
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        ganadoresAdapter = GanadoresAdapter(getGanadores())
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvGanadores.apply {
            layoutManager = linearLayoutManager
            adapter = ganadoresAdapter
        }
    }

    private fun getGanadores(): List<Ganadores> {
        var cont = 0
        var ban = true
        val ganadoresAll = mutableListOf<Ganadores>()
        val ganadoresDAO = ConcursoPyR.database2.GanadoresDAO()
        while(ban){
            val ganadores = ganadoresDAO.buscarGanadores2(cont+1)
            if(ganadores != null ){
                ganadoresAll.add(ganadores)
                cont+= 1
            }else{
                ban = false
            }


        }
        return ganadoresAll.reversed()
    }
}