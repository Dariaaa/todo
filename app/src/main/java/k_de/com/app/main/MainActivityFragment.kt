package k_de.com.app.main

import android.support.v4.app.Fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import k_de.com.app.R
import k_de.com.app.tasks.TasksListAdapter
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.widget.ListView
import k_de.com.app.createtask.CreateTaskActivity

class MainActivityFragment : Fragment(), MainContract.View {

    override fun showAddTask() {
        val intent = Intent(context, CreateTaskActivity::class.java)
        startActivityForResult(intent, CreateTaskActivity.REQUEST_ADD_TASK)
    }

    private lateinit var presenter:MainContract.Presenter
    private lateinit var tasksListAdapter: TasksListAdapter
    private lateinit var tasksList:ListView

    override fun setPresenter(p: MainContract.Presenter) {
        presenter = p
    }

    override fun showAllTasks() {
        val tasks = presenter.loadTasks(true)
        tasksListAdapter = TasksListAdapter(this.activity!!, tasks)
        tasksListAdapter.setPresenter(presenter)

        tasksList.adapter = tasksListAdapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v =  inflater.inflate(R.layout.content_main, container, false)
        tasksList = v.findViewById(R.id.tasksView) as ListView
        showAllTasks()
        val btn = v.findViewById(R.id.fab) as FloatingActionButton
        btn.setOnClickListener({
            showAddTask()
        })
        return v
    }
}
