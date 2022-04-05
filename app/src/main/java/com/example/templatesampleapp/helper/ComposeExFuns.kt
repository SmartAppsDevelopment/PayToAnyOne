package com.example.templatesampleapp.helper


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.templatesampleapp.R



fun getFontAspiraReg() = FontStyle(R.font.aspira_regular)
fun getFontAspiraBol() = FontStyle(R.font.aspira_bold)
fun getFontAspiraDem() = FontStyle(R.font.aspira_demi)
fun getFontAspiraLit() = FontStyle(R.font.aspira_light)
fun getFontAspiraMed() = FontStyle(R.font.aspira_medium)
fun getFontAspiraThin() = FontStyle(R.font.aspira_thin)

val firaSansFamily = FontFamily(
    Font(R.font.aspira_light, FontWeight.Light),
    Font(R.font.aspira_regular, FontWeight.Normal),
    Font(R.font.aspira_thin, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.aspira_medium, FontWeight.Medium),
    Font(R.font.aspira_bold, FontWeight.Bold)
)


fun Context.ShowToast(
    msg: String
) {
    Toast.makeText(
        this,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}


