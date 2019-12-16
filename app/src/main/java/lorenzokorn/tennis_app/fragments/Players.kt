package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import android.util.Log
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
import lorenzokorn.tennis_app.viewmodels.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
class Players : Fragment() {

    private lateinit var viewModel: PlayerViewModel
    private var players = arrayListOf<Player>()
    private var playerAdapter = PlayerAdapter(players)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // shows the actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("hallo", "hallo1")
        initView()
        initRecyclerView()
        initFab()
    }

    private fun initView() {
        Log.i("hallo", "hallo2")

        viewModel.players.observe(this, Observer { players ->
            this@Players.players.clear()
            this@Players.players.addAll(players)
            playerAdapter.notifyDataSetChanged()
        })
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
}
