package k_de.com.app.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.util.*

@Parcelize
@Entity
class Task( var name:String, var content:String): Parcelable{
    @PrimaryKey
    var id:Long? = null
    var initDate: Date? = null
    var isDone:Boolean
    var date:Date? = null

    init{
        this.initDate = Date()
        this.isDone = false
    }

}