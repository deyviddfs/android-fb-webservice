package br.com.deyvidfernandes.feedbackwebservice.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.deyvidfernandes.feedbackwebservice.PokemonInfoActivity
import br.com.deyvidfernandes.feedbackwebservice.R
import br.com.deyvidfernandes.feedbackwebservice.network.Card
import com.squareup.picasso.Picasso

class PokemonAdapter(private val dataSet: List<Card>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textViewTitle: TextView
        var imageViewDemo: ImageView

        init {
            textViewTitle = view.findViewById(R.id.textView1)
            imageViewDemo = view.findViewById(R.id.imageView1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = dataSet[position]
        holder.textViewTitle.text = item.name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PokemonInfoActivity::class.java).apply {
                putExtra("EXTRA_POKEMON_ID", item.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        Log.i("PICASSO", item.images.small)
        Picasso.get().load(item.images.small).into(holder.imageViewDemo)
    }

    override fun getItemCount() = dataSet.size
}