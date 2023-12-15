package com.example.front_end_mobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.front_end_mobile.Aluno
import com.example.front_end_mobile.R

class AlunoAdapter(private var alunos: List<Aluno> = listOf()) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_listar_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        holder.bind(alunos[position])
    }

    override fun getItemCount(): Int = alunos.size

    fun updateAlunos(newAlunos: List<Aluno>) {
        alunos = newAlunos
        notifyDataSetChanged()
    }

    class AlunoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idadeAlunoTextView: TextView = itemView.findViewById(R.id.edtIdade)
        private val sexoAlunoTextView: TextView = itemView.findViewById(R.id.edtSexo)
        private val nomeAlunoTextView: TextView = itemView.findViewById(R.id.edtNomeAluno)

        fun bind(aluno: Aluno) {
            nomeAlunoTextView.text = "Nome: " + aluno.nome
            idadeAlunoTextView.text = "Idade: " + aluno.idade
            sexoAlunoTextView.text = "Gênero: " + aluno.sexo

        }
    }
}