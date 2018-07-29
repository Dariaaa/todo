package k_de.com.app.db

interface TaskRepository{
    fun deleteTask(t: Task)

    fun saveTask(t: Task)

    fun loadTasks(forceUpdate:Boolean):List<Task>

    fun change(item: Task)

}