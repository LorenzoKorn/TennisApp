package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_users.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Player
import lorenzokorn.tennis_app.models.PlayerAdapter
import lorenzokorn.tennis_app.viewmodels.MatchViewModel
import lorenzokorn.tennis_app.viewmodels.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
class Players : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var matchViewModel: MatchViewModel
    private var players = arrayListOf<Player>()
    private var playerAdapter = PlayerAdapter(players) { player -> openPlayerHome(player) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // shows the actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRecyclerView()
        initFab()
        initWeatherButton()
    }

    private fun initView() {
        playerViewModel.players.observe(this, Observer {
            this@Players.players.clear()
            this@Players.players.addAll(it)
            playerAdapter.notifyDataSetChanged()
        })
        playerViewModel.getPlayers()
    }

    private fun initRecyclerView() {
        rvPlayers.layoutManager =
            LinearLayoutManager(this@Players.context, RecyclerView.VERTICAL, false)
        rvPlayers.adapter = playerAdapter
        rvPlayers.addItemDecoration(DividerItemDecoration(this@Players.context, 0))
    }

    private fun initFab() {
        players_fab.setOnClickListener {
            findNavController().navigate(R.id.users_to_createUser)
        }
    }

    private fun initWeatherButton() {
        weather_btn.setOnClickListener {
            findNavController().navigate(R.id.open_weather)
        }
    }

    private fun openPlayerHome(player: Player) {
        val args = Bundle()
        args.putParcelable("player", player)
        findNavController().navigate(R.id.users_to_userHome, args)
    }
}
