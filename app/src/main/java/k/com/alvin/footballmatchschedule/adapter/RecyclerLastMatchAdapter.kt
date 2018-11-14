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

        fun bindItem(listMatchModel: MatchModel, listener: (MatchModel) -> Unit) {
            homeName.text = listMatchModel.homeTeam
            awayName.text = listMatchModel.awayTeam

            if (listMatchModel.homeScore.isNullOrBlank())
                homeScore.text = ""
            else
                homeScore.text = listMatchModel.homeScore.toString()

            if (listMatchModel.awayScore.isNullOrBlank())
                awayScore.text = ""
            else
                awayScore.text = listMatchModel.awayScore.toString()

            matchDate.text = dateFormat(listMatchModel.matchDate!!)

            itemView.setOnClickListener {
                listener(listMatchModel)
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun dateFormat(oldDate: String) : String {
            val dateFormat: SimpleDateFormat = SimpleDateFormat ("dd/mm/yy")
            val date: Date
            date = dateFormat.parse(oldDate)
            val newFormat: SimpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            val finalDate: String = newFormat.format(date)
            return finalDate
        }

    }

}