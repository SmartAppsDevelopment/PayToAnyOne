package com.example.templatesampleapp.customview

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class FixedKeyboardEditText(context: Context, attributeSet: AttributeSet?) : androidx.appcompat.widget.AppCompatEditText(context, attributeSet) {
    private var showKeyboardDelayed = false
    /**
     * Will request focus and try to show the keyboard.
     * It has into account if the containing window has focus or not yet.
     * And delays the call to show keyboard until it's gained.
     */
    fun focusAndShowKeyboard() {
        requestFocus()
        showKeyboardDelayed = true
        maybeShowKeyboard()
    }

    @Override
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus);
        maybeShowKeyboard()
    }

    private fun maybeShowKeyboard() {
        if (hasWindowFocus() && showKeyboardDelayed) {
            if (isFocused) {
                post {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
                }
            }
            showKeyboardDelayed = false
        }
    }
}