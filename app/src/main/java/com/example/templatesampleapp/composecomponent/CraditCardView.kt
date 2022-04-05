package com.example.templatesampleapp.composecomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.example.templatesampleapp.R
import com.example.templatesampleapp.helper.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn

class CraditCardView {

    val topTitle = MutableStateFlow("")
    val allowToShowSideButton = MutableStateFlow(true)
    val iconID = MutableStateFlow<Int?>(null)
    val acNo = MutableStateFlow("")
    val bankName = MutableStateFlow("")


    @Preview
    @Composable
    private fun PreViewCraditCard() {
        //CraditCardViewUi()
        Column {

            CraditCardViewUi()
            SpecialTypeCircleView("BI")
            SpecialTypeCircleView(drawaleID = R.drawable.man)
             TextViewWithTitle("Uzair","Developer")
        }
    }

    @Composable
    fun CraditCardViewUi(isTextAllowed:Boolean=true) {

        val isAllowToShowSideButton = allowToShowSideButton.collectAsState()
        val iconsid=iconID.collectAsState()
        Card(shape = RoundedCornerShape(30.dp)) {
            Column(
                modifier = Modifier.background(
                    Brush.linearGradient(
                        colors = listOf(
                            colorResource(id = R.color.gradient_start),
                            colorResource(id = R.color.gradient_end)
                        )
                    )
                )
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    val (bigCircle, edit, bottomContent, delete) = createRefs()

                    SpecialTypeCircleView(
                        text = if(isTextAllowed) "BI" else null,
                        drawaleID = iconsid.value,
                        modifier = Modifier
                            .constrainAs(bigCircle) {
                                start.linkTo(parent.start)
                            })
                    if(isAllowToShowSideButton.value) {
                        ShowEditIconButton(
                            Modifier
                                .constrainAs(edit) {
                                    end.linkTo(delete.start)
                                    top.linkTo(delete.top)
                                    bottom.linkTo(delete.bottom)
                                })
                        ShowDeleteIconButton(
                            Modifier
                                .size(28.dp)
                                .constrainAs(delete) {
                                    end.linkTo(parent.end)
                                    top.linkTo(bigCircle.top)
                                    bottom.linkTo(bigCircle.bottom)
                                })
                    }
                    Column(modifier = Modifier
                        .padding(top = 16.dp)
                        .constrainAs(bottomContent) {
                            start.linkTo(parent.start)
                            top.linkTo(bigCircle.bottom)
                        }) {
                        val title = topTitle.collectAsState()
                        val accNo = acNo.collectAsState()
                        val bnkName = bankName.collectAsState()

                        Text(
                            text = title.value,
                            fontSize = 20.sp,
                            fontFamily = firaSansFamily,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        TextViewWithTitle(
                            title = "Account Number",
                            msg = accNo.value
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        TextViewWithTitle(
                            title = "Bank Name",
                            msg = bnkName.value
                        )
                    }
                }
            }
        }
    }


    @Composable
    private fun SpecialTypeCircleView(
        text: String? = null,
        drawaleID: Int? = null,
        modifier: Modifier = Modifier
    ) {
        val title = topTitle.collectAsState()
        Box(
            modifier = modifier
                .size(43.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.black_trans))
        ) {
            if ((text != null) and (drawaleID == null))
                Text(
                    text = title.value.splitNameToCapital(),
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            if (drawaleID != null)
                Image(
                    painter = painterResource(id = drawaleID),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
        }
    }

    @Composable
    private fun ShowDeleteIconButton(modifier: Modifier = Modifier) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(1.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_button_del),
                "contentDescription",
                modifier = Modifier.size(10.dp),
                tint = Color.Red
            )
        }
    }

    @Composable
    private fun ShowEditIconButton(modifier: Modifier = Modifier) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .height(26.dp)
                .width(60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(0.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Edit", fontSize = 10.sp, modifier = Modifier.padding(end = 4.dp), fontWeight = FontWeight.ExtraBold)
                Icon(
                    painter = painterResource(id = R.drawable.icon_edit),
                    "contentDescription",
                    modifier = Modifier.size(8.dp)
                )
            }
        }
    }

    @Composable
    private fun TextViewWithTitle(title: String, msg: String, modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                title,
                modifier = modifier,
                fontSize = 8.sp,
                fontStyle = getFontAspiraReg(),
                color = Color.White
            )
            Text(
                msg,
                modifier = modifier.padding(top = 2.dp),
                fontSize = 12.sp,
                fontStyle = getFontAspiraMed(),
                color = Color.White
            )
        }
    }


}