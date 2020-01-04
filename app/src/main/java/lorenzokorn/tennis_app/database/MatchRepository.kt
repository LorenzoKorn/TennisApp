package lorenzokorn.tennis_app.database

import android.content.Context
import lorenzokorn.tennis_app.models.Match

class MatchRepository(context: Context) {
    private val matchDao: MatchDao

    init {
        val db = TennisRoomDatabase.getDatabase(context)
        matchDao = db!!.matchDao()
    }

    fun getMatches(id: Long): List<Match> = matchDao.getMatches(id)

    fun insertPlayer(match: Match) = matchDao.insertMatch(match)

    fun deleteMatch(match: Match) = matchDao.deleteMatch(match)
}