package com.example.templatesampleapp.composecomponent

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.templatesampleapp.R
import com.example.templatesampleapp.helper.ResponseState
import com.example.templatesampleapp.helper.ShowToast
import com.example.templatesampleapp.helper.getFontAspiraDem
import com.example.templatesampleapp.helper.getFontAspiraMed
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import com.example.templatesampleapp.ui.paynow.PayNowViewModel
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ExpandablePurposeAccountView() {


    var _viewModel: PayNowViewModel? = null
    val viewModel get() = _viewModel!!

    @Preview
    @Composable
    private fun PreviewPurposeExpandListUi() {
        Column(
            modifier = Modifier
                .padding(30.dp)
                .wrapContentHeight()
//                .heightIn(300.dp, 420.dp)
//                .sizeIn(minHeight = 100.dp, maxHeight = 420.dp)
                .background(colorResource(id = R.color.gradient_start))
            ///.fillMaxSize()
        ) {
            ComponentPurposeListItem()
            ComponentAccountListItem()
//            /*PurposeListItem("Sample Name ").*/PurposeListItemView()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ComponentPurposeListItem() {
        val listOfItems = remember{
            mutableStateListOf<PurposeListItem>()
//            emptyList<PuroposeList>()
        }
        viewModel.uiUpdatesPurposeItem
            .collectAsState().value.let{
                when (it) {
                    is ResponseState.Error -> {
                    }
                    is ResponseState.Idle -> {
                    }
                    is ResponseState.Loading -> {
                    }
                    is ResponseState.Success -> {
                        val mappedList = it.data!!.map {
                            it!!

                        }.let { listOfAcc->
                            listOfItems.addAll(listOfAcc)
                        }
//                        updateDataInExpandViewPurpose(mappedList)
                    }
                }
            }

        var isCollapsed by remember {
            mutableStateOf(true)
        }
        var purposeTitle by remember {
            mutableStateOf("Select Purpose")
        }

        Card(
            modifier = with(Modifier) {
                if (isCollapsed) {
                    sizeIn(
                        minHeight = 60.dp,
                        maxHeight = 420.dp
                    )
                        .fillMaxWidth()
                        .animateContentSize()
                } else {
                    sizeIn(minHeight = 60.dp, maxHeight = 420.dp)
                        .fillMaxSize()
                        .animateContentSize()
                }
            },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            onClick = {
                val isViewCollapsed = !isCollapsed
                isCollapsed = isViewCollapsed
            }
        ) {

            Column(verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimateContentInside(isCollapsed) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_search_24_black),
                            "contentDescription",
                            modifier = Modifier
                                .padding(start = 18.dp, end = 8.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = R.color.mischka))
                                .padding(4.dp)
                                .size(28.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .weight(1f)
                    ) {
                        AnimateContentInside(!isCollapsed) {
                            Text(
                                "Select Purpose",
                                fontWeight = FontWeight.Bold,
                                fontStyle = getFontAspiraDem(),
                                fontSize = 10.sp,
                                color = colorResource(id = R.color.santas_grey)
                            )
                        }
                        Text(
                            purposeTitle,
                            fontWeight = FontWeight.Bold,
                            fontStyle = getFontAspiraDem(),
                            modifier = Modifier.animateContentSize()
                        )
                    }
                    AnimateContentInside(!isCollapsed) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                            contentDescription = "",
                            tint = colorResource(id = R.color.lochmara)
                        )
                    }
                }

//                val listOfItems = remember {
//                    mutableStateListOf(
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf"),
//                        PurposeListItem("dslkfjlsdkf")
//                    )
//                }
                AnimateContentInside(isCollapsed = isCollapsed) {
                    ///  if (!isCollapsed) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 8.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(listOfItems.size) {
                            listOfItems[it].PurposeListItemView {
                                val isViewCollapsed = !isCollapsed
                                isCollapsed = isViewCollapsed
                                purposeTitle = it
                            }
                        }
                    }
                    ///  }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ComponentAccountListItem() {
