package k_de.com.app.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import k_de.com.app.R
import k_de.com.app.main.MainContract
import k_de.com.app.util.DateUtils

class TasksListAdapter(context: Context,
                       private val dataSource: List<Task>) : BaseAdapter(){

    private lateinit var presenter: MainContract.Presenter

    fun setPresenter(presenter:MainContract.Presenter){
        this.presenter = presenter
    }

    override fun getView(p0: Int, rowView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        val item = dataSource[p0]

        val taskName = rowView.findViewById(R.id.taskName) as TextView
        val done = rowView.findViewById(R.id.done) as CheckBox
        val taskDate = rowView.findViewById(R.id.taskDate) as TextView

        done.setOnCheckedChangeListener { view, isChecked ->
            item.isDone = isChecked
            presenter.change(item)
            taskName.setTextAppearance(if (isChecked) R.style.doneText else R.style.normalText)
            taskDate.setTextAppearance(if (isChecked) R.style.doneTextItalic else R.style.normalTextItelic)
        }
        taskName.setText(item.name)
        taskDate.setText(DateUtils.toSimpleString(item.date))
        done.isChecked = item.isDone

        return rowView
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

}