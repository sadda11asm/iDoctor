package com.example.mppapp.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.mppapp.R

class ProgressDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return activity!!.layoutInflater.inflate(R.layout.fragment_dialog_progress, container)
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.also { window ->
            window.attributes?.also { attributes ->
                attributes.dimAmount = 0.1f
                window.attributes = attributes

            }
        }
    }


    companion object {
        private const val FRAGMENT_TAG = "progress_dialog"

        fun newInstance() = ProgressDialogFragment()

        fun show(supportFragmentManager: FragmentManager): ProgressDialogFragment {
            val dialog = ProgressDialogFragment.newInstance()
            // prevent dismiss by user click
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, FRAGMENT_TAG)
            return dialog
        }
    }
}