package com.example.omapp.common.presentation

import androidx.fragment.app.Fragment
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import com.example.omapp.R

abstract class BaseFragment : Fragment() {

    private lateinit var progressDialog: ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(activity)
    }

    protected fun showLoadingDialogFragment() {
        progressDialog.setMessage(getString(R.string.downloading_title_dialog))
        progressDialog.show()
    }

    protected fun hideLoadingDialogFragment() {
        progressDialog.hide()
    }
}