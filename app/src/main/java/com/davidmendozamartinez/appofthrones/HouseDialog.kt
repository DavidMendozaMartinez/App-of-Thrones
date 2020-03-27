package com.davidmendozamartinez.appofthrones

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_house.view.*

class HouseDialog : DialogFragment() {

    companion object {
        fun newInstance(house: House): HouseDialog {
            val arguments = Bundle()
            arguments.putSerializable("key_house", house)

            val dialog = HouseDialog()
            dialog.arguments = arguments

            return dialog
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_house, null)
        val house = arguments?.getSerializable("key_house") as House

        context?.let { context ->
            with(house) {
                dialogView.labelName.text = name
                dialogView.labelRegion.text = region
                dialogView.labelWords.text = words
                dialogView.layoutDialog.background =
                    ContextCompat.getDrawable(context, House.getBaseColor(name))
            }
        }

        Picasso.get()
            .load(house.img)
            .into(dialogView.imgHouse)

        return activity?.let { activity ->
            AlertDialog.Builder(activity)
                .setView(dialogView)
                .setPositiveButton(R.string.label_accept) { _, _ -> dismiss() }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}