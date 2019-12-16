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
import kotlinx.android.synthetic.main.fragment_create_player.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Player
import lorenzokorn.tennis_app.viewmodels.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreatePlayer : Fragment() {

    private lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFab()
        initViewModel()
    }

    private fun initFab() {
        new_player_fab.setOnClickListener {
            viewModel.addPlayer(
                Player(
                    new_player_first_name.text.toString(),
                    new_player_prefix.text.toString(),
                    new_player_last_name.text.toString(),
                    new_player_single_rating.text.toString().toDouble(),
                    new_player_double_rating.text.toString().toDouble(),
                    new_player_id.text.toString().toLong()
                )
            )
            findNavController().navigateUp()
        }
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(PlayerViewModel::class.java)

        viewModel.error.observe(this, Observer {
            Log.i("INFOOOOOO", "ERROR OCCURRED")
        })

        viewModel.success.observe(this, Observer {
            Log.i("INFOOOOOO", "SUCCESSFUL")
        })
    }
}
