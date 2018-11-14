package k.com.alvin.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.database.Favorite
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alvin Tandiardi on 08/11/2018.
 */
class RecyclerFavoritesAdapter(private val listFavorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<RecyclerFavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = listFavorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(listFavorite[position], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val homeName: TextView = view.findViewById(R.id.tv_left_team)
        private val awayName: TextView = view.findViewById(R.id.tv_right_team)
        private val homeScore: TextView = view.findViewById(R.id.tv_left_score)
        private val awayScore: TextView = view.findViewById(R.id.tv_right_score)
        private val matchDate: TextView = view.findViewById(R.id.tv_date)

        fun bindItem(listFavorite: Favorite, listener: (Favorite) -> Unit) {
            homeName.text = listFavorite.homeTeamName
            awayName.text = listFavorite.awayTeamName

            if (listFavorite.homeScore.isNullOrBlank())
                homeScore.text = ""
            else
                homeScore.text = listFavorite.homeScore.toString()

            if (listFavorite.awayScore.isNullOrBlank())
                awayScore.text = ""
            else
                awayScore.text = listFavorite.awayScore.toString()

            matchDate.text = dateFormat(listFavorite.matchDate!!)

            itemView.setOnClickListener {
                listener(listFavorite)
            }
        }

        fun dateFormat(oldDate: String): String {
            val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/mm/yy")
            val date: Date
            date = dateFormat.parse(oldDate)
            val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val finalDate: String = newFormat.format(date)
            return finalDate
        }

    }


}