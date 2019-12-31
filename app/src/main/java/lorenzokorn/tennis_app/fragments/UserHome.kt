package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_user_home.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Player

/**
 * A simple [Fragment] subclass.
 */
class UserHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFab()
    }

    private fun initView() {
        val player: Player? = arguments?.getParcelable("player")
        player_initials.text = player!!.getInitials()
        player_name.text = player.getFullName()
        player_single_rating.text = getString(R.string.single_rating, player.ratingSingles)
        player_double_rating.text = getString(R.string.double_rating, player.ratingDoubles)
    }

    private fun initFab() {
        player_add_match.setOnClickListener {
            findNavController().navigate(R.id.userHome_to_createMatch)
        }
    }
}
