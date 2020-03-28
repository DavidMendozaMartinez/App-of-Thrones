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

            val instance = HouseDialog()
            instance.arguments = arguments
            return instance
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_house, null)
        val house = arguments?.getSerializable("key_house") as House?

        house?.run {
            dialogView.labelName.text = name
            dialogView.labelRegion.text = region
            dialogView.labelWords.text = words
            Picasso.get()
                .load(img)
                .into(dialogView.imgHouse)

            context?.let { context ->
                dialogView.layoutDialog.background =
                    ContextCompat.getDrawable(context, House.getBaseColor(name))
            }

        } ?: throw IllegalAccessException(
            "Attached activity doesn't use HouseDialog.newInstance() method"
        )

        return activity?.let { activity ->
            AlertDialog.Builder(activity)
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok) { _, _ -> dismiss() }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}