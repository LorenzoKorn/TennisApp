package lorenzokorn.tennis_app.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_create_match.*

import lorenzokorn.tennis_app.R
import lorenzokorn.tennis_app.models.Player
import lorenzokorn.tennis_app.viewmodels.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateMatch : Fragment() {
    lateinit var viewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(PlayerViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDropdown()

        btn.setOnClickListener {
            Snackbar.make(btn, "${player_home_1.selectedItem}", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initDropdown() {
        val playerList = arrayListOf<Player>()

        viewModel.players.observe(this, Observer { players ->
                playerList.addAll(players)
        })

        Log.e("players list size", "${playerList.size}")

        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_item,
            playerList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player_home_1.adapter = adapter

        player_home_1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.e("nothing", "selected!")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // either one will work as well
                // val item = parent.getItemAtPosition(position) as String
//                val a = parent.selectedItem as Player
//                Log.e("item---", a.toString())
                // todo: on change

//                val item = adapter.getItem(position) as Player
                Log.e("item", "item!!.toString()")
//                Log.e("item", item.toString())
//                Log.e("item1", item.getFullName())
            }
        }
    }
}
