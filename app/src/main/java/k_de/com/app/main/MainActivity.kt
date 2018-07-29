package k_de.com.app.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import k_de.com.app.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val view = MainActivityFragment()

        val presenter = MainPresenter()
        presenter.attachView(view)

        view.setPresenter(presenter)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container,view)
                .commit()




    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
