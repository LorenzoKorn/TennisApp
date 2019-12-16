package lorenzokorn.tennis_app.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "player")
data class Player(
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "prefix") var prefix: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "rating_singles") var ratingSingles: Double,
    @ColumnInfo(name = "rating_doubles") var ratingDoubles: Double,
    @PrimaryKey @ColumnInfo(name = "id") var id: Long
) : Parcelable