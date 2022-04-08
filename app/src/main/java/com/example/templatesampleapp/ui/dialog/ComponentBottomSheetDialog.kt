package com.example.templatesampleapp.ui.dialog

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.example.templatesampleapp.R
import com.example.templatesampleapp.model.uimodel.DialogModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ComponentBottomSheetDialog() :
    BottomSheetDialogFragment() {
    lateinit var dialogModel: DialogModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.SheetDialogx)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                DialogPreview()
                isCancelable = false
            }
        }
    }
    /*{

        val dataModel = DataBindingUtil.inflate<PaySuccessDialogBinding>(
            inflater,
            R.layout.pay_success_dialog, container, false
        ).apply {
            model = dialogModel
        }
        dataModel.ivCross.setOnClickListener {
            dismiss()
            findNavController().popBackStack()
        }
        isCancelable = false
        return dataModel.root
    }*/

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
        }
    }


    @Preview
    @Composable
    fun ShowDialogUiPreview() {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            DialogPreview()
        }
    }

    @Composable
    fun DialogPreview() {

        val amount by remember {
            mutableStateOf(dialogModel.amount)
        }

        val paidTo by remember {
            mutableStateOf(dialogModel.receiverName)
        }
        val paidFrom by remember {
            mutableStateOf(dialogModel.senderName)
        }
        val acNo by remember {
            mutableStateOf(dialogModel.accountNo)
        }

        Card(shape = RoundedCornerShape(16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_clear_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .align(Alignment.End)
                        .clip(CircleShape)
                        .background(
                            colorResource(id = R.color.oslo_grey)
                        )
                        .clickable {
                            dismiss()
                            findNavController().popBackStack()
                        }
                        .padding(4.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_credit),
                    contentDescription = "",
                )
                Text(
                    text = stringResource(id = R.string.your_payment_has_been_processed),
                    modifier = Modifier.width(179.dp),
                    color = colorResource(id = R.color.woodsmoke),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.amount_sent),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 10.dp),
                    color = colorResource(id = R.color.bombay),
                    fontSize = 15.sp
                )
                Text(
                    text = amount,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 4.dp),
                    color = colorResource(id = R.color.woodsmoke),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.this_message_can_be_described_in_detail),
                    modifier = Modifier.width(200.dp),
                    color = colorResource(id = R.color.woodsmoke),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string._12_november_2020),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 8.dp),
                    color = colorResource(id = R.color.bombay),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.paid_to),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 2.dp),
                    color = colorResource(id = R.color.bombay),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = paidTo,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 2.dp),
                    color = colorResource(id = R.color.woodsmoke),
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = acNo,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 2.dp),
                    color = colorResource(id = R.color.bombay),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Paid from",
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 8.dp),
                    color = colorResource(id = R.color.bombay),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = paidFrom,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 2.dp),
                    color = colorResource(id = R.color.woodsmoke),
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                ShowEditIconButton(Modifier.padding(top = 16.dp))
            }
        }
    }

    @Composable
    private fun ShowEditIconButton(modifier: Modifier = Modifier) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .height(40.dp)
                .width(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.mischka)),
            contentPadding = PaddingValues(0.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Share",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_edit),
                    "contentDescription",
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}