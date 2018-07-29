package k_de.com.app.createtask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import k_de.com.app.R
import k_de.com.app.tasks.Task

import kotlinx.android.synthetic.main.activity_main.*


class CreateTaskActivity : AppCompatActivity(){

    companion object {
        var REQUEST_ADD_TASK = 0
    }
    private lateinit var viewCreate:CreateTaskContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val view = CreateTaskFragment()
        viewCreate = view
        val presenter = CreateTaskPresenter()
        presenter.attachView(view)
        view.setPresenter(presenter)

        val task = intent.getParcelableExtra<Task>("task")

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container,view)
                .commit()
        if (task!=null) view.showTask(task)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_create, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_done ->
                viewCreate.saveTask()
        }
            return true
    }
}
