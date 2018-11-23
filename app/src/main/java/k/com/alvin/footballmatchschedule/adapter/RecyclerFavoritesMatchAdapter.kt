package k.com.alvin.footballmatchschedule.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.database.Favorite
import k.com.alvin.footballmatchschedule.util.invisible
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alvin Tandiardi on 08/11/2018.
 */
class RecyclerFavoritesMatchAdapter(private val listFavorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<RecyclerFavoritesMatchAdapter.FavoriteViewHolder>() {

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
        private val matchTime: TextView = view.findViewById(R.id.tv_time)
        private val reminder: ImageView = view.findViewById(R.id.image_alarm)

        fun bindItem(listFavorite: Favorite, listener: (Favorite) -> Unit) {

            val dateMatch = toGMTFormat(listFavorite.matchDate!!, listFavorite.matchTime!!)

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

            if (!listFavorite.homeScore.isNullOrBlank() && !listFavorite.awayScore.isNullOrBlank()) {
                reminder.invisible()
            }

            matchDate.text = dateFormat(dateMatch!!)

            matchTime.text = timeFormat(dateMatch)

            itemView.setOnClickListener {
                listener(listFavorite)
            }

            reminder.setOnClickListener {

                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra(CalendarContract.Events.TITLE, "${listFavorite.homeTeamName} vs ${listFavorite.awayTeamName}")
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateMatch.time)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateMatch.time + 5_400_000)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, 1_800_000)
                itemView.context.startActivity(intent)
            }

        }

        @SuppressLint("SimpleDateFormat")
        fun dateFormat(matchDate : Date): String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val finalDate: String = newFormat.format(matchDate)
            return finalDate
        }

        @SuppressLint("SimpleDateFormat")
        fun timeFormat(matchTime : Date): String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val finalTime: String = newFormat.format(matchTime)
            return finalTime
        }

        @SuppressLint("SimpleDateFormat")
        fun toGMTFormat(date: String, time: String): Date? {
            val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$date $time"
            return formatter.parse(dateTime)
        }

    }


}