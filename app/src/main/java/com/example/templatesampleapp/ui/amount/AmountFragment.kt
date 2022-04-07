package com.example.templatesampleapp.ui.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.helper.isAmountVerified
import com.example.templatesampleapp.helper.showLog
import com.example.templatesampleapp.helper.showToast
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AmountFragment : BaseFragmentCompose(){

    private val transData by navArgs<AmountFragmentArgs>()
     val viewModel by viewModels<AmountViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val name=transData.transAaccount?.name ?: "No Name"
            ShowMainUi(userName = name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLog(transData.toString())
//        setDataToView()
//        attachLister()
//        showKeyBoard()
    }

//    private fun showKeyBoard() {
//        binding.tvAmount.focusAndShowKeyboard()
//        //  requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
//    }
//
//    private fun attachLister() = with(binding) {
//        tvAmount.addTextChangedListener {
//            viewModel.txtChangeListener = tvAmount.text.toString()
//        }
//        amountFrag.apply {
//            amount = viewModel.txtChangeListener
//
//        }
//        tvAmount.setText(viewModel.txtChangeListener)
//        btnProceedToPay.setOnClickListener {
//            val dir = AmountFragmentDirections.actionAmountFragmentToReviewPaymentFragment(
//                amountFrag.amount,
//                transData.payees
//            )
//            dir.transAaccount = transData.transAaccount
//            dir.transPurpose = transData.transPurpose
//
//            if (amountFrag.amount.isAmountVerified() > 0)
//                findNavController().navigate(dir)
//            else
//                requireContext().showToast(getString(R.string.enter_amount))
//        }
//
//        edtText.visibility = View.GONE
//        tvAddcmnt.setOnClickListener {
//            with(binding) {
//                edtText.visibility = View.VISIBLE
//                //edtText.showKeyboard()
//            }
//        }
//        edtText.addTextChangedListener {
//            val txt = edtText.text.toString()
//
//            if (txt.isNotEmpty())
//                tvAddcmnt.text = txt
//            else
//                tvAddcmnt.text = getString(R.string.add_comment)
//        }
//        tvNameSender.text = (transData.transAaccount?.name ?: "No Name")
//    }
//
//    private val amountFrag = AmountFragmentModel().apply {
//    }
//
//    private fun setDataToView() {
//        binding.datamodel = amountFrag
//    }

    override fun getToolbar() = ToolBarModel("Pay", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    }).apply {
        hideSideIcon = false
        sideButtonText = "Edit"
        sideButtonIcon = R.drawable.icon_edit
    }


    @Preview
    @Composable
    fun ShowMainUiPreview() {
        Column {
            ShowMainUi("Umer Bilal")
        }
    }

    @Composable
    fun ShowMainUi(userName: String) {

        val textState = remember { mutableStateOf(TextFieldValue("0")) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.concrete))
        ) {
            Text(
                text = userName,
                fontSize = 18.sp,
                color = colorResource(id = R.color.santas_grey),
                modifier = Modifier.padding(start = 16.dp)
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Rs", fontSize = 14.sp, modifier = Modifier
                            .alignByBaseline()
                            .padding(end = 1.dp)
                    )

                    TextField(
                        maxLines = 1,
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Gray,
                            disabledTextColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .alignByBaseline()
                            /// .sizeIn(minWidth = 100.dp, maxWidth = 140.dp)
                            .widthIn(100.dp, 140.dp)
                            .wrapContentWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle.Default.copy(fontSize = 60.sp, color = Color.Black)
                    )
                }
                TextButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(
                            id = R.color.mischka
                        )
                    ),
                    modifier = Modifier.clip(RoundedCornerShape(180.dp)),
                ) {
                    Text(text = "Add Comment", modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp))
                }
            }
            BottomButton(
                callOnClick = {
                    val dir = AmountFragmentDirections.actionAmountFragmentToReviewPaymentFragment(
                        textState.component1().text,
                        transData.payees
                    )
                    dir.transAaccount = transData.transAaccount
                    dir.transPurpose = transData.transPurpose
                    if (textState.component1().text.isAmountVerified() > 0)
                        findNavController().navigate(dir)
                    else
                        requireContext().showToast(getString(R.string.enter_amount))
                },
                Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    fun BottomButton(callOnClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(
            onClick = { callOnClick() },
            modifier = modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.lochmara
                )
            )
        ) {
            Text(text = "Proceed To Pay", color = Color.White)
        }
    }


}