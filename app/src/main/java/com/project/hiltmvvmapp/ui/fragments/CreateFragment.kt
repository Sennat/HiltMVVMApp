package com.project.hiltmvvmapp.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.project.hiltmvvmapp.R
import com.project.hiltmvvmapp.databinding.FragmentCreateBinding
import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.model.Nutrition
import com.project.hiltmvvmapp.states.ResultState

private const val TAG = "CreateFruitFragment"

class CreateFragment : BaseFragment() {

    private var _binding: FragmentCreateBinding? = null
    private var list: MutableList<EditText>? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)

        initViewModel()
        createNewFruitData()

        list = mutableListOf(
            binding.inputFruitName,
            binding.inputGenus,
            binding.inputFamily,
            binding.inputOrder,
            binding.inputCarb,
            binding.inputProtein,
            binding.inputFat,
            binding.inputCalories,
            binding.inputSugar
        )
        return binding.root
    }

    private fun initViewModel() {
        appViewModel.createFruitsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.UPDATED -> {
                    Toast.makeText(
                        requireContext(),
                        "Fruit created successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ResultState.FAILURE -> {
                    Log.e(TAG, "onCreateView: ${it.error.localizedMessage}", it.error)
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
                else -> {}
            }
        }
    }

    private fun createNewFruitData() {
        binding.btnCreate.setOnClickListener {
            if (list?.let { it -> validateInputs(it) } == true) {
                appViewModel.createNewFruit(
                    Fruit(
                        binding.inputFruitName.toString(),
                        binding.inputGenus.toString(),
                        binding.inputFamily.toString(),
                        binding.inputOrder.toString(),
                        nutritions = Nutrition(
                            binding.inputCarb.toString().toDouble(),
                            binding.inputProtein.toString().toDouble(),
                            binding.inputFat.toString().toDouble(),
                            binding.inputCalories.toString().toInt(),
                            binding.inputSugar.toString().toDouble(),
                        )
                    )
                )
                list?.let { it -> clearInputs(it) }
            }
        }
    }

    private fun validateInputs(inputs: MutableList<EditText>): Boolean {
        listOf(inputs).forEach {
            it.forEach { i ->
                if (!i.toString().isNullOrEmpty()) {
                    i.apply {
                        //setBackgroundResource(R.color.google)
                        setHint(R.string.emptyInput)
                        setHintTextColor(Color.RED)
                    }
                }
            }
            return false
        }
        return true
    }

    private fun clearInputs(inputs: MutableList<EditText>) {
        listOf(inputs).forEach {
            it.forEach { i ->
                i.text.clear()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        appViewModel.createFruitsLiveData.removeObservers(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}