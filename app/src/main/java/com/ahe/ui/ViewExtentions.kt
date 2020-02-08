package com.ahe.ui

import android.app.Activity
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.ahe.R


fun Activity.displayToast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.displayToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.displaySuccessDialog(message: String?) {
    MaterialDialog(this)
        .show {
            title(R.string.text_success)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayErrorDialog(errorMessage: String?) {
    MaterialDialog(this)
        .show {
            title(R.string.text_error)
            message(text = errorMessage)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayInfoDialog(message: String?) {
    MaterialDialog(this)
        .show {
            title(R.string.text_info)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.areYouSureDialog(message: String, callback: AreYouSureCallback) {
    MaterialDialog(this)
        .show {
            title(R.string.are_you_sure)
            message(text = message)
            negativeButton(R.string.text_cancel) {
                callback.cancel()
            }
            positiveButton(R.string.text_yes) {
                callback.proceed()
            }
        }
}


interface AreYouSureCallback {
    fun proceed()
    fun cancel()
}


fun Activity.userTypeDialog(callback: UIMessageType.UserTypeCallback) {
    val dialog = MaterialDialog(this)
        .noAutoDismiss()
        .customView(R.layout.user_type_dialogue)

    val view = dialog.getCustomView()

    view.findViewById<TextView>(R.id.okBtn).setOnClickListener {

        val newFilter =
            when (view.findViewById<RadioGroup>(R.id.userTypeRadioGrp).checkedRadioButtonId) {
                R.id.npoRadioBtn -> "1"
                R.id.publicCommRadioBtn -> "2"
                else -> "0"
            }

        callback.proceed(newFilter)
        dialog.dismiss()
    }

    dialog.show()
}

interface UserTypeCallback {
    fun proceed(userType: String)
}








