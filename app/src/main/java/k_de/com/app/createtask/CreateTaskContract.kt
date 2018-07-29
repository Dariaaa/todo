package k_de.com.app.createtask

import k_de.com.app.mvp.MvpPresenter
import k_de.com.app.mvp.MvpView
import k_de.com.app.tasks.Task

interface CreateTaskContract{
    interface View: MvpView {
        fun setPresenter(p:Presenter)
        fun saveTask()
        fun showTask(t:Task)
    }

    interface Presenter: MvpPresenter<View> {
        fun saveTask(task: Task)
    }
}