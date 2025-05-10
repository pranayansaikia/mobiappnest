package com.mobiappnest

import CoinViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobiappnest.util.RetrofitInstance
import com.mobiappnest.data.repository.CoinRepositoryImpl
import com.mobiappnest.domain.usecase.GetCoinsUseCase
import com.mobiappnest.presentation.ui.CoinScreen

import com.mobiappnest.util.CoinViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = CoinRepositoryImpl(RetrofitInstance.api)
        val useCase = GetCoinsUseCase(repository)
        val factory = CoinViewModelFactory(useCase)

        setContent {
            val viewModel: CoinViewModel = viewModel(factory = factory)
            CoinScreen(viewModel = viewModel)
        }
    }
}
