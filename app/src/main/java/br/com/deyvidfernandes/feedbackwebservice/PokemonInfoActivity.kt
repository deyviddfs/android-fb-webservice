package br.com.deyvidfernandes.feedbackwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.deyvidfernandes.feedbackwebservice.databinding.ActivityPokemonInfoBinding
import br.com.deyvidfernandes.feedbackwebservice.network.PokemonApi
import br.com.deyvidfernandes.feedbackwebservice.network.PokemonInfo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPokemonInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("EXTRA_POKEMON_ID")

        if (id != null) {
            loadInfo(id)
        }
    }

    private fun loadInfo(id: String){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            try {
                val result = PokemonApi.RETROFIT_SERVICE.getPokemon(id)

                withContext(Dispatchers.Main){
                    updateUI(result)
                }
            }catch (e: Exception){
                Log.e("Error", "Falha: ${e.message}")
            }
        }
    }

    private fun updateUI(result: PokemonInfo){
        val card = result.data
        Log.i("RETROFIT", "Card: ${card}")
        binding.textView1.text = card.name
        Picasso.get().load(card.images.large).resize(450, 600).into(binding.imageView1)
        binding.textView2.text = card.level
        binding.textView3.text = card.hp
    }
}