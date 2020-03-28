package com.davidmendozamartinez.appofthrones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(characterId: String): DetailFragment {
            val arguments = Bundle()
            arguments.putString("key_id", characterId)

            val instance = DetailFragment()
            instance.arguments = arguments
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

            character?.run {
                labelName.text = name
                labelTitle.text = title
                labelBorn.text = born
                labelActor.text = actor
                labelQuote.text = quote
                labelParents.text = getString(R.string.label_parents, father, mother)
                labelSpouse.text = spouse

                Picasso.get()
                    .load(img)
                    .placeholder(R.drawable.placeholder)
                    .into(imgCharacter)

                context?.let { context ->
                    val overlayColor = House.getOverlayColor(house.name)
                    val baseColor = House.getBaseColor(house.name)
                    val drawable = House.getIcon(house.name)

                    imgOverlay.background = ContextCompat.getDrawable(context, overlayColor)
                    btnHouse.backgroundTintList =
                        ContextCompat.getColorStateList(context, baseColor)
                    btnHouse.setImageDrawable(ContextCompat.getDrawable(context, drawable))
                }

                btnHouse.setOnClickListener {
                    showDialog(house)
                }

            } ?: throw IllegalAccessException(
                "Attached activity doesn't use DetailFragment.newInstance() method"
            )
        }
    }

    private fun showDialog(house: House) {
        val dialog = HouseDialog.newInstance(house)
        dialog.show(childFragmentManager, "house_dialog")
    }
}