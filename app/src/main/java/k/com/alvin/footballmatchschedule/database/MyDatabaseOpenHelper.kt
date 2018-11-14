package k.com.alvin.footballmatchschedule.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by Alvin Tandiardi on 05/11/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT,
                Favorite.HOME_ID to TEXT,
                Favorite.AWAY_ID to TEXT,
                Favorite.HOME_TEAM_NAME to TEXT,
                Favorite.AWAY_TEAM_NAME to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.AWAY_SCORE to TEXT ,
                Favorite.HOME_SHOTS to TEXT,
                Favorite.AWAY_SHOTS to TEXT,
                Favorite.MATCH_DATE to TEXT,
                Favorite.AWAY_GOAL_DETAILS to TEXT,
                Favorite.AWAY_LINEUP_DEFENSE to TEXT,
                Favorite.AWAY_LINEUP_FORWARD to TEXT,
                Favorite.AWAY_LINEUP_GOAL_KEEPER to TEXT,
                Favorite.AWAY_LINEUP_MIDFIELD to TEXT,
                Favorite.AWAY_LINEUP_SUBTITUTES to TEXT,
                Favorite.HOME_GOAL_DETAILS to TEXT,
                Favorite.HOME_LINEUP_DEFENSE to TEXT,
                Favorite.HOME_LINEUP_FORWARD to TEXT,
                Favorite.HOME_LINEUP_GOAL_KEEPER to TEXT,
                Favorite.HOME_LINEUP_MIDFIELD to TEXT,
                Favorite.HOME_LINEUP_SUBTITUTES to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as ususal
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)