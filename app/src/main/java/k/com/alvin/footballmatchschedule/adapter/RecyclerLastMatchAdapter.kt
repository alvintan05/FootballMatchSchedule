package k.com.alvin.footballmatchschedule.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
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
class RecyclerLastMatchAdapter(private val listMatchModels: List<MatchModel>, private val listener: (MatchModel) -> Unit)
    : RecyclerView.Adapter<RecyclerLastMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        return LastMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = listMatchModels.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(listMatchModels[position], listener)
    }

    class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val homeName: TextView = view.findViewById(R.id.tv_left_team)
        private val awayName: TextView = view.findViewById(R.id.tv_right_team)
        private val homeScore: TextView = view.findViewById(R.id.tv_left_score)
        private val awayScore: TextView = view.findViewById(R.id.tv_right_score)
        private val matchDate: TextView = view.findViewById(R.id.tv_date)
        private val matchTime: TextView = view.findViewById(R.id.tv_time)
        private val reminder: ImageView = view.findViewById(R.id.image_alarm)

        fun bindItem(match: MatchModel, listener: (MatchModel) -> Unit) {

            val dateMatch = toGMTFormat(match.matchDate!!, match.matchTime!!)

            reminder.invisible()
            homeName.text = match.homeTeam
            awayName.text = match.awayTeam

            if (match.homeScore.isNullOrBlank())
                homeScore.text = ""
            else
                homeScore.text = match.homeScore.toString()

            if (match.awayScore.isNullOrBlank())
                awayScore.text = ""
            else
                awayScore.text = match.awayScore.toString()

            matchDate.text = dateFormat(dateMatch!!)

            matchTime.text = timeFormat(dateMatch)


            itemView.setOnClickListener {
                listener(match)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun dateFormat(matchDate: Date) : String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val finalDate: String = newFormat.format(matchDate)
            return finalDate
        }

        @SuppressLint("SimpleDateFormat")
        fun timeFormat(matchTime: Date): String {
            val newFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val finalTime: String = newFormat.format(matchTime)
            return  finalTime
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