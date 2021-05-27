package exercise.android.reemh.todo_items

import java.io.Serializable
import java.lang.Exception
import java.util.*

data class TodoItem(val description : String,
                    val timestampCreated : Long,
                    var status : Status,
                    val id: UUID,
                    val lastModified: Long) : Serializable, Comparable<TodoItem>
{
    enum class Status(val value: Int) {IN_PROGRESS(1), DONE(2)}

    public fun serialize(): String {
        return "$description#$timestampCreated#$status#$id#$lastModified"
    }

    override fun compareTo(other: TodoItem): Int {
        if (this.status == other.status) {
            return -(this.timestampCreated.compareTo(other.timestampCreated))
        }
        return (this.status.value.compareTo(other.status.value))
    }
}

fun stringToTodo(string: String?): TodoItem? {
    if (string == null) return null
    try {
        val split: List<String> = string.split("#")
        val description = split[0]
        val timestampCreated = split[1].toLong()
        val status = TodoItem.Status.valueOf(split[2])
        val id = UUID.fromString(split[3])
        val lastModified = split[1].toLong()
        return TodoItem(description, timestampCreated, status, id, lastModified)
    } catch (e: Exception) {
        println("exception - input: $string, exception: $e")
        return null
    }
}