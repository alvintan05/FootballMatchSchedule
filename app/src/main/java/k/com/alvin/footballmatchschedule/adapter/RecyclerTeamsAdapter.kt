package k.com.alvin.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.model.TeamInfoModel
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Alvin Tandiardi on 17/11/2018.
 */
class RecyclerTeamsAdapter(private val listTeam: List<TeamInfoModel>, private val listener: (TeamInfoModel) -> Unit)
    :RecyclerView.Adapter<RecyclerTeamsAdapter.TeamsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(listTeam[position], listener)
    }

    class TeamsViewHolder (view: View): RecyclerView.ViewHolder(view) {

        private val teamImage: ImageView = view.findViewById(R.id.team_logo)
        private val teamName: TextView = view.findViewById(R.id.team_name)

        fun bindItem(listTeam: TeamInfoModel, listener: (TeamInfoModel) -> Unit) {
            Glide.with(itemView.context).load(listTeam.teamBadge).override(120, 120).into(teamImage)
            teamName.text = listTeam.teamName
            itemView.onClick { listener(listTeam) }
        }

    }

}