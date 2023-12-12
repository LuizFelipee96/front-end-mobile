package com.example.front_end_mobile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.front_end_mobile.Treino
import com.google.firebase.database.*

class TreinoViewModel : ViewModel() {

    private val treinos: MutableLiveData<List<Treino>> = MutableLiveData()

    fun getTreinos(): LiveData<List<Treino>> {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Treinos")
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listaTreinos = mutableListOf<Treino>()
                dataSnapshot.children.forEach { snapshot ->
                    val treino = snapshot.getValue(Treino::class.java)
                    treino?.let { listaTreinos.add(it) }
                }
                treinos.postValue(listaTreinos)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Tratar o erro
            }
        }
        databaseReference.addValueEventListener(listener)
        return treinos
    }
}
