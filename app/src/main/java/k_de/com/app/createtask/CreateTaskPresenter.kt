package k_de.com.app.createtask

import k_de.com.app.tasks.Task
import k_de.com.app.tasks.TasksRepository

class CreateTaskPresenter:CreateTaskContract.Presenter{

    private var repos: TasksRepository = TasksRepository.instance

    override fun saveTask(task: Task) {
        repos.saveTask(task)
    }

    var view: CreateTaskContract.View? = null

    override fun attachView(mvpView: CreateTaskContract.View) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    override fun destroy() {

    }


}