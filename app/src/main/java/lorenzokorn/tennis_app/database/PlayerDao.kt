package lorenzokorn.tennis_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import lorenzokorn.tennis_app.models.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM player")
    fun getPlayers(): List<Player>

    @Query("SELECT * FROM player where id = :id")
    fun getPlayer(id: Long): Player

    @Insert
    fun insertPlayer(player: Player)

    @Update
    fun updateRating(player: Player)
}