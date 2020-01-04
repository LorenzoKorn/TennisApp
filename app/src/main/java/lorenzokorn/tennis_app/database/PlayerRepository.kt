package lorenzokorn.tennis_app.database

import android.content.Context
import androidx.lifecycle.LiveData
import lorenzokorn.tennis_app.models.Player

class PlayerRepository(context: Context) {
    private val playerDao: PlayerDao

    init {
        val db = TennisRoomDatabase.getDatabase(context)
        playerDao = db!!.playerDao()
    }

    fun getPlayers(): List<Player> = playerDao.getPlayers()

    fun getPlayer(id:Long):Player = playerDao.getPlayer(id)

    fun insertPlayer(player: Player) = playerDao.insertPlayer(player)

    fun updateRating(player: Player) = playerDao.updateRating(player)
}