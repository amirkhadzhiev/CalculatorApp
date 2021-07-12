package com.amir.calculatorapp.ui.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amir.calculatorapp.R
import com.amir.calculatorapp.base.BaseViewModel
import com.amir.calculatorapp.data.History
import com.amir.calculatorapp.data.repo.MainRepo
import io.kaen.dagger.ExpressionParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(var repository: MainRepo) : BaseViewModel() {

    enum class ClickType(var viewId: Int, var viewType: String? = null) {
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
        LEFT_PARENTHESES(R.id.button_left_parenthesis),
        RIGHT_PARENTHESES(R.id.button_right_parenthesis),
        PERCENTAGE(R.id.button_percent, "%"),
        DIVISION(R.id.button_share, "/"),
        MULTIPLY(R.id.button_multiply, "*"),
        MINUS(R.id.button_minus, "-"),
        ADDITION(R.id.button_plus, "+"),
        COMMA(R.id.button_comma, ","),
//        CHANGE(R.id.plusMinus, "-/+"),
//        BACKSPACE(R.id.backSpace),
        EQUAL(R.id.button_equally),
        DELETE(R.id.button_clear);
//        CLOCK(R.id.imageClock);

        companion object {
            fun operation(value: Int?) = values().find { it.viewId == value }
            var isContains = false
        }
    }

    var typedLiveData: MutableLiveData<String> = MutableLiveData("")
    var resultLiveData: MutableLiveData<String> = MutableLiveData()
    var fromRoom: LiveData<List<History>> = repository.readAllData()

    var typedOnes = ""
    var result = ""

    fun clickNum(int: Int) {
        typedLiveData.value = "${typedLiveData.value}$int"
    }



    fun eachClick(viewId: Int?) {
        val operation = ClickType.operation(viewId)
        Log.d("fkjdlf;kajl", "eachClick: " + operation?.viewType )

        if (operation?.viewType != null) {
            operationClick(operation)
        } else {
            nonOperation(operation?.viewId)
        }
        dataSend()
    }

    fun deleteAll(){
            typedOnes = ""
            result = ""
    }

    private fun nonOperation(viewId: Int?) {



//        if (viewId == ClickType.BACKSPACE.viewId) {
//            typedOnes = typedOnes.dropLast(1)
//        }

        if (viewId == ClickType.DELETE.viewId) {
            typedOnes = typedOnes.dropLast(1)
//            typedOnes = ""
//            result = ""
        }

        //===============================EQUAL==============================
        if (viewId == ClickType.EQUAL.viewId) {
            if (!result.isNullOrBlank() && !typedOnes.isNullOrBlank()) {
                typedOnes = ExpressionParser().evaluate(typedOnes).toString()
                resultLiveData.value = String()

                viewModelScope.launch(Dispatchers.IO) {
                    //TODO add second param for History
                    repository.insertModel(History(null, typedOnes, "asd"))
                }
            }
        }

//        =========================REQUEST TO ROOM==========================
//        if (viewId == ClickType.CLOCK.viewId) {
//            fromRoom = repository.readAllData()
//        }

        if (viewId == ClickType.LEFT_PARENTHESES.viewId) {
            if (!ClickType.isContains) {
                typedOnes = "$typedOnes("
                ClickType.isContains = true
            } else if (ClickType.isContains) {
                typedOnes = "$typedOnes)"
                ClickType.isContains = false
            }
        }

        if (viewId == ClickType.RIGHT_PARENTHESES.viewId) {
            if (!ClickType.isContains) {
                typedOnes = "$typedOnes("
                ClickType.isContains = true
            } else if (ClickType.isContains) {
                typedOnes = "$typedOnes)"
                ClickType.isContains = false
            }
        }
    }

    private fun operationClick(clickedOne: ClickType?) {
            typedOnes += clickedOne?.viewType

        if (typedOnes.contains("%")
            || typedOnes.contains("/")
            || typedOnes.contains("*")
            || typedOnes.contains("+")
            || typedOnes.contains("-")
        ) {
            try {
                result = ExpressionParser().evaluate(typedOnes).toString()
                resultLiveData.value = result
            } catch (e: Exception) {
                resultLiveData.value = String()
            }
        } else {
            resultLiveData.value = String()
        }
    }

    private fun dataSend() {
        if (typedOnes.isNotEmpty()) {
            typedLiveData.value = typedOnes
        } else {
            typedLiveData.value = ""
            resultLiveData.value = ""
        }
    }
}