package ru.stas.viewmodeldemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val RESULT_KEY = "Euro Value"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val rate = 0.74f
    private var dollarText = ""
    private var result: MutableLiveData<Float> = savedStateHandle.getLiveData(RESULT_KEY)

    fun setAmount(value: String){
        this.dollarText = value
        val convertValue = value.toFloat() * rate
        result.value = convertValue
        savedStateHandle[RESULT_KEY] = convertValue
    }
    fun getResult(): MutableLiveData<Float>{
        return result
    }

    fun cleanResult(): Int {
        return 0
    }
}