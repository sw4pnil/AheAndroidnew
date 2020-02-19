package com.ahe.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.ahe.R
import java.util.regex.Pattern

private val logFlag = true

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun showLog(TAG: String, message: String) {
    if (logFlag)
        Log.d(TAG, message)
}

fun entryAnimatiom(activity: AppCompatActivity) {
    activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
}
fun entryAnimatiomFragment(activity: FragmentActivity) {
    activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
}

fun exitAnimation(activity: AppCompatActivity) {
    activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
}


/*
  * Check phone number valid
  * */

const val REG = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}\$"
var PATTERN: Pattern = Pattern.compile(REG)
fun CharSequence.isPhoneNumber() : Boolean = PATTERN.matcher(this).find()