package com.example.templatesampleapp.ui.frag_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.templatesampleapp.R
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.databinding.FragmentReviewpaymentBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.uimodel.DialogModel
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import com.example.templatesampleapp.ui.dialog.ComponentBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReviewPaymentFragment : BaseFragmentCompose() {

    val viewModel by viewModels<ReviewPaymentViewModel>()
    private val accountDetails by navArgs<ReviewPaymentFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Column(
                Modifier
                    .fillMaxSize()
                    //  .verticalScroll(rememberScrollState())
                    .background(colorResource(id = R.color.concrete))
            ) {
                PaymentMainUi()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.btnProceedToPay.setOnClickListener {
//            showLog("BtnClicked Found 112")
//            ComponentBottomSheetDialog(
//                DialogModel(
//                    accountDetails.payees.name,
//                    accountDetails.transAaccount!!.name,
//                    accountDetails.amount ?: "0"
//                ) { requireContext().showToast("Share Click") }.apply {
//                    accountNo = accountDetails.transAaccount?.accountNumber ?: ""
//                })
//                .show(
//                    requireActivity().supportFragmentManager,
//                    "SdkReviewPayment"
//                )
//        }
//        with(accountDetails) {
//            binding.dataModel =
//                viewModel.getPaymentModel(payees, transAaccount!!, amount!!) {
//                }
//        }
    }

    override fun getToolbar() = ToolBarModel("Review\nPayment", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    }).apply {
        hideSideIcon = false
        sideButtonText = "Schedule"
        sideButtonIcon = R.drawable.icon_schedule_pay
    }

    @Preview
    @Composable
    fun ShowMainPaymentUiPreview() {
//        Column(
//            Modifier
//                .padding(20.dp)
//                .fillMaxSize()) {
            PaymentMainUi()
      ////  }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun PaymentMainUi() {
        Box(
            modifier = Modifier
                .fillMaxSize()


        ) {
            Card(/*onClick = { *//*TODO*//* },*/ shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    colorResource(id = R.color.gradient_start),
                                    colorResource(id = R.color.gradient_end)
                                )
                            )
                        )
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        SpecialTypeCircleView(R.drawable.icon_tab_bar_accounts)
                        Text(
                            text = "TAnia Khan ",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.woodsmoke),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Account Number ",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.oslo_grey)
                        )
                        Text(
                            text = "12412312313 ",
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.woodsmoke)
                        )
                    }

                    Column(
                        Modifier
                            .background(Color.Transparent)
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 30.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        SpecialTypeCircleView(
                            R.drawable.icon_menu_mobile_top_up,
                            bgColorId = R.color.white
                        )
                        Text(
                            text = "TAnia Khan ",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.white),
                            fontWeight = FontWeight.ExtraBold
                        )
                        TextViewWithTitle(title = "Account Number", msg = "21312312312321")
                        TextViewWithTitle(title = "Bank Name", msg = "Ubl Bank ")
                        TextViewWithTitle(title = "Date", msg = "21312312312321")
                    }
                }
            }

            Column(modifier = Modifier
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row {
                    Text(
                        text = "Rs", fontSize = 16.sp, modifier = Modifier
                            .alignByBaseline()
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "3434",
                        maxLines = 1,
                        modifier = Modifier
                            .alignByBaseline()
                            .wrapContentWidth()
                            .background(Color.White),
                        fontSize = 30.sp
                    )
                }
                BottomButton(callOnClick = { /*TODO*/ })
            }
        }
    }

    @Composable
    private fun SpecialTypeCircleView(
        drawaleID: Int? = null,
        modifier: Modifier = Modifier,
        bgColorId: Int = R.color.mischka
    ) {

        Box(
            modifier = modifier
                .padding(bottom = 8.dp)
                .size(38.dp)
                .clip(CircleShape)
                .background(colorResource(id = bgColorId))
        ) {

            if (drawaleID != null)
                Image(
                    painter = painterResource(id = drawaleID),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
        }
    }

    @Composable
    private fun TextViewWithTitle(title: String, msg: String, modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                title,
                modifier = modifier,
                fontSize = 12.sp,
                fontStyle = getFontAspiraReg(),
                color = Color.White
            )
            Text(
                msg,
                modifier = modifier.padding(top = 2.dp),
                fontSize = 14.sp,
                fontStyle = getFontAspiraMed(),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
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