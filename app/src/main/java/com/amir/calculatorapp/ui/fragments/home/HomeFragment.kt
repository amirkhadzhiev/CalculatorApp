package com.amir.calculatorapp.ui.fragments.home

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.amir.calculatorapp.R
import com.amir.calculatorapp.base.BaseFragment
import com.amir.calculatorapp.databinding.HomeFragmentBinding
import com.amir.calculatorapp.ui.fragments.animation.OnSwipeTouchListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(
    HomeFragmentBinding::inflate,
), View.OnClickListener {

    private lateinit var layout: View

    override val viewModel: HomeViewModel by viewModel()

    override fun setupLiveData() {}

    @SuppressLint("ClickableViewAccessibility")
    override fun setupUI() {
        requireActivity().actionBar?.hide()
//        removeToolbarBottomBar()

        binding.keyboardFragment.buttonZero.setAnimation()
        binding.keyboardFragment.buttonZero.setOnClickListener { viewModel.eachClick(R.id.button_zero) }
        binding.keyboardFragment.buttonOne.setAnimation()
        binding.keyboardFragment.buttonOne.setOnClickListener { viewModel.eachClick(R.id.button_one) }
        binding.keyboardFragment.buttonTwo.setAnimation()
        binding.keyboardFragment.buttonTwo.setOnClickListener { viewModel.eachClick(R.id.button_two) }
        binding.keyboardFragment.buttonThree.setAnimation()
        binding.keyboardFragment.buttonThree.setOnClickListener { viewModel.eachClick(R.id.button_three) }
        binding.keyboardFragment.buttonFour.setAnimation()
        binding.keyboardFragment.buttonFour.setOnClickListener { viewModel.eachClick(R.id.button_four) }
        binding.keyboardFragment.buttonFive.setAnimation()
        binding.keyboardFragment.buttonFive.setOnClickListener { viewModel.eachClick(R.id.button_five) }
        binding.keyboardFragment.buttonSix.setAnimation()
        binding.keyboardFragment.buttonSix.setOnClickListener { viewModel.eachClick(R.id.button_six) }
        binding.keyboardFragment.buttonSeven.setAnimation()
        binding.keyboardFragment.buttonSeven.setOnClickListener { viewModel.eachClick(R.id.button_seven) }
        binding.keyboardFragment.buttonEight.setAnimation()
        binding.keyboardFragment.buttonEight.setOnClickListener { viewModel.eachClick(R.id.button_eight) }
        binding.keyboardFragment.buttonNine.setAnimation()
        binding.keyboardFragment.buttonNine.setOnClickListener { viewModel.eachClick(R.id.button_nine) }
        binding.keyboardFragment.buttonMultiply.setAnimation()
        binding.keyboardFragment.buttonMultiply.setOnClickListener { viewModel.eachClick(R.id.button_multiply) }
        binding.keyboardFragment.buttonShare.setAnimation()
        binding.keyboardFragment.buttonShare.setOnClickListener { viewModel.eachClick(R.id.button_share) }
        binding.keyboardFragment.buttonMinus.setAnimation()
        binding.keyboardFragment.buttonMinus.setOnClickListener { viewModel.eachClick(R.id.button_minus) }
        binding.keyboardFragment.buttonPlus.setAnimation()
        binding.keyboardFragment.buttonPlus.setOnClickListener { viewModel.eachClick(R.id.button_plus) }
        binding.blueKeyboardFragment.buttonLeftParenthesis.setAnimation()
        binding.blueKeyboardFragment.buttonLeftParenthesis.setOnClickListener {
            viewModel.eachClick(
                R.id.button_left_parenthesis
            )
        }
        binding.blueKeyboardFragment.buttonRightParenthesis.setAnimation()
        binding.blueKeyboardFragment.buttonRightParenthesis.setOnClickListener {
            viewModel.eachClick(
                R.id.button_right_parenthesis
            )
        }
        binding.blueKeyboardFragment.buttonPercent.setAnimation()
        binding.blueKeyboardFragment.buttonPercent.setOnClickListener { viewModel.eachClick(R.id.button_percent) }
        binding.keyboardFragment.buttonEqually.setAnimation()
        binding.keyboardFragment.buttonEqually.setOnClickListener { viewModel.eachClick(R.id.button_equally) }
        binding.keyboardFragment.buttonComma.setAnimation()
        binding.keyboardFragment.buttonComma.setOnClickListener { viewModel.eachClick(R.id.button_comma) }
        binding.keyboardFragment.buttonClear.setAnimation()
        binding.keyboardFragment.buttonClear.setOnClickListener {
            viewModel.eachClick(R.id.button_clear)
        }
        binding.keyboardFragment.buttonClear.setOnLongClickListener {
            viewModel.deleteAll()
            false
        }

        val listener = object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeLeft() {
                binding.buttonOpenBlueKeyboard.visibility = View.GONE
                binding.blueKeyboardFragment.root.visibility = View.VISIBLE
                binding.blueKeyboardFragment.root.invalidate()
            }

            override fun onSwipeRight() {
                binding.buttonOpenBlueKeyboard.visibility = View.VISIBLE
                binding.blueKeyboardFragment.root.visibility = View.GONE
                binding.blueKeyboardFragment.root.invalidate()
            }

            override fun updateLocation(x: Float, y: Float) {
//                layout.x = layout.x + x
//                layout.y = layout.y + y
            }
        }

        binding.buttonOpenBlueKeyboard.setOnTouchListener(listener)
        binding.blueKeyboardFragment.root.setOnTouchListener(listener)

        binding.buttonOpenBlueKeyboard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_blueKeyboard)
        }

        binding.currentExpressionFragment.txtRad.setOnClickListener {
            binding.currentExpressionFragment.txtRad.setText("RAD")
        }


        observers()
    }

    private fun observers() {
        viewModel.typedLiveData.observe(viewLifecycleOwner) {
            binding.currentExpressionFragment.textMain.setText(it)
            binding.currentExpressionFragment.textMain.setSelection(
                binding.currentExpressionFragment.textMain.text.length
            )
        }

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            binding.currentExpressionFragment.textResult.text = it.toString()
        }

        viewModel.fromRoom.observe(viewLifecycleOwner) {
//            binding.recyclerView.apply {
//                this.adapter = HistoryAdapter(it)
//                this.smoothScrollToPosition(it.size-1)
//            }
        }
    }


    private fun TextView.setAnimation() {
        this.setOnClickListener {

            var startSize = 26f
            val endSize = 15f

            val colorFrom = resources.getColor(R.color.blackish)
            var colorTo = resources.getColor(R.color.whitish)

            if (it.id == binding.keyboardFragment.buttonPlus.id ||
                it.id == binding.keyboardFragment.buttonMinus.id ||
                it.id == binding.keyboardFragment.buttonShare.id
            ) startSize = 40f
            else if (it.id == binding.keyboardFragment.buttonMultiply.id) startSize = 30f
//            else if (it.id == binding.parentheses.id) startSize = 22f

            else if (it.id == binding.keyboardFragment.buttonEqually.id) {
                startSize = 37f;colorTo = resources.getColor(R.color.green)
            }

            val animationDuration: Long = 300
            val animator = ValueAnimator.ofFloat(endSize, startSize)
            animator.duration = animationDuration

            animator.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                this.textSize = animatedValue
            }

            animator.start()

//            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
//            colorAnimation.duration = animationDuration

//            colorAnimation.addUpdateListener { animator -> this.setBackgroundColor(animator.animatedValue as Int) }
//            colorAnimation.start()

            onClick(this)
        }
    }

    override fun onClick(v: View?) {
//////        if (v?.id == binding.imageClock.id) {
////            adjustVisibility()
////        }
//////        viewModel.eachClick(v?.id)
//    }
//
////    private fun removeToolbarBottomBar() {
//////        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
////        navBar.visibility = View.GONE
////    }
////
////    private fun adjustVisibility() {
////        binding.historyContainer.isVisible = !binding.historyContainer.isVisible
////        binding.historyLine.isVisible = !binding.historyLine.isVisible
    }
}