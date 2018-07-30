package k_de.com.app.main


import android.app.AlertDialog

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import k_de.com.app.R
import k_de.com.app.createtask.CreateTaskActivity
import k_de.com.app.db.Task
import k_de.com.app.tasks.TasksListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivityFragment : Fragment(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var tasksListAdapter: TasksListAdapter
    private lateinit var tasksList: ListView

    override fun showTask(t: Task) {
        val intent = Intent(context, CreateTaskActivity::class.java)
        intent.putExtra("task", t)
        startActivityForResult(intent, CreateTaskActivity.REQUEST_ADD_TASK)

        val sharedPref = activity!!.getSharedPreferences(
                getString(R.string.preference_task_key), AppCompatActivity.MODE_PRIVATE)
        with(sharedPref.edit()){
            putLong(getString(R.string.preference_task_key), t.id!!)
            commit()
        }
    }

    override fun showDialog(titleResId: Int, messResId: Int, item: Task) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(titleResId)
        builder.setMessage(messResId)
        builder.setPositiveButton(R.string.ok) { dialog, which ->
            doAsync {
                presenter.delete(item)
            }
            dialog.dismiss()
            showAllTasks()
        }
        builder.setNegativeButton(R.string.cancel) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun showAddTask() {
        val intent = Intent(context, CreateTaskActivity::class.java)
        startActivityForResult(intent, CreateTaskActivity.REQUEST_ADD_TASK)
    }

    override fun setPresenter(p: MainContract.Presenter) {
        presenter = p
    }

    override fun showAllTasks() {
        doAsync {
            val tasks = presenter.loadTasks(true)
            uiThread {
                tasksListAdapter.addAll(tasks)
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.content_main, container, false)

        presenter = MainPresenter(context)
        presenter.attachView(this)
        tasksList = v.findViewById(R.id.tasksView) as ListView

        val btn = v.findViewById(R.id.fab) as FloatingActionButton
        btn.setOnClickListener({
            showAddTask()
        })
        tasksListAdapter = TasksListAdapter(this.activity!!, mutableListOf())
        tasksListAdapter.setPresenter(presenter)
        tasksListAdapter.setView(this)
        tasksList.adapter = tasksListAdapter
        return v
    }

    override fun onResume() {
        super.onResume()
        presenter = MainPresenter(context)
        presenter.attachView(this)

        showAllTasks()
    }
}
