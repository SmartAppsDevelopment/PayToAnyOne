package com.example.templatesampleapp.ui.frag_empty

import androidx.lifecycle.*
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.base.BaseViewModel
import com.example.templatesampleapp.databinding.FragmentMyPayeesBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmptyFragment() :
    BaseFragment<FragmentMyPayeesBinding>(R.layout.fragment_empty) {
    override fun getToolbar() = ToolBarModel("Sample Frag", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")


}