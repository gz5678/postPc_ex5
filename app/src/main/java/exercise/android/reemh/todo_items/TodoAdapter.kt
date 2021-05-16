package exercise.android.reemh.todo_items

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter: RecyclerView.Adapter<TodoItemHolder>() {

    private val _todos: MutableList<TodoItem> = ArrayList()

    fun setTodos(todos: List<TodoItem>) {
        _todos.clear()
        _todos.addAll(todos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return _todos.size
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        TODO("Not yet implemented")
    }
}