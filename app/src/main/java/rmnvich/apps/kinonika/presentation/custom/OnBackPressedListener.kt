package rmnvich.apps.kinonika.presentation.custom

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager


interface OnBackPressedListener {
    fun doBack()
}

open class BaseBackPressedListener(
        private val activity: FragmentActivity) :
        OnBackPressedListener {

    override fun doBack() {
        activity.supportFragmentManager.popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}