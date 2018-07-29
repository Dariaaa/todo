package k_de.com.app.main

import android.content.Context
import k_de.com.app.db.AppDatabase
import k_de.com.app.db.Task

class MainPresenter(context: Context?):MainContract.Presenter{
    var view: MainContract.View? = null
    var db:AppDatabase = AppDatabase.getInstance(context)!!
    lateinit var tasks :List<Task>

    override fun delete(item: Task) {
        db.taskDao().delete(item)
    }

    override fun change(item: Task) {
        db.taskDao().update(item)
    }

    override fun attachView(mvpView: MainContract.View) {
        view = mvpView
    }

    override fun loadTasks(forseUpdate: Boolean): List<Task> {
        if (forseUpdate)
            tasks = db.taskDao().getAll()

        return tasks
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {

    }

}