package lorenzokorn.tennis_app.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.match_list_item.view.*
import lorenzokorn.tennis_app.R

class MatchAdapter(private var matches: List<Match>) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match) {
            itemView.player_home_1.text = context.getString(R.string.home_1, match.playerHome1.toString(), match.playerHome1Rating)
            itemView.player_home_2.text = context.getString(R.string.home_2, match.playerHome2.toString(), match.playerHome1Rating)
            itemView.challenger_1.text = context.getString(R.string.out_1, match.challenger1.toString(), match.challenger1Rating)
            itemView.challenger_2.text = context.getString(R.string.out_2, match.challenger2.toString(), match.challenger2Rating)
            itemView.set_home_1.text = "${match.setHome1}"
            itemView.set_home_2.text = "${match.setHome2}"
            itemView.set_home_3.text = "${match.setHome3}"
            itemView.set_out_1.text = "${match.setChallenger1}"
            itemView.set_out_2.text = "${match.setChallenger2}"
            itemView.set_out_3.text = "${match.setChallenger3}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.match_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position])
    }

}