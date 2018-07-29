package k_de.com.app.createtask

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import k_de.com.app.tasks.Task
import k_de.com.app.R
import k_de.com.app.main.MainActivity
import k_de.com.app.util.DateManager
import k_de.com.app.util.DateUtils
import kotlinx.android.synthetic.main.create_task.*

/**
 * A placeholder fragment containing a simple view.
 */
class CreateTaskFragment : Fragment(), CreateTaskContract.View {
    lateinit var taskDate:EditText
    lateinit var taskTime:EditText

    override fun saveTask() {
        if (!validate()) {
            Toast.makeText(context,"Please enter all parameters",Toast.LENGTH_SHORT).show()
            return
        }
        val date = DateUtils.toDate(taskDate.text.toString() +" "+ taskTime.text.toString())
        val t = Task(taskName.text.toString(),taskDescription.text.toString(),date)
        presenter.saveTask(t)
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
    private fun validate():Boolean{
        if (taskDate.text.trim().isEmpty() || taskTime.text.trim().isEmpty()
                ||taskName.text.trim().isEmpty()){
            return false;
        }
        try{
            DateUtils.toDate(taskDate.text.toString() + " "+ taskTime.text.toString())
        }catch (e:Exception){
            return false
        }
        return true
    }
    private lateinit var presenter: CreateTaskContract.Presenter

    override fun setPresenter(p: CreateTaskContract.Presenter) {
        this.presenter = p
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v =  inflater.inflate(R.layout.create_task, container, false)
        taskDate = v.findViewById(R.id.taskDate) as EditText
        taskTime = v.findViewById(R.id.taskTime) as EditText

        val dm = DateManager(context,taskDate,taskTime)
        taskDate.setOnClickListener({
            dm.setDate(it)
        })
        taskTime.setOnClickListener({
            dm.setTime(it)
        })

        return v
    }
}
