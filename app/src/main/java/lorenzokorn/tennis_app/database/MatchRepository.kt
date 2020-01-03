package lorenzokorn.tennis_app.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Insert
import lorenzokorn.tennis_app.models.Match
import lorenzokorn.tennis_app.models.Player

class MatchRepository(context: Context) {
    private val matchDao: MatchDao

    init {
        val db = TennisRoomDatabase.getDatabase(context)
        matchDao = db!!.matchDao()
    }

    fun getMatches(id: Long): List<Match> = matchDao.getMatches(id)

    fun insertPlayer(match: Match) = matchDao.insertMatch(match)
}