package exercise.android.reemh.todo_items

import java.util.*
import kotlin.collections.ArrayList

class TodoItemsHolderImpl : TodoItemsHolder {

    // We want the collection to remain sorted after add and remove.
    class SortedTodoItems: ArrayList<TodoItem>() {
        override fun add(element: TodoItem): Boolean {
            val index = this.binarySearch(element)
            // in case we find an element (which happens when both items are identical),
            // just push it at that spot (since it doesn't matter and we don't want to mess with plus/minus)
            if (index >= 0) super.add(index, element)

            // binarySearch returns invertedInsertion point when element not found so need to transform to insertion point
            else super.add(-(index+1), element)
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
        item?.status = TodoItem.Status.DONE
        if (item != null) todoItemArrayList.sort()
    }

    override fun markItemInProgress(item: TodoItem?) {
        item?.status = TodoItem.Status.IN_PROGRESS
        if (item != null) todoItemArrayList.sort()
    }

    override fun deleteItem(item: TodoItem?) {todoItemArrayList.remove(item)}

    override fun setItems(items: MutableList<TodoItem>) {
        todoItemArrayList.clear()
        todoItemArrayList.addAll(items)
        todoItemArrayList.sort()
    }
}