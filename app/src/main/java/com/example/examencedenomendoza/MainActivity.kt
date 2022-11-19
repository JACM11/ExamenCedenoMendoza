package com.example.examencedenomendoza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                        serie = 200,
                        marca = document.data.get("marca").toString(),
                        pulgadas = 200,
                        modelo = document.data.get("modelo").toString(),
                        urlImagen = document.data.get("urlImagen").toString()
                    )
                    listaTelevisores.add(ObjetoTelevisor)
                }
                binding.recyclerview.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter= TelevisorAdapter(listaTelevisores)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //cargaDeDatosTelevisores()

        db.collection("televisores").add(user1)
        db.collection("televisores").add(user2)
        db.collection("televisores").add(user3)
        getTelevisores()
    }

    fun cargaDeDatosTelevisores(){
        binding.recyclerview.apply {
            layoutManager= LinearLayoutManager(context)
            adapter = TelevisorAdapter(televisores)
        }
    }

}

