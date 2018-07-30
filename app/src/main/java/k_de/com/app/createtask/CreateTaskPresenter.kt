package k_de.com.app.createtask

import android.content.Context
import k_de.com.app.db.AppDatabase
import k_de.com.app.db.Task
import k_de.com.app.main.MainContract

class CreateTaskPresenter(context: Context?) :CreateTaskContract.Presenter{

    var db: AppDatabase = AppDatabase.getInstance(context)!!
    var view: CreateTaskContract.View? = null

    override fun saveTask(task: Task) {
        db.taskDao().insert(task)
    }
    override fun updateTask(task: Task){
        db.taskDao().update(task)
    }

    override fun getById(id:Long):Task{
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