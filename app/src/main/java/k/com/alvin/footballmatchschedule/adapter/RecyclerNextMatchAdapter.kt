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
import k.com.alvin.footballmatchschedule.model.MatchModel
import k.com.alvin.footballmatchschedule.util.invisible
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alvin Tandiardi on 29/10/2018.
 */
class RecyclerNextMatchAdapter(private val listMatch: List<MatchModel>, private val listener: (MatchModel) -> Unit)
    : RecyclerView.Adapter<RecyclerNextMatchAdapter.NextMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = listMatch.size

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(listMatch[position], listener)

    }

    class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val homeName: TextView = view.findViewById(R.id.tv_left_team)
        private val awayName: TextView = view.findViewById(R.id.tv_right_team)
        private val homeScore: TextView = view.findViewById(R.id.tv_left_score)
        private val awayScore: TextView = view.findViewById(R.id.tv_right_score)
        private val matchDate: TextView = view.findViewById(R.id.tv_date)
        private val matchTime: TextView = view.findViewById(R.id.tv_time)
        private val addMatch: ImageView = view.findViewById(R.id.image_alarm)

        fun bindItem(listMatch: MatchModel, listener: (MatchModel) -> Unit) {

            val dateMatch = toGMTFormat(listMatch.matchDate, listMatch.matchTime)

            homeName.text = listMatch.homeTeam
            awayName.text = listMatch.awayTeam

            if (listMatch.homeScore.isNullOrBlank())
                homeScore.text = ""
            else
                homeScore.text = listMatch.homeScore

            if (listMatch.awayScore.isNullOrBlank())
                awayScore.text = ""
            else
                awayScore.text = listMatch.awayScore

            if (!listMatch.homeScore.isNullOrBlank() && !listMatch.awayScore.isNullOrBlank()) {
                addMatch.invisible()
            }

            matchDate.text = dateFormat(dateMatch)

            matchTime.text = timeFormat(dateMatch)

            itemView.setOnClickListener {
                listener(listMatch)
            }

            addMatch.setOnClickListener {

                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra(CalendarContract.Events.TITLE, "${listMatch.homeTeam} vs ${listMatch.awayTeam}")
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateMatch?.time)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dateMatch!!.time + 5_400_000)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, 1_800_000)
                itemView.context.startActivity(intent)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun dateFormat(matchDate: Date?): String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val finalDate: String = newFormat.format(matchDate)
            return finalDate
        }

        @SuppressLint("SimpleDateFormat")
        fun timeFormat(matchTime: Date?): String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val finalTime: String = newFormat.format(matchTime)
            return finalTime
        }

        @SuppressLint("SimpleDateFormat")
        fun toGMTFormat(date: Date?, time: String?): Date? {
            val dateStringFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yy")
            val dateString: String = dateStringFormat.format(date)
            val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$dateString $time"
            return formatter.parse(dateTime)
        }

    }

}