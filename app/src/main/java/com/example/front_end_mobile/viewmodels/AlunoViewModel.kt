package com.example.front_end_mobile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.front_end_mobile.Aluno
import com.example.front_end_mobile.Treino
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AlunoViewModel : ViewModel() {

    private val alunos: MutableLiveData<List<Aluno>> = MutableLiveData()

    fun getAlunos(): LiveData<List<Aluno>>? {
        FirebaseAuth.getInstance().currentUser?.let {
            val databaseReference = FirebaseDatabase.getInstance().getReference("Alunos")
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val listaAlunos = mutableListOf<Aluno>()
                    dataSnapshot.children.forEach { snapshot ->
                        val aluno = snapshot.getValue(Aluno::class.java)
                        aluno?.let { listaAlunos.add(it) }
                    }
                    alunos.postValue(listaAlunos)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Tratar o erro
                }
            }
            databaseReference.addValueEventListener(listener)
            return alunos
        } ?: run {
            return null
        }

    }
}
