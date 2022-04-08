package com.example.templatesampleapp.composecomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.templatesampleapp.R
import com.example.templatesampleapp.helper.getFontAspiraBol

class TopToolBar {

    @Preview
    @Composable
    fun ToolbarUiPreview() {
        ToolbarUi("This is Top Title"){}
    }

    @Composable
    fun ToolbarUi(topTitle:String,backClick:()->Unit) {

        ConstraintLayout(modifier = Modifier.background(colorResource(id = R.color.concrete)).fillMaxWidth()) {
            val (personIcon, searchIcon, backIcon, title) = createRefs()

            val startGuideLine = createGuidelineFromStart(0.05f)
            val endGuideLine = createGuidelineFromEnd(0.05f)
            val topGuideLine = createGuidelineFromTop(0.1f)

            Image(
                painter = painterResource(id = R.drawable.man),
                "contentDescription",
                modifier = Modifier
                    .padding(start = 1.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.mischka))
                    .padding(4.dp)
                    .size(25.dp)
                    .constrainAs(personIcon) {
                        start.linkTo(startGuideLine)
                        top.linkTo(topGuideLine)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.icon_nav_back),
                "contentDescription",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { backClick() }
                    .constrainAs(backIcon) {
                        end.linkTo(endGuideLine)
                        top.linkTo(searchIcon.top)
                        bottom.linkTo(searchIcon.bottom)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24_black),
                "contentDescription",
                modifier = Modifier
                    .padding(start = 6.dp, end = 8.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.mischka))
                    .padding(4.dp)
                    .size(25.dp)
                    .constrainAs(searchIcon) {
                        start.linkTo(personIcon.end)
                        top.linkTo(topGuideLine)
                    }
            )

            Text(text = topTitle,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = getFontAspiraBol(),
                fontSize = 23.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .constrainAs(title) {
                        start.linkTo(startGuideLine)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(personIcon.bottom)
                    })
        }
    }
}