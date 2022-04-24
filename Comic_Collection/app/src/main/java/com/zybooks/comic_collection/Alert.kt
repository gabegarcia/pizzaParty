package com.zybooks.comic_collection

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment

class Alert : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.warning)
        builder.setMessage(R.string.warning_message)
        builder.setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
            // User clicked OK button

        })
        builder.setNegativeButton("No", null)
        return builder.create()
    }
}