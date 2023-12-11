package com.example.front_end_mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent

data class Treino(
    val nomeAluno: String,
    val nomeExercicio: String,
    val quantidadeSeries: Int,
    val numeroRepeticoes: Int,
    val diasDaSemana: List<String>,
    val grupoMuscular: String
)

class AddTreinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_treino)

        findViewById<Button>(R.id.btnEnviarTreino).setOnClickListener {
            enviarTreino()
        }

        findViewById<Button>(R.id.btnConsultarTreinos).setOnClickListener {
            val intent = Intent(this, ConsultaTreinosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun enviarTreino() {
        val nomeAluno = findViewById<EditText>(R.id.edtNomeAluno).text.toString()
        val nomeExercicio = findViewById<EditText>(R.id.edtNomeExercicio).text.toString()
        val quantidadeSeries = findViewById<EditText>(R.id.edtQuantidadeSeries).text.toString().toIntOrNull() ?: 0
        val numeroRepeticoes = findViewById<EditText>(R.id.edtNumeroRepeticoes).text.toString().toIntOrNull() ?: 0

        val diasDaSemana = listOf(
            if (findViewById<ToggleButton>(R.id.toggleMonday).isChecked) "Seg" else "",
            if (findViewById<ToggleButton>(R.id.toggleTuesday).isChecked) "Ter" else "",
            if (findViewById<ToggleButton>(R.id.toggleWednesday).isChecked) "Qua" else "",
            if (findViewById<ToggleButton>(R.id.toggleThursday).isChecked) "Qui" else "",
            if (findViewById<ToggleButton>(R.id.toggleFriday).isChecked) "Sex" else ""
        ).filter { it.isNotEmpty() }

        val grupoMuscularId = findViewById<RadioGroup>(R.id.radioGroupMuscular).checkedRadioButtonId
        val grupoMuscular = findViewById<RadioButton>(grupoMuscularId).text.toString()

        val treino = Treino(nomeAluno, nomeExercicio, quantidadeSeries, numeroRepeticoes, diasDaSemana, grupoMuscular)
        salvarNoFirebase(treino)
    }

    private fun salvarNoFirebase(treino: Treino) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Treinos")
        val treinoId = databaseReference.push().key

        treinoId?.let {
            databaseReference.child(it).setValue(treino).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Treino cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erro ao enviar treino.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
