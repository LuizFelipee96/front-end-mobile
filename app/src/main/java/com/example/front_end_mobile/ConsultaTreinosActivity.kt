package com.example.front_end_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.front_end_mobile.adapters.TreinoAdapter
import com.example.front_end_mobile.viewmodels.TreinoViewModel

class ConsultaTreinosActivity : AppCompatActivity() {

    private lateinit var treinoViewModel: TreinoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_treinos)

        // Inicializa o ViewModel
        treinoViewModel = ViewModelProvider(this)[TreinoViewModel::class.java]

        // Configura o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTreinos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TreinoAdapter()
        recyclerView.adapter = adapter

        // Observa mudanças nos dados e atualiza o RecyclerView
        treinoViewModel.getTreinos().observe(this, { treinos ->
            adapter.updateTreinos(treinos) // Aqui foi feita a alteração
        })
    }
}
