package lorenzokorn.tennis_app.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "match")
data class Match(
    @ColumnInfo(name = "player_home_1") var playerHome1: Long,
    @ColumnInfo(name = "player_home_1_rating") var playerHome1Rating: Double,
    @ColumnInfo(name = "player_home_2") var playerHome2: Long,
    @ColumnInfo(name = "player_home_2_rating") var playerHome2Rating: Double,
    @ColumnInfo(name = "challenger_1") var challenger1: Long,
    @ColumnInfo(name = "challenger_1_rating") var challenger1Rating: Double,
    @ColumnInfo(name = "challenger_2") var challenger2: Long,
    @ColumnInfo(name = "challenger_2_rating") var challenger2Rating: Double,
    @ColumnInfo(name = "set_home_1") var setHome1: Int,
    @ColumnInfo(name = "set_home_2") var setHome2: Int,
    @ColumnInfo(name = "set_home_3") var setHome3: Int,
    @ColumnInfo(name = "set_challenger_1") var setChallenger1: Int,
    @ColumnInfo(name = "set_challenger_2") var setChallenger2: Int,
    @ColumnInfo(name = "set_challenger_3") var setChallenger3: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null
) : Parcelable