import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.usecase.GetCoinsUseCase
import com.mobiappnest.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CoinState(
    val coins: List<Coin> = emptyList(),
    val allCoins: List<Coin> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)

class CoinViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(CoinState())
    val state: StateFlow<CoinState> = _state

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            when (val result = getCoinsUseCase()) {
                is Resource.Success -> {
                    _state.value = CoinState(
                        coins = result.data ?: emptyList(),
                        allCoins = result.data ?: emptyList(),
                        loading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = CoinState(
                        coins = emptyList(),
                        allCoins = emptyList(),
                        loading = false,
                        error = result.message ?: "An unknown error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = true)
                }
            }
        }
    }

    fun refreshCoins() {
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
            try {
                when (val result = getCoinsUseCase()) {
                    is Resource.Success -> {
                        _state.value = CoinState(
                            coins = result.data ?: emptyList(),
                            allCoins = result.data ?: emptyList(),
                            loading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            error = result.message ?: "An unknown error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(loading = true)
                    }
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(loading = false, error = e.localizedMessage)
            }
        }
    }

    fun filterCoins(query: String) {
        val filtered = if (query.isBlank()) {
            _state.value.allCoins
        } else {
            _state.value.allCoins.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _state.value = _state.value.copy(coins = filtered)
    }
}

