package k_de.com.app.tasks

import java.util.*

class Task(subject:String,content:String,date:Date){
    var id:Long
        private set
    var date: Date
        private set
    var content: String
        private set
    var name: String
        private set
    var initDate: Date
        private set
    var isDone:Boolean
        set

    init{
        this.id = Random().nextLong()
        this.date = date
        this.content = content
        this.name = subject
        this.initDate = Date()
        this.isDone = false
    }


    override fun equals(other: Any?): Boolean {
        if (other is Task) {
            return other.id == this.id
        }
        return false
    }


}