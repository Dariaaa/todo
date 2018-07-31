package k_de.com.app.createtask

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import k_de.com.app.db.Task
import k_de.com.app.R
import k_de.com.app.main.MainActivity
import k_de.com.app.util.DateManager
import k_de.com.app.util.DateUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread

/**
 * A placeholder fragment containing a simple view.
 */
class CreateTaskFragment : Fragment(), CreateTaskContract.View {
    lateinit var taskDate: EditText
    lateinit var taskTime: EditText
    lateinit var taskName: EditText
    private lateinit var presenter: CreateTaskContract.Presenter
    lateinit var taskDescription: EditText
    private var task: Task? = null

    override fun saveTask() {
        if (!validate()) {
            Toast.makeText(context, getString(R.string.task_create_toast), Toast.LENGTH_SHORT).show()
            return
        }
        val date = DateUtils.toDate(taskDate.text.toString() + " " + taskTime.text.toString())
        if (task == null) {
            task = Task(taskName.text.toString(), taskDescription.text.toString())
            task!!.date = date
            doAsync {
                presenter.saveTask(task!!)
            }
        } else {
            doAsync {

                task!!.name = taskName.text.toString()
                task!!.content = taskDescription.text.toString()
                task!!.date = date

                presenter.updateTask(task!!)
                task = null
            }
        }

        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun validate(): Boolean {
        if (taskDate.text.trim().isEmpty() || taskTime.text.trim().isEmpty()
                || taskName.text.trim().isEmpty()) {
            return false;
        }
        try {
            DateUtils.toDate(taskDate.text.toString() + " " + taskTime.text.toString())
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override fun setPresenter(p: CreateTaskContract.Presenter) {
        this.presenter = p
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.create_task, container, false)
        taskDate = v.findViewById(R.id.taskDate) as EditText
        taskTime = v.findViewById(R.id.taskTime) as EditText
        taskName = v.findViewById(R.id.taskName) as EditText
        taskDescription = v.findViewById(R.id.taskDescription) as EditText



        val dm = DateManager(context, taskDate, taskTime)
        taskDate.setOnClickListener({
            dm.setDate(it)
        })
        taskTime.setOnClickListener({
            dm.setTime(it)
        })

        if (savedInstanceState!=null) {
            task = savedInstanceState.getParcelable("task")
            if (task!!.date!=null){
                val dateTime = DateUtils.toSimpleString(task!!.date!!).split(" ")
                taskDate.setText(dateTime[0])
                taskTime.setText(dateTime[1])
            }
            taskName.setText(task!!.name)
            taskDescription.setText(task!!.content)
        }
        return v
    }

    override fun onResume() {
        super.onResume()
        presenter = CreateTaskPresenter(context)
        presenter.attachView(this)
        presenter.setPrefs(activity!!.getSharedPreferences(getString(R.string.preferences), AppCompatActivity.MODE_PRIVATE))
        if (task == null) {
            doAsync {
                task = presenter.detachTask()
                if (task != null) {
                    uiThread {
                        val dateTime = DateUtils.toSimpleString(task!!.date!!).split(" ")
                        taskName.setText(task!!.name)
                        taskDescription.setText(task!!.content)
                        taskDate.setText(dateTime[0])
                        taskTime.setText(dateTime[1])
                    }
                }else{

                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val task = Task(taskName.text.toString(), taskDescription.text.toString())

        if (!taskDate.text.isEmpty() && !taskTime.text.isEmpty()){
            val date = DateUtils.toDate(taskDate.text.toString() + " " + taskTime.text.toString())
            task.date = date
        }
        outState.putParcelable("task",task)
    }
}
