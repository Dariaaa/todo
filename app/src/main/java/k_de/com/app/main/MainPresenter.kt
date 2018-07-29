package k_de.com.app.main

import k_de.com.app.tasks.Task
import k_de.com.app.tasks.TasksRepository

class MainPresenter:MainContract.Presenter{
    var view: MainContract.View? = null

    var repos:TasksRepository = TasksRepository.instance

    override fun delete(item: Task) {
        TasksRepository.instance.deleteTask(item)
    }

    override fun change(item: Task) {
        repos.change(item)
    }

    override fun attachView(mvpView: MainContract.View) {
        view = mvpView
    }

    override fun loadTasks(forseUpdate: Boolean): List<Task> {
        return repos.loadTasks(forseUpdate)
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {

    }

}