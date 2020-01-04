package lorenzokorn.tennis_app.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import lorenzokorn.tennis_app.database.MatchRepository
import lorenzokorn.tennis_app.models.Match

class MatchViewModel(application: Application): AndroidViewModel(application) {

    private val matchRepository = MatchRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.IO)

    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()
    val matches = MutableLiveData<List<Match>>()

    fun getMatches(id: Long) {
        matches.value = matchRepository.getMatches(id)
    }

    fun insertMatch(match: Match) {
        Log.w("match", "$match")
        matchRepository.insertPlayer(match)
    }

    fun deleteMatch(match: Match) {
        matchRepository.deleteMatch(match)
    }
}