package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*

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

                    val overlayColor = House.getOverlayColor(character.house.name)
                    val baseColor = House.getBaseColor(character.house.name)
                    val drawable = House.getIcon(character.house.name)

                    context?.let { context ->
                        imgOverlay.background = ContextCompat.getDrawable(context, overlayColor)
                        btnHouse.backgroundTintList =
                            ContextCompat.getColorStateList(context, baseColor)
                        btnHouse.setImageDrawable(ContextCompat.getDrawable(context, drawable))
                    }

                    btnHouse.setOnClickListener {
                        Toast.makeText(context, house.words, Toast.LENGTH_SHORT).show()
                    }

                    Picasso.get()
                        .load(character.img)
                        .placeholder(R.drawable.test)
                        .into(imgCharacter)
                }
            } ?: throw IllegalAccessException(
                "Attached activity doesn't use DetailFragment.newInstance() method"
            )
        }
    }
}