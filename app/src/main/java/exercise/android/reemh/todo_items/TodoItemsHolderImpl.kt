package exercise.android.reemh.todo_items

import java.util.*

class TodoItemsHolderImpl : TodoItemsHolder {
    private val todoItemArrayList = mutableListOf<TodoItem>()

    override fun getCurrentItems(): MutableList<TodoItem> {
        return todoItemArrayList
    }

    override fun addNewInProgressItem(description: String) {
        todoItemArrayList.add(TodoItem(
                description = description,
                timestampCreated = System.currentTimeMillis(),
                status = TodoItem.Status.IN_PROGRESS))
    }

    override fun markItemDone(item: TodoItem?) {
        item?.status = TodoItem.Status.DONE
    }

    override fun markItemInProgress(item: TodoItem?) {
        item?.status = TodoItem.Status.IN_PROGRESS
    }
    override fun deleteItem(item: TodoItem?) {todoItemArrayList.remove(item)}
}