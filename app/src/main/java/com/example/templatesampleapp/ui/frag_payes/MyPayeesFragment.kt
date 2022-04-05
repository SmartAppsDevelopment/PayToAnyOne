package com.example.templatesampleapp.ui.frag_payes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.templatesampleapp.R
import com.example.templatesampleapp.adapter.PayeesItemsAdapter
import com.example.templatesampleapp.base.BaseFragment
import com.example.templatesampleapp.base.BaseFragmentCompose
import com.example.templatesampleapp.databinding.FragmentMyPayeesBinding
import com.example.templatesampleapp.helper.*
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.ToolBarModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MyPayeesFragment : BaseFragmentCompose() {
////class MyPayeesFragment : BaseFragment<FragmentMyPayeesBinding>(R.layout.fragment_my_payees) {

    val viewModel by viewModels<PayeesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.getDataFromLocalDb()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            ShowMainUi()
        }
    }

    override fun getToolbar() = ToolBarModel("My Payees", searchClick = {
        showLog("1 Search Click")
    }, userImgClick = {
        showLog("1 Img CLick")
    }).apply {
        hideSearchIcon = false
        hideSideIcon = false
        sideButtonText = "Add"
        sideButtonIcon = R.drawable.icon_add
    }

    @Preview
    @Composable
    fun PreviewUI() {
        val item = Payees("" + R.drawable.man2, "", "", "")
        val thisIsItemList = List(1000) {
            item
        }
        ShowMainUi()
    }

    @Composable
    fun ShowMainUi() {
        val payeesListItems = remember {
            mutableStateListOf<Payees>()
        }
        viewModel.uiUpdates
            .collectAsState().let {
                when (it.value) {
                    is ResponseState.Error -> {
                        requireContext().showToast("Error While Loading Data ")
                    }
                    is ResponseState.Idle -> {
                        //showToast("Idle State" + msg)
                    }
                    is ResponseState.Loading -> {
                        viewModel.showLoading()
                    }
                    is ResponseState.Success -> {
                        viewModel.hideLoading()
                        payeesListItems.addAll(it.value.data!!.map { it!! })
                    }
                }
            }


        val showProgressIndicator = viewModel.showProgressBar.collectAsState()

        Surface(modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.concrete)) {
            Box {
                if (showProgressIndicator.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(60.dp), strokeWidth = 6.dp
                    )
                }
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(payeesListItems.size, itemContent = {
                        payeesListItems[it].InflateView(showProgressIndicator.value)
                    })
                }
            }
        }
    }

    @Composable
    fun Payees.InflateView(progressIndicator: Boolean) {
        if (!progressIndicator) {
            Card(
                elevation = 16.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable {
                    val dir =
                        MyPayeesFragmentDirections.actionPayeesDetailFragmentToPayeesDetailsFragment()
                    dir.accountDetails = this
                    findNavController().safeNavigate(dir)
                }
            ) {
                Box {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = iconDrawableId.toInt()),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 8.dp)
                                .size(37.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = R.color.bombay)),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                        ) {
                            Text(
                                text = "$name ", fontSize = 16.sp, fontFamily = FontFamily(
                                    Font(
                                        R.font.aspira_bold,
                                        FontWeight.Light
                                    )
                                ), modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "$accountNumber ", fontSize = 12.sp, fontFamily = FontFamily(
                                    Font(
                                        R.font.aspira_demi,
                                        FontWeight.Light
                                    )
                                ), modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "$bankName ",
                                fontSize = 10.sp,
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.aspira_medium,
                                        FontWeight.Light
                                    )
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                color = colorResource(id = R.color.santas_grey)
                            )
                        }

                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(22.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}








