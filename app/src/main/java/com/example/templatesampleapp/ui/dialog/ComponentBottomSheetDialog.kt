package com.example.templatesampleapp.ui.dialog

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.templatesampleapp.R
import com.example.templatesampleapp.databinding.PaySuccessDialogBinding
import com.example.templatesampleapp.model.uimodel.DialogModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.drawable.DrawableUtils


class ComponentBottomSheetDialog(var dialogModel: DialogModel) : BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.SheetDialogx)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val dataModel = DataBindingUtil.inflate<PaySuccessDialogBinding>(
            inflater,
            R.layout.pay_success_dialog, container, false
        ).apply {
            model= dialogModel
        }
        dataModel.ivCross.setOnClickListener {
            dismiss()
            findNavController().popBackStack()
        }
isCancelable=false
        return dataModel.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        }
    }
}