package com.example.examencedenomendoza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examencedenomendoza.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val televisores = mutableListOf<Televisor>()

    val db = FirebaseFirestore.getInstance()
    val user1 = hashMapOf(
        "serie" to 2550,
        "marca" to "LG",
        "pulgadas" to 70,
        "modelo" to "2016",
        "urlimagen" to "https://www.lg.com/ec/images/tvs/md07550992/gallery/D-1.jpg"
    )

    val user2 = hashMapOf(
        "serie" to 2541,
        "marca" to "Samsung",
        "pulgadas" to 80,
        "modelo" to "2015",
        "urlimagen" to "https://www.lg.com/ec/images/tvs/md07550992/gallery/D-1.jpg"
    )

    val user3 = hashMapOf(
        "serie" to 2365,
        "marca" to "Global",
        "pulgadas" to 100,
        "modelo" to "2014",
        "urlimagen" to "https://www.lg.com/ec/images/tvs/md07550992/gallery/D-1.jpg"
    )

    fun getTelevisores(){
        var listaTelevisores = mutableListOf<Televisor>()

        db.collection("televisores")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents ){
                    var ObjetoTelevisor = Televisor(
                        serie = document.data.get("serie").toString(),
                        marca = document.data.get("marca").toString(),
                        pulgadas = document.data.get("pulgadas").toString(),
                        modelo = document.data.get("modelo").toString(),
                        urlImagen = document.data.get("urlimagen").toString()
                    )
                    listaTelevisores.add(ObjetoTelevisor)
                }
                binding.recyclerview.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter= TelevisorAdapter(listaTelevisores)
                }
            }
    }
    private fun leerTelevisores(){
        val docTel = db.collection("televisores")
            .whereEqualTo("marca","LG")
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    var ObjetoTelevisor = Televisor(
                        serie = document.data.get("serie").toString(),
                        marca = document.data.get("marca").toString(),
                        pulgadas = document.data.get("pulgadas").toString(),
                        modelo = document.data.get("modelo").toString(),
                        urlImagen = document.data.get("urlimagen").toString()
                    )
                }
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.collection("televisores").add(user1)
        db.collection("televisores").add(user2)
        db.collection("televisores").add(user3)
        getTelevisores()


        binding.buscar.setOnClickListener{
            var listaTelevisores = mutableListOf<Televisor>()
            val docTel =db.collection("televisores").document(binding.dato2.text.toString())
            docTel.get()
                .addOnSuccessListener { document ->
                    if(document != null){
                            var ObjetoTelevisor = Televisor(
                                serie = document.get("serie").toString(),
                                marca = document.get("marca").toString(),
                                pulgadas = document.get("pulgadas").toString(),
                                modelo = document.get("modelo").toString(),
                                urlImagen = document.get("urlimagen").toString()
                            )
                        listaTelevisores.add(ObjetoTelevisor)
                        }
                    binding.recyclerview.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter= TelevisorAdapter(listaTelevisores)
                    }
                }
        }
    }




}

