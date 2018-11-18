package k.com.alvin.footballmatchschedule.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.model.MatchModel
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

        fun bindItem(listMatch: MatchModel, listener: (MatchModel) -> Unit) {
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

            matchDate.text = dateFormat(listMatch.matchDate!!)

            matchTime.text = timeFormat(listMatch.matchTime!!)

            itemView.setOnClickListener {
                listener(listMatch)
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

        @SuppressLint("SimpleDateFormat")
        fun timeFormat(oldTime: String): String {
            val timeformat: SimpleDateFormat = SimpleDateFormat("HH:mm:ssZ")
            val time: Date
            time = timeformat.parse(oldTime)
            val newFormat: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val finalTime: String = newFormat.format(time)
            return  finalTime
        }


    }

}