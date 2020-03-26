package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(characterId: String): DetailFragment {
            val instance = DetailFragment()
            val args = Bundle()
            args.putString("key_id", characterId)

            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("key_id")?.let { id ->
            val character = CharactersRepository.findCharacterById(id)

            character?.let {
                with(character) {
                    labelName.text = name
                    labelTitle.text = title
                    labelBorn.text = born
                    labelActor.text = actor
                    labelQuote.text = quote
                    labelParents.text = "$father & $mother"
                    labelSpouse.text = spouse
                    button.text = house.name

                    button.setOnClickListener {
                        Toast.makeText(context, house.words, Toast.LENGTH_SHORT).show()
                    }
                }
            } ?: throw IllegalAccessException(
                "Attached activity doesn't use DetailFragment.newInstance() method"
            )
        }
    }
}