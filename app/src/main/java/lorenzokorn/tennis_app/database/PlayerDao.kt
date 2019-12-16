package lorenzokorn.tennis_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import lorenzokorn.tennis_app.models.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    fun getPlayers(): LiveData<List<Player>>

    @Insert
    fun insertPlayer(player: Player)
}