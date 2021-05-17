package exercise.android.reemh.todo_items

import java.util.*
import kotlin.collections.ArrayList

class TodoItemsHolderImpl : TodoItemsHolder {

    // We want the collection to remain sorted after add and remove.
    class SortedTodoItems: ArrayList<TodoItem>() {
        override fun add(element: TodoItem): Boolean {
            val index = this.binarySearch(element)
            // binarySearch returns invertedInsertion point so need to transform to insertion point
            super.add(-(index+1), element)
            return true
        }
    }

    private val todoItemArrayList = SortedTodoItems()

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
        todoItemArrayList.remove(item)
        item?.status = TodoItem.Status.DONE
        if (item != null) {
            todoItemArrayList.add(item)
        }
    }

    override fun markItemInProgress(item: TodoItem?) {
        todoItemArrayList.remove(item)
        item?.status = TodoItem.Status.IN_PROGRESS
        if (item != null) {
            todoItemArrayList.add(item)
        }
    }
    override fun deleteItem(item: TodoItem?) {todoItemArrayList.remove(item)}
}