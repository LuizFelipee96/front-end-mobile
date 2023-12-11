package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.front_end_mobile.Treino
import com.example.front_end_mobile.R


class TreinoAdapter(private var treinos: List<Treino>) : RecyclerView.Adapter<TreinoAdapter.TreinoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_treino, parent, false)
        return TreinoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreinoViewHolder, position: Int) {
        holder.bind(treinos[position])
    }

    override fun getItemCount(): Int = treinos.size

    fun updateTreinos(newTreinos: List<Treino>) {
        treinos = newTreinos
        notifyDataSetChanged()
    }

    class TreinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(treino: Treino) {
            // data to UI
        }
    }
}
