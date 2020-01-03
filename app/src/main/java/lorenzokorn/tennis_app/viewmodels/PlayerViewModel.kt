package lorenzokorn.tennis_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lorenzokorn.tennis_app.database.PlayerRepository
import lorenzokorn.tennis_app.models.Player

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val playerRepository = PlayerRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.IO)

    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()
    val players = MutableLiveData<List<Player>>()

    fun getPlayers() {
        players.value = playerRepository.getPlayers()
    }

    fun addPlayer(player: Player) {
        mainScope.launch {
            playerRepository.insertPlayer(player)
        }
        success.value = true
    }

    /**
     * validates all the input field that makes up the user.
     * @return boolean if all field are valid
     */
    private fun isPlayerValid(player: Player): Boolean {
        return when {
            // first name contains numbers or special characters
            isNameValid(player.firstName) -> {
                error.value =
                    "First name cannot be empty and cannot contain digits or special characters"
                false
            }
            isNameValid(player.prefix) -> {
                error.value =
                    "Prefix cannot contain digits or special characters"
                false
            }
            isNameValid(player.lastName) -> {
                error.value =
                    "Last name cannot be empty and cannot contain digits or special characters"
                false
            }
            else -> true
        }
    }

    /**
     * Checks if the name contains digits, spaces, or special characters.
     *
     * @param name firstName, prefix, or lastName
     * @return boolean if the name is valid
     */
    private fun isNameValid(name: String): Boolean {
        return !name.matches(Regex("\\W+")) &&
                !name.matches(Regex("\\D+"))
    }
}