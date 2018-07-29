package k_de.com.app

import android.app.Application
import k_de.com.app.db.AppDatabase
import org.jetbrains.anko.doAsync

class RoomApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        doAsync {
            val database     = AppDatabase.getInstance(context = this@RoomApplication)

            }
        }

}