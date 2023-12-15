package com.example.front_end_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.front_end_mobile.adapters.AlunoAdapter
import com.example.front_end_mobile.viewmodels.AlunoViewModel
import com.google.firebase.auth.FirebaseAuth


class ListarAlunoActivity : AppCompatActivity() {
    private lateinit var listarAlunoViewModel: AlunoViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_alunos)
        auth = FirebaseAuth.getInstance()

        listarAlunoViewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewListarAlunos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = AlunoAdapter()
        recyclerView.adapter = adapter

        listarAlunoViewModel.getAlunos()?.observe(this, { alunos ->
            adapter.updateAlunos(alunos)
        })

        val cadastrarButton = findViewById<Button>(R.id.btnCadastrar)

        cadastrarButton.setOnClickListener {
            val intent = Intent(this, CadastroAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}

