package com.example.examencedenomendoza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examencedenomendoza.databinding.RecycleviewItemBinding
import com.squareup.picasso.Picasso

class TelevisorAdapter(val lista: List<Televisor>):RecyclerView.Adapter<TelevisorAdapter.ViewHolderTelevisor>() {

    class ViewHolderTelevisor(view: View) : RecyclerView.ViewHolder(view) {
        val binding: RecycleviewItemBinding = RecycleviewItemBinding.bind(view)
        fun bin(item:Televisor){
            binding.lblSerie.text = item.serie
            binding.lblMarca.text = item.marca
            binding.lblModelo.text=item.modelo
            binding.lblPulgadas.text = item.pulgadas
            Picasso.get().load(item.urlImagen).into(binding.imagen)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTelevisor {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item, parent, false)

        return ViewHolderTelevisor(view)

    }

    override fun onBindViewHolder(holder: ViewHolderTelevisor, position: Int) {
        val item = lista[position]
        holder.bin(item)

    }

    override fun getItemCount(): Int {
        return lista.size
    }

}