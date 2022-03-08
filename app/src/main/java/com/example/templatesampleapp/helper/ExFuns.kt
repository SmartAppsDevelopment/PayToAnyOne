package com.example.templatesampleapp.helper

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import com.example.templatesampleapp.model.Accounts
import com.example.templatesampleapp.model.Payees
import com.example.templatesampleapp.model.uimodel.AccountsListItem
import com.example.templatesampleapp.model.uimodel.CardViewRef
import java.text.SimpleDateFormat


inline fun <reified T> T.showLog(msg:String){
    Log.e(T::class.java.toString(), msg)
}

fun Context.showToast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun View.visibleView(){
    visibility=View.VISIBLE
}
fun View.inVisibleView(){
    visibility=View.INVISIBLE
}

fun Payees.toCardViewRef(editClick:()->Unit, delClick:()->Unit)=CardViewRef(name,accountNumber,bankName,name.splitNameToCapital(),editClick,delClick)
fun String.splitNameToCapital():String{
    val array=split(" ")
    var name=""
    array.forEachIndexed { index, str ->
        if(str.isNotEmpty())
        name+=str.toCharArray().get(0).uppercase()
    }
    return name
}

fun NavController.safeNavigate(nav: NavDirections) {
    val destinationId = currentDestination?.getAction(nav.actionId)?.destinationId.orEmpty()
    currentDestination?.let { node ->
        val currentNode = when (node) {
            is NavGraph -> node
            else -> node.parent
        }
        if (destinationId != 0) {
            currentNode?.findNode(destinationId)?.let { navigate(nav) }
        }
    }
}

fun Int?.orEmpty(default: Int = 0): Int {
    return this ?: default
}

fun EditText.showKeyboard() {
    if(requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val showSoftInput = imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
    }
}

fun Accounts.toAccountListItem():AccountsListItem= AccountsListItem(name, accountNumber, amount)

fun AccountsListItem.toAccountItem()= Accounts(name, accountNumber, amount)

fun getCurrentDateTime():String{
    val date=System.currentTimeMillis()
   return SimpleDateFormat("dd MMMM, yyyy").format(date)
}