package com.davidmendozamartinez.appofthrones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter(val itemClickListener: ((Character) -> Unit)) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private val items = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        holder.character = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setCharacters(characters: MutableList<Character>) {
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var character: Character? = null
            set(value) {
                value?.run {
                    itemView.labelName.text = name
                    itemView.labelTitle.text = title

                    val overlayColor = House.getOverlayColor(house.name)
                    itemView.imgOverlay.background =
                        ContextCompat.getDrawable(itemView.context, overlayColor)

                    Picasso.get()
                        .load(img)
                        .placeholder(R.drawable.placeholder)
                        .into(itemView.imgCharacter)
                }
                field = value
            }

        init {
            itemView.setOnClickListener {
                character?.let {
                    itemClickListener.invoke(character as Character)
                }
            }
        }
    }
}