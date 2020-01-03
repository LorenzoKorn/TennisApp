package lorenzokorn.tennis_app.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.player_list_item.view.*
import lorenzokorn.tennis_app.R

class PlayerAdapter(private var players: List<Player>, private val onClick: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.setOnClickListener { onClick(player) }
            itemView.player_name.text = player.getFullName()
            itemView.player_single_rating.text = player.ratingSingles.toInt().toString()
            itemView.player_double_rating.text = player.ratingDoubles.toInt().toString()
            itemView.player_id.text = player.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.player_list_item, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(players[position])
    }
}