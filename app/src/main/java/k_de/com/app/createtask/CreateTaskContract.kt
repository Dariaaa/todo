package k_de.com.app.createtask

import k_de.com.app.mvp.MvpPresenter
import k_de.com.app.mvp.MvpView
import k_de.com.app.db.Task

interface CreateTaskContract{
    interface View: MvpView {
        fun setPresenter(p:Presenter)
        fun saveTask()
    }

    interface Presenter: MvpPresenter<View> {
        fun saveTask(task: Task)
        fun updateTask(task: Task)
        fun getById(id: Long): Task
    }
}