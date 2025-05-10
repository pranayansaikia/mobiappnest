package com.mobiappnest.presentation.viewmodel

import CoinViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiappnest.domain.usecase.GetCoinsUseCase

class ViewModelFactory(private val useCase: GetCoinsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinViewModel::class.java)) {
            return CoinViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}