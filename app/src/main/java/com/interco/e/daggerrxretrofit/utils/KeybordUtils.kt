package com.interco.e.daggerrxretrofit.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.view.inputmethod.InputMethodManager

import androidx.annotation.Keep

@Keep
class KeybordUtils {
    companion object {

        fun hideKeyboard(mActivity: Activity?) {
            if (mActivity != null) {
                try {
                    @SuppressLint("WrongConstant") val imm = mActivity.getSystemService("input_method") as InputMethodManager
                    imm.hideSoftInputFromWindow(mActivity.window.decorView.rootView.windowToken, 0)
                } catch (var2: Exception) {
                    var2.printStackTrace()
                }

            }

        }
    }
}
