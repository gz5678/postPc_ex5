package exercise.android.reemh.todo_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter: RecyclerView.Adapter<TodoItemHolder>() {

    private val _todos: MutableList<TodoItem> = ArrayList()

    fun setTodos(todos: MutableList<TodoItem>) {
        _todos.clear()
        _todos.addAll(todos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
                .inflate(R.layout.row_todo_item, parent, false)
        return TodoItemHolder(view)
    }

    override fun getItemCount(): Int {
        return _todos.size
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        val todo = _todos[position]
        holder.description.text = todo.description
        holder.checkBox.isChecked = todo.status == TodoItem.Status.DONE
    }
}