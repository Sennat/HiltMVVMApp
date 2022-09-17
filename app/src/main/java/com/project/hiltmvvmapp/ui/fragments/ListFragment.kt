package com.project.hiltmvvmapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.project.hiltmvvmapp.R
import com.project.hiltmvvmapp.databinding.FragmentListBinding
import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.states.ResultState
import com.project.hiltmvvmapp.ui.activities.MainActivity
import com.project.hiltmvvmapp.viewAdapters.ListViewAdapter

private const val TAG = "FruitsFragment"

class DashboardFragment : BaseFragment() {

    private var _binding: FragmentListBinding? = null
    private val dashboardViewAdapter by lazy { ListViewAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.progress.visibility = View.VISIBLE
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        appViewModel.fruitsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.LOADING -> {
                    Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
                }
                is ResultState.SUCCESS<*> -> {
                    binding.progress.visibility = View.GONE
                    //Toast.makeText(requireContext(), state.fruits.lastOrNull()?.name, Toast.LENGTH_LONG).show()
                    binding.apply {
                        dashboardViewAdapter.updateFruitData(state.response as List<Fruit>)
                        recyclerView.adapter = dashboardViewAdapter
                    }
                }
                is ResultState.UPDATED -> {
                    // no-operation
                }
                is ResultState.FAILURE -> {
                    binding.progress.visibility = View.GONE
                    Log.e(TAG, "onCreateView: ${state.error.localizedMessage}", state.error)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        appViewModel.fruitsLiveData.removeObservers(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}