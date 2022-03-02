package com.example.templatesampleapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.templatesampleapp.model.uimodel.ToolBarRef
import com.example.templatesampleapp.ui.activmain.MainActivity
import com.example.templatesampleapp.ui.activmain.MainViewModel

abstract class BaseFragment<T : ViewDataBinding>(private var layoutId: Int) : Fragment() {
    lateinit var binding: T
    val isBindingInit by lazy { this::binding.isInitialized }
    abstract val viewModel: BaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context ?: return
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).binding.topBarMain.toolbar=getToolbar()
    }
    abstract fun getToolbar():ToolBarRef
}