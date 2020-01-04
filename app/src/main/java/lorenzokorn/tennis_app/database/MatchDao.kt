package lorenzokorn.tennis_app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import lorenzokorn.tennis_app.models.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM `match` WHERE player_home_1 = :player_id OR player_home_2 = :player_id OR challenger_1 = :player_id OR challenger_2 = :player_id")
    fun getMatches(player_id: Long): List<Match>

    @Insert
    fun insertMatch(match: Match)

    @Delete
    fun deleteMatch(match: Match)
}