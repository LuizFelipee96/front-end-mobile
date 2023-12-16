package com.example.front_end_mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.front_end_mobile.adapters.TreinoAdapter
import com.example.front_end_mobile.viewmodels.TreinoViewModel
import com.google.firebase.auth.FirebaseAuth

class ConsultaTreinosActivity : AppCompatActivity() {

    private lateinit var treinoViewModel: TreinoViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_treinos)
        auth = FirebaseAuth.getInstance()

        // Inicializa o ViewModel
        treinoViewModel = ViewModelProvider(this)[TreinoViewModel::class.java]

        // Configura o RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewListarTreinos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = TreinoAdapter()
        recyclerView.adapter = adapter

        // Observa mudanças nos dados e atualiza o RecyclerView
        treinoViewModel.getTreinos()?.observe(this, { treinos ->
            adapter.updateTreinos(treinos)
        })

        // Configura o botão "Voltar"
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            val intent = Intent(this, AddTreinoActivity::class.java)
            startActivity(intent)
        }

        // Configura o botão "Dicas de Suplemento"
        val btnDicasSuplemento = findViewById<Button>(R.id.btnDicasSuplemento)
        btnDicasSuplemento.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // logic
            }
        })
    }
}