package k_de.com.app.createtask

import android.content.Context
import k_de.com.app.db.AppDatabase
import k_de.com.app.db.Task

class CreateTaskPresenter(context: Context?) :CreateTaskContract.Presenter{

    var db: AppDatabase = AppDatabase.getInstance(context)!!
    var view: CreateTaskContract.View? = null

    override fun saveTask(task: Task) {
        db.taskDao().insert(task)
    }
    override fun updateTask(task: Task){
        val tasks = db.getAll(false)
        val i = tasks.indexOf(task)
        task.id = tasks[i].id
        db.taskDao().update(task)
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