//        val listOfAccounts = remember{
//            emptyList<Accounts>()
//        }

        val listOfItems = remember {
            mutableStateListOf(
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132"),
                Accounts("Umer Bilal", "021312012030213", "2132")
            )
        }


        var isCollapsed by remember {
            mutableStateOf(true)
        }
        var purposeTitle by remember {
            mutableStateOf("Select Purpose")
        }
        Card(
            modifier = with(Modifier)
            {
                if (isCollapsed) {
                    sizeIn(
                        minHeight = 60.dp,
                        maxHeight = 420.dp
                    )
                        .fillMaxWidth()
                        .animateContentSize()
                } else {
                    sizeIn(minHeight = 60.dp, maxHeight = 420.dp)
                        .fillMaxSize()
                        .animateContentSize()
                }
            },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            onClick =
            {
                val isViewCollapsed = !isCollapsed
                isCollapsed = isViewCollapsed
            }
        )
        {

            Column(verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AnimateContentInside(isCollapsed) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_search_24_black),
                            "contentDescription",
                            modifier = Modifier
                                .padding(start = 18.dp, end = 8.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = R.color.mischka))
                                .padding(4.dp)
                                .size(28.dp)
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.icon_tab_bar_accounts24),
                        "contentDescription",
                        modifier = Modifier
                            .padding(start = 6.dp, end = 8.dp)
                            .clip(CircleShape)
                            .background(colorResource(id = R.color.mischka))
                            .padding(4.dp)
                            .size(28.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        AnimateContentInside(!isCollapsed) {
                            Text(
                                "Account",
                                fontWeight = FontWeight.Bold,
                                fontStyle = getFontAspiraDem(),
                                fontSize = 10.sp,
                                color = colorResource(id = R.color.santas_grey)
                            )
                        }
                        Text(
                            purposeTitle,
                            fontWeight = FontWeight.Bold,
                            fontStyle = getFontAspiraDem(),
                            modifier = Modifier.animateContentSize(),
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.black)
                        )
                        AnimateContentInside(!isCollapsed) {
                            Text(
                                "0100423401032001",
                                fontWeight = FontWeight.Bold,
                                fontStyle = getFontAspiraDem(),
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.woodsmoke)
                            )
                        }
                    }
                    AnimateContentInside(!isCollapsed) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                            contentDescription = "",
                            tint = colorResource(id = R.color.lochmara)
                        )
                    }
                }



                AnimateContentInside(isCollapsed = isCollapsed) {
                    ///  if (!isCollapsed) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 8.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        listOfItems.let { sampleItmsList ->
                            items(sampleItmsList.size) {
                                sampleItmsList[it].AccountListItemView {
                                    val isViewCollapsed = !isCollapsed
                                    isCollapsed = isViewCollapsed
                                    purposeTitle = it.name
                                }
                            }
                        }

                    }
                    ///  }
                }
            }
        }
    }

    @Composable
    fun PurposeListItem.PurposeListItemView(click: (String) -> Unit) {
        val context = LocalContext.current
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.alabaster))
                .clickable(onClick = {
                    context.ShowToast(name)
                    click(name)
                })
        ) {
            Text(
                text = name,
                fontSize = 13.sp,
                fontStyle = getFontAspiraMed(),
                modifier = Modifier.padding(start = 52.dp)
            )
        }
    }

    @Composable
    fun Accounts.AccountListItemView(click: (Accounts) -> Unit) {
        val context = LocalContext.current
        Column(
            /* verticalAlignment = Alignment.CenterVertically,*/
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.alabaster))
                .padding(start = 51.dp, top = 10.dp, bottom = 10.dp)
                .wrapContentHeight()
                .clickable(onClick = {
                    context.ShowToast(name)
                    click(this)
                })
        ) {
            Text(
                text = name,
                fontSize = 14.sp,
                fontStyle = getFontAspiraMed(),
                color = colorResource(id = R.color.woodsmoke)
            )
            Text(
                text = accountNumber,
                fontSize = 10.sp,
                fontStyle = getFontAspiraMed(),
                color = colorResource(id = R.color.santas_grey)
            )
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "Rs ",
                    fontSize = 12.sp,
                    fontStyle = getFontAspiraMed(),
                    color = colorResource(id = R.color.lochmara),
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = amount,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = getFontAspiraMed(),
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.alignByBaseline()
                )
            }

        }
    }

    @Composable
    fun AnimateContentInside(isCollapsed: Boolean, content: @Composable () -> Unit) {
        AnimatedVisibility(
            visible = !isCollapsed,
            enter = fadeIn(
                // customize with tween AnimationSpec
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            ),
            // you can also add animationSpec in fadeOut if need be.
            exit = fadeOut(), /*+ shrinkHorizontally()*/

        ) {
            content()
        }
    }


//    fun GetHorizentalAlignment(): BiasAlignment.Horizontal {
//        var horizontalBias by remember { mutableStateOf(-1f) }
//        val alignment by animateHo(horizontalBias)
//        return alignment
//    }
//
//    @Composable
//    private fun animateHorizontalAlignmentAsState(
//        targetBiasValue: Float
//    ): State<BiasAlignment.Horizontal> {
//        val bias by animateFloatAsState(targetBiasValue)
//        return derivedStateOf { BiasAlignment.Horizontal(bias) }
//    }
}