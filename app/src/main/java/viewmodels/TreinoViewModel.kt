package viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.front_end_mobile.Treino
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TreinoViewModel : ViewModel() {

    private val dbTreinos = FirebaseDatabase.getInstance().getReference("Treinos")
    private val _treinos = MutableLiveData<List<Treino>>()
    val treinos: LiveData<List<Treino>> = _treinos

    fun fetchTreinos() {
        dbTreinos.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val treinos = snapshot.children.mapNotNull { it.getValue(Treino::class.java) }
                    _treinos.postValue(treinos)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // erro
            }
        })
    }
}