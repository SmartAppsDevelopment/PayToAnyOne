package com.example.templatesampleapp.ui.payeedetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.PayeesitemsAdapter
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentMyPayeesBinding
import com.example.templatesampleapp.databinding.FragmentPayessDetailBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.uimodel.ToolBarRef
import com.example.templatesampleapp.ui.frag_payes.PayeesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PayeesDetailsFragment :
    BaseFragment<FragmentPayessDetailBinding>(R.layout.fragment_payess_detail) {

    private val payessDetails by navArgs<PayeesDetailsFragmentArgs>()
    override val viewModel by viewModels<PayeesDetailViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payessDetails.accountDetails?.let {
            binding.datamodel = it.toCardViewRef({
                showLog("Edit Click ")
            }, {
                showLog("Del Click ")
            })
        }
        binding.btnPayNow.setOnClickListener {
            val dir = PayeesDetailsFragmentDirections.actionPayeesDetailsFragmentToPayNowFragment()
            dir.payesDetails = payessDetails.accountDetails
            findNavController().safeNavigate(dir)
        }

    }


    override fun getToolbar() = ToolBarRef("Payees Details", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


}