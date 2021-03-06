package lorenzokorn.tennis_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lorenzokorn.tennis_app.models.Match
import lorenzokorn.tennis_app.models.Player

@Database(entities = [Player::class, Match::class], version = 2, exportSchema = false)
abstract class TennisRoomDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun matchDao(): MatchDao

    companion object {
        private const val DATABASE_NAME = "Tennis_database"

        @Volatile
        private var INSTANCE: TennisRoomDatabase? = null

        fun getDatabase(context: Context): TennisRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(TennisRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TennisRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}