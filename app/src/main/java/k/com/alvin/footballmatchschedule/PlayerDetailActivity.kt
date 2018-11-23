package k.com.alvin.footballmatchschedule

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import k.com.alvin.footballmatchschedule.model.PlayerModel
import k.com.alvin.footballmatchschedule.util.invisible
import k.com.alvin.footballmatchschedule.util.visible
import kotlinx.android.synthetic.main.activity_player_detail.*
import java.text.SimpleDateFormat
import java.util.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var player: PlayerModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(toolbar_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        player = intent.getParcelableExtra("player")

        supportActionBar?.title = player.playerName

        progress_bar.visible()

        showData()

    }

    private fun showData() {
        val weight: String = player.playerWeight!!.split(" ")[0]
        val height: String = player.playerHeight!!.split(" ")[0]

        Glide
                .with(this)
                .load(player.playerBanner)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(player_banner)

        player_weight.text = weight
        player_height.text = height

        if (player.playerDateSigned.isNullOrBlank()) player_sign_date.text = player.playerDateSigned
        else player_sign_date.text = dateFormat(player.playerDateSigned)

        player_position.text = player.playerPosition

        if (player.playerBirthDate.isNullOrBlank()) player_birth_date.text = player.playerBirthDate
        else player_birth_date.text = dateFormat(player.playerBirthDate)

        player_birth_location.text = player.playerBirthLocation
        player_nationality.text = player.playerNationatlity
        player_desc.text = player.playerDescription

        progress_bar.invisible()
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(oldDate: String?): String {
        val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date
        date = dateFormat.parse(oldDate)
        val newFormat: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
        val finalDate: String = newFormat.format(date)
        return finalDate
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
