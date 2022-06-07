package com.otus.vmikhaylov

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class ExitDialog(accepted: () -> Unit) : DialogFragment() {
    private val callback: () -> Unit = accepted

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Wait!")
            .setMessage("Will u stay with me?")
            .setNegativeButton("No") { dialog, which -> }
            .setPositiveButton("Yep :)") { dialog, which -> }
            .setNegativeButton(android.R.string.cancel) { dialog, which -> }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                callback()
            }
            .create()
    }
}