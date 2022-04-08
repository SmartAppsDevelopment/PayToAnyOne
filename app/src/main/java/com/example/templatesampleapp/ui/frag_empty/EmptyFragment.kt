package com.example.templatesampleapp.ui.frag_empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmptyFragment() : BaseFragmentCompose() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Text(text = "Empty Fragment", fontSize = 22.sp)
        }
    }

    override fun getToolbar() = ToolBarModel("Sample Frag", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })

    val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")


}