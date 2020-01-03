package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_create_match.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Match
import lorenzokorn.tennis_app.models.Player
import lorenzokorn.tennis_app.viewmodels.MatchViewModel
import lorenzokorn.tennis_app.viewmodels.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateMatch : Fragment() {
    lateinit var playerViewModel: PlayerViewModel
    lateinit var matchViewModel: MatchViewModel
    private val players = arrayListOf(Player("Select","","player",0.0,0.0,-11))
    lateinit var playerHome1: Player
    lateinit var playerHome2: Player
    lateinit var challenger1: Player
    lateinit var challenger2: Player

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playerViewModel = ViewModelProviders.of(activity as AppCompatActivity).get(PlayerViewModel::class.java)
        matchViewModel = ViewModelProviders.of(activity as AppCompatActivity).get(MatchViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPlayers()
        initMatchFab()
    }

    /**
     * fetch players from room
     */
    private fun initPlayers() {
        playerViewModel.players.observe(this, Observer {
            players.addAll(it)
            initHomePlayers()
            initChallengers()
        })
        playerViewModel.getPlayers()
    }

    /**
     * initializes the drop downs for the home players
     */
    private fun initHomePlayers() {
        val adapter = ArrayAdapter<Player>(context!!, android.R.layout.simple_spinner_item, players)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player_home_1.adapter = adapter
        player_home_2.adapter = adapter

        player_home_1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setPlayer(1, parent.selectedItem as Player)
            }
        }

        player_home_2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setPlayer(2, parent.selectedItem as Player)
            }
        }
    }

    /**
     * initializes the drop downs for the challengers
     */
    private fun initChallengers() {
        val adapter = ArrayAdapter<Player>(context!!, android.R.layout.simple_spinner_item, players)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        challenger_1.adapter = adapter
        challenger_2.adapter = adapter

        challenger_1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setPlayer(3, parent.selectedItem as Player)
            }
        }

        challenger_2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                setPlayer(4, parent.selectedItem as Player)
            }
        }
    }

    /**
     * Set chosen players in the right variable
     */
    private fun setPlayer(i: Int, player: Player) {
        when (i) {
            1 -> playerHome1 = player
            2 -> playerHome2 = player
            3 -> challenger1 = player
            4 -> challenger2 = player
        }
    }

    private fun initMatchFab() {
        new_match_fab.setOnClickListener {
            matchViewModel.insertMatch(Match(
                123,
                1.0000,
                234,
                1.0000,
                345,
                1.0000,
                456,
                1.0000,
                6,
                4,
                6,
                1,
                6,
                4
            ))
        }
    }
}
