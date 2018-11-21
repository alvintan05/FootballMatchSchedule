package k.com.alvin.footballmatchschedule.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import k.com.alvin.footballmatchschedule.R
import k.com.alvin.footballmatchschedule.model.PlayerModel
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Alvin Tandiardi on 20/11/2018.
 */
class RecyclerPlayerAdapter (private val listPlayerModel: List<PlayerModel>, private val listener: (PlayerModel) -> Unit)
    :RecyclerView.Adapter<RecyclerPlayerAdapter.PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int = listPlayerModel.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(listPlayerModel[position], listener)
    }

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val playerImage : ImageView = view.findViewById(R.id.image_player)
        private val playerName : TextView = view.findViewById(R.id.player_name)
        private val playerPosition: TextView = view.findViewById(R.id.player_position)

        fun bindItem(player: PlayerModel, listener: (PlayerModel) -> Unit) {
            Glide
                    .with(itemView.context)
                    .load(player.playerImage)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(playerImage)

            playerName.text = player.playerName
            playerPosition.text = player.playerPosition

            itemView.onClick { listener(player) }
        }

    }

}