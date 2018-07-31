package k_de.com.app.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import k_de.com.app.R
import k_de.com.app.db.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity(){

    private lateinit var presenter: MainPresenter

    private lateinit var view: MainActivityFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        view = MainActivityFragment()

        presenter = MainPresenter(this)
        presenter.attachView(view)
        presenter.setPrefs(getSharedPreferences( getString(R.string.preferences), AppCompatActivity.MODE_PRIVATE))
        view.setPresenter(presenter)

        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container,view)
                .commit()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all ->{
                doAsync {
                    presenter.deleteAll()
                    view.showAllTasks()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        AppDatabase.destroyInstance()
    }
}
