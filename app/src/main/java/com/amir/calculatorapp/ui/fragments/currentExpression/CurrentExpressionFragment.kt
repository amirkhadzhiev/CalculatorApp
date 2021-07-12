package com.amir.calculatorapp.ui.fragments.currentExpression

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amir.calculatorapp.R

class CurrentExpressionFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentExpressionFragment()
    }

    private lateinit var viewModel: CurrentExpressionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_expression_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentExpressionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}