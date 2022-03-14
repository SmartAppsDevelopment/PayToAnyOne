package com.example.templatesampleapp.bindings

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton


@BindingAdapter( "setMaterialDrawIcon")
fun MaterialButton.setDrawIcon(drawableId:Int) {
    setIconResource(drawableId)
}