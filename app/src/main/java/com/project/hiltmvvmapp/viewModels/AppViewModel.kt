package com.project.hiltmvvmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hiltmvvmapp.model.Fruit
import com.project.hiltmvvmapp.repositories.NetworkRepository
import com.project.hiltmvvmapp.states.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _fruitsLiveData : MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val fruitsLiveData : LiveData<ResultState> get() = _fruitsLiveData

    private val _newlyCreatedFruitsLiveData : MutableLiveData<ResultState> = MutableLiveData(ResultState.LOADING)
    val newlyCreatedFruitsLiveData : LiveData<ResultState> get() = _newlyCreatedFruitsLiveData

    init {
        getAllFruitsData()
    }

    private fun getAllFruitsData() {
        viewModelScope.launch(ioDispatcher) {
            networkRepository.getAllFruitRecords().collect {
                _fruitsLiveData.postValue(it)
            }
        }
    }

    fun createNewFruit(fruit: Fruit) {
        viewModelScope.launch(ioDispatcher) {
            networkRepository.createNewFruitRecords(fruit).collect {
                _newlyCreatedFruitsLiveData.postValue(it)
            }
        }
    }

}