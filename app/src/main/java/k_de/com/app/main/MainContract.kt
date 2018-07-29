package k_de.com.app.main

import k_de.com.app.mvp.MvpPresenter
import k_de.com.app.mvp.MvpView
import k_de.com.app.db.Task

interface MainContract{
    interface View: MvpView {
        fun showAllTasks()
        fun setPresenter(p:Presenter)
        fun showAddTask()
        fun showTask(t: Task)
        fun showDialog(titleResId:Int, messResId: Int,item: Task)
    }

    interface Presenter: MvpPresenter<View> {
        fun loadTasks(forseUpdate:Boolean):List<Task>
        fun change(item: Task)
        fun delete(item: Task)
    }
}