package com.example.templatesampleapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import com.example.templatesampleapp.ui.activmain.MainActivity

abstract class BaseFragmentCompose() : Fragment() {
//    lateinit var binding: T
//    val isBindingInit by lazy { this::binding.isInitialized }
//    abstract val viewModel: BaseViewModel
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(inflater, layoutId, null, false)
//        binding.lifecycleOwner = viewLifecycleOwner
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context ?: return
    }

    override fun onResume() {
        super.onResume()
        with((activity as MainActivity).binding) {
            topBarMain.toolbar = getToolbar().apply {
                if (hideSearchIcon) {
                   topBarMain.ivSearchIcon.visibility=View.INVISIBLE
                }else{
                    topBarMain.ivSearchIcon.visibility=View.VISIBLE
                }
            }
        }

    }

    abstract fun getToolbar(): ToolBarModel



}