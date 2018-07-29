package k_de.com.app.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import k_de.com.app.util.Converters

@Database(entities = arrayOf(Task::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    if (context != null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "tasks.db")
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    var tasks:MutableList<Task> = mutableListOf()

    fun getAll(forceUpdade:Boolean):MutableList<Task>{
        if (forceUpdade || tasks.isEmpty())
            tasks = taskDao().getAll() as MutableList<Task>
        return tasks
    }
}