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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_match.*
import kotlinx.android.synthetic.main.fragment_user_home.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Match
import lorenzokorn.tennis_app.models.MatchAdapter
import lorenzokorn.tennis_app.models.Player
import lorenzokorn.tennis_app.viewmodels.MatchViewModel

/**
 * A simple [Fragment] subclass.
 */
class UserHome : Fragment() {

    private lateinit var matchViewModel: MatchViewModel
    private var matches = arrayListOf<Match>()
    private var matchAdapter = MatchAdapter(matches)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        matchViewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(MatchViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRecyclerView()
        initFab()
    }

    private fun initView() {
        // display user data
        val player: Player? = arguments?.getParcelable("player")
        player_initials.text = player!!.getInitials()
        player_name.text = player.getFullName()
        player_single_rating.text = getString(R.string.single_rating, player.ratingSingles)
        player_double_rating.text = getString(R.string.double_rating, player.ratingDoubles)

        // get matches for recyclerview
        matchViewModel.matches.observe(this, Observer {
            matches.clear()
            matches.addAll(it)
            matchAdapter.notifyDataSetChanged()
        })
        matchViewModel.getMatches(player.id)
    }

    private fun initRecyclerView() {
        player_matches.layoutManager =
            LinearLayoutManager(this@UserHome.context, RecyclerView.VERTICAL, false)
        player_matches.adapter = matchAdapter
        player_matches.addItemDecoration(DividerItemDecoration(this@UserHome.context, 0))
        createItemTouchHelper().attachToRecyclerView(player_matches)
    }

    private fun initFab() {
        player_add_match.setOnClickListener {
            findNavController().navigate(R.id.userHome_to_createMatch)
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val match = matches[position]
                matchViewModel.deleteMatch(match)
                Snackbar.make(player_matches, "Successfully deleted!", Snackbar.LENGTH_SHORT).show()
                val player: Player? = arguments?.getParcelable("player")
                matchViewModel.getMatches(player!!.id)
            }
        }
        return ItemTouchHelper(callback)
    }
}
