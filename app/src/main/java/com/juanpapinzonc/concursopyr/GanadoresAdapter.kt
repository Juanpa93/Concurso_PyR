package com.juanpapinzonc.concursopyr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanpapinzonc.concursopyr.databinding.ItemGanadorBinding

class GanadoresAdapter (private val ganadores : List<Ganadores>) : RecyclerView.Adapter<GanadoresAdapter.ViewHolder>(){

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_ganador, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ganador = ganadores.get(position)

        with(holder){
            binding.tvNombreGanador.text = ganador.nombreGanador
            binding.tvDocumentoGanador.text = ganador.documentoGanador
            binding.tvPremioGanador.setText("${ganador.premioGanador} xp")
            if(ganador.esCampeon) {
                binding.ivTrofeo.setImageResource(R.drawable.trofeo)
            }else{
                binding.ivTrofeo.setImageResource(R.drawable.sin_trofeo)
            }
            }

            /*Glide.with(context)
                .load(producto.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.ivCard)*/
        }


    override fun getItemCount(): Int =ganadores.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = ItemGanadorBinding.bind(view)
    }

}