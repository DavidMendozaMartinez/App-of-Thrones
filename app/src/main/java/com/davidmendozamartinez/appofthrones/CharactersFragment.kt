package com.davidmendozamartinez.appofthrones

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CharactersFragment : Fragment() {

    private val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

    private val adapter: CharactersAdapter by lazy {
        val adapter = CharactersAdapter { item, _ ->
            clickListener.onItemClicked(item)
        }
        adapter
    }

    private lateinit var clickListener: OnItemClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickListener)
            clickListener = context
        else
            throw IllegalArgumentException(
                "Attached activity doesn't implement CharactersFragment.OnItemClickListener"
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characters: MutableList<Character> = CharactersRepository.characters
        adapter.setCharacters(characters)

        list.adapter = adapter
    }

    interface OnItemClickListener {
        fun onItemClicked(character: Character)
    }
}