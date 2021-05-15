package exercise.android.reemh.todo_items

import java.io.Serializable

data class TodoItem(val description : String,
                    val timestampCreated : Long,
                    val inProgress : Boolean) : Serializable { // TODO: edit this class as you want
}