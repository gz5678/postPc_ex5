package exercise.android.reemh.todo_items

import java.io.Serializable

data class TodoItem(val description : String,
                    val timestampCreated : Long,
                    var status : Status) : Serializable
{
    enum class Status {IN_PROGRESS, DONE}
}