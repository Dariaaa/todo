package k_de.com.app.createtask

import android.content.Context
import android.content.SharedPreferences
import k_de.com.app.R
import k_de.com.app.db.AppDatabase
import k_de.com.app.db.Task

class CreateTaskPresenter(var context: Context?) : CreateTaskContract.Presenter {
    var db: AppDatabase = AppDatabase.getInstance(context)!!

    var view: CreateTaskContract.View? = null

    var pref: SharedPreferences? = null

    override fun persistTask(task: Task) {
        with(pref!!.edit()){
            putLong(context!!.getString(k_de.com.app.R.string.preference_task_key), task.id!!)
            commit()
        }
    }

    override fun detachTask(): Task? {
        val taskId = pref!!.getLong(context!!.getString(R.string.preference_task_key), 0)
                ?: return null
        val task = getById(taskId)
        with(pref!!.edit()) {
            clear()
            commit()
        }
        return task
    }

    override fun setPrefs(pref: SharedPreferences) {
        this.pref = pref
    }

    override fun saveTask(task: Task) {
        db.taskDao().insert(task)
    }

    override fun updateTask(task: Task) {
        db.taskDao().update(task)
    }

    override fun getById(id: Long): Task {
        return db.taskDao().getById(id)
    }

    override fun attachView(mvpView: CreateTaskContract.View) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {
    }


}