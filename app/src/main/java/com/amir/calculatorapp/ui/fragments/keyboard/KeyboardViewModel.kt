package com.amir.calculatorapp.ui.fragments.keyboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.calculatorapp.R
import com.amir.calculatorapp.base.BaseViewModel
import io.kaen.dagger.DeprecateParser
//import kotlinx.android.synthetic.main.keyboard_fragment.view.*

class KeyboardViewModel : BaseViewModel() {
    enum class ClickType(var viewId: Int, var viewType: String? = null){
        ZERO(R.id.button_zero, "0"),
        ONE(R.id.button_one, "1"),
        TWO(R.id.button_two, "2"),
        THREE(R.id.button_three, "3"),
        FOUR(R.id.button_four, "4"),
        FIVE(R.id.button_five, "5"),
        SIX(R.id.button_six, "6"),
        SEVEN(R.id.button_seven, "7"),
        EIGHT(R.id.button_eight, "8"),
        NINE(R.id.button_nine, "9"),
        SHARE(R.id.button_share, "/"),
        MULTIPLY(R.id.button_multiply, "*"),
        MINUS(R.id.button_minus, "-"),
        PLUS(R.id.button_plus, "+"),
        EQUALLY(R.id.button_equally, "="),
        COMMA(R.id.button_comma, ","),
        CLEAR(R.id.button_clear);

        companion object{
            fun operation(value: Int?) = values().find { it.viewId == value}
        }
    }

    var fieldLiveData: MutableLiveData<String> = MutableLiveData()
    var resultLiveData: MutableLiveData<String> = MutableLiveData()
    var fieldString = ""
    var result = ""

    fun eachClick(viewId: Int?) {
        val operation = ClickType.operation(viewId)
        if (operation?.viewType != null && operation.viewType != "="){
            operationClick(operation)
        } else{
            nonOperation(operation?.viewId)
        }
        if (operation?.viewType == "="){
            if (!result.isNullOrBlank()) fieldString = result
            result = ""
            resultLiveData.value = result
        }
        dataSend()
    }

    private fun dataSend() {
        if (fieldString.isNotEmpty()){
            fieldLiveData.value = fieldString
        }else{
            fieldLiveData.value = ""
        }
    }

    private fun nonOperation(viewId: Int?) {
        if (viewId == ClickType.CLEAR.viewId)
            fieldString = ""
    }

    private fun operationClick(clickType : ClickType) {
        fieldString += clickType?.viewType

        result = DeprecateParser().evaluateExpression(fieldString).toString()

        resultLiveData.value = result
    }

}