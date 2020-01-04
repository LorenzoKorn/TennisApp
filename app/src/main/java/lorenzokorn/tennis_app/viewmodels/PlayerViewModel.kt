package lorenzokorn.tennis_app.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lorenzokorn.tennis_app.database.PlayerRepository
import lorenzokorn.tennis_app.models.Match
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

    fun getPlayer(id: Long): Player {
        return playerRepository.getPlayer(id)
    }

    fun addPlayer(player: Player) {
        mainScope.launch {
            playerRepository.insertPlayer(player)
        }
        success.value = true
    }

    fun updatePlayersRating(match: Match) {
        // player home 1
        updatePlayerRating(match.playerHome1, match.challenger1, match.challenger2, "home", getWinningTeam(match))

        // dont check if match is single
        if (match.playerHome2 != (-11).toLong()) {
            updatePlayerRating(match.playerHome2, match.challenger1, match.challenger2, "home", getWinningTeam(match))
        }

        // player out 1
        updatePlayerRating(match.challenger1, match.playerHome1, match.playerHome2, "out", getWinningTeam(match))

        // dont check if match is single
        if (match.playerHome2 != (-11).toLong()) {
            updatePlayerRating(match.challenger2, match.playerHome1, match.playerHome2, "out", getWinningTeam(match))
        }
    }

    /**
     * @param p player to update
     * @param o1 opponent 1 of player
     * @param o2 opponent 2 of player
     * @param t team
     * @param w winning team
     */
    private fun updatePlayerRating(p: Long, o1: Long, o2: Long?, t: String, w: String) {
        val player = getPlayer(p)
        val opponent1 = getPlayer(o1)
        val opponent2 = getPlayer(o2!!)

        Log.w("info", "$player $opponent1 $opponent2")

        when {
            // home wins
            t == w -> {
                if (opponent2 == null) {
                    player.ratingSingles =
                        (player.ratingSingles + opponent1.ratingSingles - 1.0) / 2.0
                } else {
                    player.ratingDoubles =
                        (player.ratingDoubles + ((opponent1.ratingDoubles + opponent2.ratingDoubles) / 2.0) - 1.0) / 2.0
                }
            }
            // out wins
            t != w -> {
                if (opponent2 == null) {
                    player.ratingSingles =
                        (player.ratingSingles + opponent1.ratingSingles + 1.0) / 2.0
                } else {
                    player.ratingDoubles =
                        (player.ratingDoubles + ((opponent1.ratingDoubles + opponent2.ratingDoubles) / 2.0) + 1.0) / 2.0
                }
            }
        }

        playerRepository.updateRating(player)
    }

    private fun getWinningTeam(match: Match): String {
        var home = 0
        var out = 0

        if (match.setHome1 == 6 || match.setHome1 == 7) home++ else out++
        if (match.setHome2 == 6 || match.setHome2 == 7) home++ else out++
        if (match.setHome3 == 6 || match.setHome3 == 7) home++ else out++

        return if (home > out) "home" else "out"
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