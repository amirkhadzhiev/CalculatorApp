package com.amir.calculatorapp.ui.fragments.bluekeyboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amir.calculatorapp.R
import com.amir.calculatorapp.databinding.BlueKeyboardFragmentBinding
import com.amir.calculatorapp.ui.fragments.currentExpression.CurrentExpressionFragment
import com.amir.calculatorapp.ui.fragments.currentExpression.CurrentExpressionViewModel

class BlueKeyboard : Fragment() {

    companion object {
        fun newInstance() = CurrentExpressionFragment()
    }



    private lateinit var viewModel: CurrentExpressionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.blue_keyboard_fragment, container, false)

        val view = BlueKeyboardFragmentBinding.inflate(layoutInflater)

        view.imageOpenBlueKeyboard.setOnClickListener {
            findNavController().navigate(R.id.action_blueKeyboard_to_homeFragment)
        }

        return BlueKeyboardFragmentBinding.inflate(layoutInflater).root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentExpressionViewModel::class.java)
        // TODO: Use the ViewModel
        requireActivity().actionBar?.hide()
    }
}
