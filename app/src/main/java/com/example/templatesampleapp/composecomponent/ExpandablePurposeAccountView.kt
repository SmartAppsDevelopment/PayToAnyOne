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
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.templatesampleapp.R
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.uimodel.PurposeListItem
import com.example.templatesampleapp.ui.paynow.PayNowViewModel

class ExpandablePurposeAccountView {


    var _viewModel: PayNowViewModel? = null
    val viewModel get() = _viewModel!!
    private val minimumHeight=60.dp
    private val maximumHeight=360.dp

    @Preview
    @Composable
    private fun PreviewPurposeExpandListUi() {
        Column(
            modifier = Modifier
                .padding(30.dp)
                .wrapContentHeight()
//                .heightIn(300.dp, 420.dp)
//                .sizeIn(minHeight = 100.dp, maxHeight = maximumHeight)
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

//        var isCollapsed by remember {
//            mutableStateOf(isCollapsedAllow)
//        }
        val isCollapsed =  viewModel.isPurposeViewCollapsed.collectAsState()

        val listOfItems = remember {
            mutableStateListOf<PurposeListItem>()
//            emptyList<PuroposeList>()
        }
        viewModel.uiUpdatesPurposeItem
            .collectAsState().value.let {
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
                        }.let { listOfAcc ->
                            listOfItems.addAll(listOfAcc)
                        }
//                        updateDataInExpandViewPurpose(mappedList)
                    }
                }
            }
//        var isCollapsed by remember {
//            mutableStateOf(true)
//        }
//        var purposeTitle by remember {
//            mutableStateOf("Select Purpose")
//        }
        val purposeTitle =viewModel.currentTransPurpose.observeAsState(PurposeListItem("Purpose"))
        Card(
            modifier = with(Modifier) {
                if (isCollapsed.value) {
                    sizeIn(
                        minHeight = minimumHeight,
                        maxHeight = maximumHeight
                    )
                        .fillMaxWidth()
                        .animateContentSize()
                } else {
                    sizeIn(minHeight = minimumHeight, maxHeight = maximumHeight)
                        .fillMaxSize()
                        .animateContentSize()
                }
            },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            onClick = {
                val isViewCollapsed = !isCollapsed.value
                viewModel.isPurposeViewCollapsed.value = isViewCollapsed
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
                    AnimateContentInside(isCollapsed.value) {
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
                        AnimateContentInside(!isCollapsed.value) {
                            Text(
                                "Select Purpose",
                                fontWeight = FontWeight.Bold,
                                fontStyle = getFontAspiraDem(),
                                fontSize = 10.sp,
                                color = colorResource(id = R.color.santas_grey)
                            )
                        }
                        Text(
                            purposeTitle.value.name,
                            fontWeight = FontWeight.Bold,
                            fontStyle = getFontAspiraDem(),
                            modifier = Modifier.animateContentSize()
                        )
                    }
                    AnimateContentInside(!isCollapsed.value) {
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
                AnimateContentInside(isCollapsed = isCollapsed.value) {
                    ///  if (!isCollapsed) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            horizontal = 8.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(listOfItems.size) {
                            listOfItems[it].PurposeListItemView {
                                val isViewCollapsed = !isCollapsed.value
                                viewModel.isPurposeViewCollapsed.value = isViewCollapsed
//                                purposeTitle = PurposeListItem(it)
//
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
//        var isCollapsed by remember {
//            mutableStateOf(isCollapsedAllow)
//        }
        val isCollapsed =viewModel.isAccountViewCollapsed.collectAsState()
        val listOfItems = remember {
            mutableStateListOf<Accounts>()
        }
        viewModel.uiUpdates
            .collectAsState()
            .value.let {
                when (it) {
                    is ResponseState.Error -> {
                    }
                    is ResponseState.Idle -> {
                    }
                    is ResponseState.Loading -> {
                    }
                    is ResponseState.Success -> {
//                        val mappedList = it.data!!.map {
//                            it!!.toAccountListItem()
//                        }
                        listOfItems.addAll(it.data!!.map { it!! })
                    }
                }
            }
        var purposeTitle by remember {
            mutableStateOf("Select Purpose")
        }
        Card(
            modifier = with(Modifier) {
                if (isCollapsed.value) {
                    sizeIn(
                        minHeight = minimumHeight,
                        maxHeight = maximumHeight
                    )
                        .fillMaxWidth()
                        .animateContentSize()
                } else {
                    sizeIn(minHeight = minimumHeight, maxHeight = maximumHeight)
                        .fillMaxSize()
                        .animateContentSize()
                }
            },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            onClick = {
                val isViewCollapsed = !isCollapsed.value
                viewModel.isAccountViewCollapsed.value = isViewCollapsed
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

                    AnimateContentInside(isCollapsed.value) {
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
                        AnimateContentInside(!isCollapsed.value) {
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
                        AnimateContentInside(!isCollapsed.value) {
                            Text(
                                "0100423401032001",
                                fontWeight = FontWeight.Bold,
                                fontStyle = getFontAspiraDem(),
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.woodsmoke)
                            )
                        }
                    }
                    AnimateContentInside(!isCollapsed.value) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                            contentDescription = "",
                            tint = colorResource(id = R.color.lochmara)
                        )
                    }
                }



                AnimateContentInside(isCollapsed = isCollapsed.value) {
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
                                    val isViewCollapsed = !isCollapsed.value
                                    viewModel.isAccountViewCollapsed.value = isViewCollapsed
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
                    viewModel.currentTransPurpose.value = PurposeListItem(name)
                    viewModel.isPurposeSelected = true
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
                    viewModel.currentSelectAccount.value = this
                    viewModel.isAccountSelected = true
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


}