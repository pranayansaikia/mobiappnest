package com.mobiappnest.util
import CoinViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiappnest.domain.usecase.GetCoinsUseCase

class CoinViewModelFactory(private val getCoinsUseCase: GetCoinsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
            return CoinViewModel(getCoinsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
