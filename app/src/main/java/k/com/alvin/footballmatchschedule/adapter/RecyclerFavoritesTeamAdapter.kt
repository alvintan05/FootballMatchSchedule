package k.com.alvin.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.database.Team
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Alvin Tandiardi on 21/11/2018.
 */
class RecyclerFavoritesTeamAdapter(private val listTeam: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<RecyclerFavoritesTeamAdapter.FavoritesTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTeamViewHolder {
        return FavoritesTeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: FavoritesTeamViewHolder, position: Int) {
        holder.bindItem(listTeam[position], listener)
    }

    class FavoritesTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val teamImage: ImageView = view.findViewById(R.id.team_logo)
        private val teamName: TextView = view.findViewById(R.id.team_name)

        fun bindItem(listTeam: Team, listener: (Team) -> Unit) {
            Glide.with(itemView.context).load(listTeam.teamBadge).override(120, 120).into(teamImage)
            Log.d("link badge", listTeam.teamBadge)
            teamName.text = listTeam.teamName
            itemView.onClick { listener(listTeam) }
        }

    }

}