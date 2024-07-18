package com.romanydev.twittertask.utlis



import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun Activity.showIndefiniteSnackBar(
    text: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_SHORT
) {
    val rootView = findViewById<View>(android.R.id.content)
    Snackbar.make(rootView, text, length).show()
}


