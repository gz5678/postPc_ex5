package exercise.android.reemh.todo_items

import java.io.Serializable

data class TodoItem(val description : String,
                    val timestampCreated : Long,
                    var status : Status) : Serializable, Comparable<TodoItem>
{
    enum class Status(val value: Int) {IN_PROGRESS(1), DONE(2)}

    override fun compareTo(other: TodoItem): Int {
        if (this.status == other.status) {
            return -(this.timestampCreated.compareTo(other.timestampCreated))
        }
        return (this.status.value.compareTo(other.status.value))
    }
}