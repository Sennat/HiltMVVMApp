package com.project.hiltmvvmapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.hiltmvvmapp.databinding.FragmentCreateBinding
import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.model.Nutrition
import com.project.hiltmvvmapp.states.ResultState

private const val TAG = "CreateFruitFragment"

class CreateFragment : BaseFragment() {

    private var _binding: FragmentCreateBinding? = null

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
        return binding.root
    }

    private fun initViewModel() {
        appViewModel.newlyCreatedFruitsLiveData.observe(viewLifecycleOwner) {
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
            appViewModel.createNewFruit(
                Fruit(
                    binding.inputFruitName.toString(),
                    binding.inputGenus.toString(),
                    binding.inputFamily.toString(),
                    binding.inputOrder.toString(),
                    nutritions = Nutrition(
                    binding.inputProtein.toString().toInt(),
                    binding.inputCarb.toString().toInt(),
                    binding.inputFat.toString().toDouble(),
                    binding.inputCalories.toString().toInt(),
                    binding.inputSugar.toString().toDouble(),
                    )
                )
            )
        }
    }


    override fun onStop() {
        super.onStop()
        appViewModel.newlyCreatedFruitsLiveData.removeObservers(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}