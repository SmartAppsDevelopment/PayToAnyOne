package com.example.templatesampleapp.ui.frag_review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentReviewpaymentBinding
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.helper.showToast
import com.example.templatesampleapp.model.uimodel.DialogModel
import com.example.templatesampleapp.model.uimodel.ToolBarRef
import com.example.templatesampleapp.ui.dialog.ComponentBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReviewPaymentFragment :
    BaseFragment<FragmentReviewpaymentBinding>(R.layout.fragment_reviewpayment) {

    override val viewModel by viewModels<ReviewPaymentViewModel>()
    val accountDetails by navArgs<ReviewPaymentFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnProceedToPay.setOnClickListener {
            showLog("BtnClicked Found 112")
            ComponentBottomSheetDialog(
                DialogModel(
                    accountDetails.payees.name,
                    accountDetails.transAaccount!!.name,
                    accountDetails.amount ?: "0"
                ) { requireContext().showToast("Share Click") })


                .show(
                requireActivity().supportFragmentManager,
                "SdkReviewPayment"
            )
        }
        with(accountDetails) {
            binding.dataModel =
                viewModel.getPaymentModel(payees, transAaccount!!, transPurpose!!, amount!!) {
                    showLog("sdlkfjsdlkfdsjf")
                }
        }

    }

    override fun getToolbar() = ToolBarRef("Review\nPayment", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


}