package k_de.com.app.db

import android.arch.persistence.room.*


@Dao
interface TaskDao{

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getById(id: Long): Task

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM task")
    fun deleteAll()

}