package com.example.templatesampleapp.bindings

import androidx.databinding.BindingAdapter
import com.example.templatesampleapp.helper.AppBaseSetting
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


@BindingAdapter( "setDrawIcon")
fun ShapeableImageView.setDrawIcon(drawableId:String) {
    if(drawableId.isNotBlank()){
        Picasso.get().load(Integer.parseInt(drawableId)).error(
            AppBaseSetting.defaultIconDrawable).into(this)
    }else{
        Picasso.get().load(AppBaseSetting.defaultIconDrawable).into(this)
    }
}