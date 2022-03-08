package com.example.templatesampleapp.ui.amount

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.databinding.FragmentAmountBinding
import com.example.templatesampleapp.helper.safeNavigate
import com.example.templatesampleapp.helper.showKeyboard
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.helper.showToast
import com.example.templatesampleapp.model.uimodel.AmountFragmentModel
import com.example.templatesampleapp.model.uimodel.ToolBarRef
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AmountFragment :
    BaseFragment<FragmentAmountBinding>(R.layout.fragment_amount) {

    private val transData by navArgs<AmountFragmentArgs>()
    override val viewModel by viewModels<AmountViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLog(transData.toString())
        setDataToView()



        attachLister()

    }

    private fun attachLister() = with(binding) {
        tvAmount.addTextChangedListener {
            viewModel.txtChangeListener = tvAmount.text.toString()
        }
        amountFrag.apply {
            amount = viewModel.txtChangeListener

        }
        tvAmount.setText(viewModel.txtChangeListener)
        btnProceedToPay.setOnClickListener {
            val dir = AmountFragmentDirections.actionAmountFragmentToReviewPaymentFragment(
                amountFrag.amount,
                transData.payees
            )
            dir.transAaccount=transData.transAaccount
            dir.transPurpose=transData.transPurpose
            findNavController().safeNavigate(dir)
        }

        edtText.visibility = View.GONE
        tvAddcmnt.setOnClickListener {
            with(binding) {
                edtText.visibility = View.VISIBLE
                edtText.showKeyboard()
            }
        }
        edtText.addTextChangedListener {
            val txt = edtText.text.toString()

            if (txt.isNotEmpty())
                tvAddcmnt.text = txt
            else
                tvAddcmnt.text = getString(R.string.add_comment)
        }
        tvNameSender.text = (transData.transAaccount?.name ?: "No Name")
    }

    private val amountFrag = AmountFragmentModel().apply {
    }

    private fun setDataToView() {
        binding.datamodel = amountFrag
    }


    override fun getToolbar() = ToolBarRef("Pay", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })


}