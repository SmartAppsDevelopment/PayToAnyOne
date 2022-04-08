package com.example.templatesampleapp.ui.payeedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.composecomponent.CraditCardView
import com.example.templatesampleapp.helper.safeNavigate
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PayeesDetailsFragment : BaseFragmentCompose() {

    private val payessDetails by navArgs<PayeesDetailsFragmentArgs>()
    val viewModel by viewModels<PayeesDetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent { MainUi() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        payessDetails.accountDetails?.let {
//            binding.datamodel = it.toCardViewRef({
//                showLog("Edit Click ")
//            }, {
//                showLog("Del Click ")
//            })
//        }
//        binding.btnPayNow.setOnClickListener {
//            val dir = PayeesDetailsFragmentDirections.actionPayeesDetailsFragmentToPayNowFragment()
//            dir.payesDetails = payessDetails.accountDetails
//            findNavController().safeNavigate(dir)
//        }

    }


    override fun getToolbar() = ToolBarModel("Payees Details", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    })

    @Preview
    @Composable
    fun MainUiPreview() {
        Column(modifier = Modifier.padding(30.dp)) {
            // BottomButton()
            MainUi()
        }
    }

    @Composable
    fun MainUi() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                CraditCardView().apply {
                    val (icon, nam, accNo, bnknam) = payessDetails.accountDetails!!
                    topTitle.value = nam
                    acNo.value = accNo
                    bankName.value = bnknam

                }.CraditCardViewUi()
                Spacer(modifier = Modifier.size(6.dp))
                TapAndPay()
            }
            BottomButton({
                val dir =
                    PayeesDetailsFragmentDirections.actionPayeesDetailsFragmentToPayNowFragment()
                dir.payesDetails = payessDetails.accountDetails
                findNavController().safeNavigate(dir)
            }, Modifier.align(Alignment.BottomCenter))
        }
    }

    @Composable
    fun TapAndPay(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(colorResource(id = R.color.mischka))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_accounts_card_nfc),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(CircleShape)
                    .background(color = Color.Black)
                    .padding(6.dp),
                tint = Color.White
            )
            Text(stringResource(id = R.string.tap_amp_pay))
        }
    }

    @Composable
    fun BottomButton(callOnClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            onClick = { callOnClick() },
            modifier = modifier
                .padding(bottom = 6.dp)
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.lochmara
                )
            )
        ) {
            Text(text = "Pay Now", color = Color.White)
        }
    }

}