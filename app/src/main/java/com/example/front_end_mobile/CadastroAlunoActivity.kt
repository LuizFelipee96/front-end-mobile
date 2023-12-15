package com.example.front_end_mobile

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.front_end_mobile.viewmodels.AlunoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

data class Aluno(
    val nome: String? = null,
    val sexo: String? = null,
    val idade: String? = null,
)

class CadastroAlunoActivity : AppCompatActivity() {
    private lateinit var cadastroAlunoViewModel: AlunoViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        auth = FirebaseAuth.getInstance()

        cadastroAlunoViewModel = ViewModelProvider(this)[AlunoViewModel::class.java]

        val nomeEditText = findViewById<EditText>(R.id.edtNome)
        val idadeEditText = findViewById<EditText>(R.id.edtIdade)
        val generoRadioGroup = findViewById<RadioGroup>(R.id.radioGroupGenero)
        val enviarButton = findViewById<Button>(R.id.btnEnviar)

        enviarButton.setOnClickListener {
            val nome = nomeEditText.text.toString().trim()
            val idade = idadeEditText.text.toString().trim()
            val generoId = generoRadioGroup.checkedRadioButtonId
            val genero = when (generoId) {
                R.id.radioFeminino -> "Feminino"
                R.id.radioMasculino -> "Masculino"
                else -> ""
            }

            if (nome.isEmpty()) {
                Toast.makeText(this, "Por favor preencha o nome.", Toast.LENGTH_SHORT).show()
            }
            if (idade.isEmpty()) {
                Toast.makeText(this, "Por favor preencha a idade.", Toast.LENGTH_SHORT).show()
            }
            if (genero.isEmpty()) {
                Toast.makeText(this, "Por favor selecione o gênero.", Toast.LENGTH_SHORT).show()
            }
            if (nome.isNotEmpty() && idade.isNotEmpty() && genero.isNotEmpty()) {
                val aluno = Aluno(nome, sexo = genero, idade)
                salvarNoFirebase(aluno)
                val intent = Intent(this, ListarAlunoActivity::class.java)
                startActivity(intent)
            }

        }

    }

    private fun salvarNoFirebase(aluno: Aluno) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Alunos")
        val alunoId = databaseReference.push().key

        alunoId?.let { it ->
            databaseReference.child(it).setValue(aluno).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erro ao salvar aluno.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

