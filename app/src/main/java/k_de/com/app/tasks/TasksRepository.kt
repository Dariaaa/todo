package k_de.com.app.tasks

class TasksRepository private constructor(){
    private val tasks:MutableList<Task>

    init{
        tasks = mutableListOf()
    }

    companion object {
        var instance: TasksRepository = TasksRepository()
        @Synchronized
        fun getInstence(): TasksRepository{
            return instance
        }
    }

    fun saveTask(t: Task){
        tasks.add(t)
    }

    fun loadTasks(forceUpdate:Boolean):List<Task>{
        return tasks
    }

    fun change(item: Task) {
        val i = tasks.indexOf(item)
        tasks.set(i,item)
    }

}