import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiappnest.domain.model.Coin
import com.mobiappnest.domain.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CoinState(
    val coins: List<Coin> = emptyList(),
    val allCoins: List<Coin> = emptyList(), // Backup for filtering
    val loading: Boolean = true
)

class CoinViewModel(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(CoinState())
    val state: StateFlow<CoinState> = _state

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val coins = getCoinsUseCase()
            _state.value = CoinState(
                coins = coins,
                allCoins = coins,
                loading = false
            )
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
