package exercise.android.reemh.todo_items

import java.util.*

// TODO: implement!
class TodoItemsHolderImpl : TodoItemsHolder {
    private val todoItemArrayList: = mutableListOf<TodoItem>()
    override fun getCurrentItems(): MutableList<TodoItem?>? {
        return todoItemArrayList
    }

    override fun addNewInProgressItem(description: String?) {}
    override fun markItemDone(item: TodoItem?) {}
    override fun markItemInProgress(item: TodoItem?) {}
    override fun deleteItem(item: TodoItem?) {}
}