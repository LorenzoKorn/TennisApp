package lorenzokorn.tennis_app.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import lorenzokorn.tennis_app.database.MatchRepository
import lorenzokorn.tennis_app.models.Match

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    private val matchRepository = MatchRepository(application.applicationContext)

    val matches = MutableLiveData<List<Match>>()

    fun getMatches(id: Long) {
        matches.value = matchRepository.getMatches(id)
    }

    fun insertMatch(match: Match) {
        matchRepository.insertPlayer(match)
    }

    fun deleteMatch(match: Match) {
        matchRepository.deleteMatch(match)
    }
}