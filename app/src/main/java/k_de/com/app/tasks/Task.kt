package k_de.com.app.tasks

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Task(var id:Long,var name:String, var content:String, var date:Date): Parcelable{

    var initDate: Date
        private set
    var isDone:Boolean
        set

    init{
        this.initDate = Date()
        this.isDone = false
    }


    override fun equals(other: Any?): Boolean {
        if (other is Task) {
            return other.id == this.id
        }
        return false
    }

}