package com.example.templatesampleapp.ui.frag_payes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.PayeesItemsAdapter
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentMyPayeesBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MyPayeesFragment : BaseFragment<FragmentMyPayeesBinding>(R.layout.fragment_my_payees) {

    override val viewModel by viewModels<PayeesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.getDataFromLocalDb()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListItems.adapter = PayeesItemsAdapter { payees, _ ->
            val dir = MyPayeesFragmentDirections.actionPayeesDetailFragmentToPayeesDetailsFragment()
            dir.accountDetails = payees
            findNavController().safeNavigate(dir)
        }.apply {
            lifecycleScope.launch {
                viewModel.uiUpdates
                    .collectLatest {
                        when (it) {
                            is ResponseState.Error -> {
                                requireContext().showToast("Error While Loading Data ")
                            }
                            is ResponseState.Idle -> {
                                //showToast("Idle State" + msg)
                            }
                            is ResponseState.Loading -> {
                                showLoading()
                            }
                            is ResponseState.Success -> {
                                hideLoading()
                                submitList(it.data)
                            }
                        }
                    }

            }

        }

    }

    private fun showLoading() {
        binding.apply {
            progressCircular.visibleView()
            rvListItems.inVisibleView()
        }
    }

    private fun hideLoading() {
        binding.apply {
            progressCircular.inVisibleView()
            rvListItems.visibleView()
        }
    }


    override fun getToolbar() = ToolBarModel("My Payees", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


}