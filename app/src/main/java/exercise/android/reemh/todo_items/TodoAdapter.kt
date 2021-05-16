package exercise.android.reemh.todo_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val _todosHolder: TodoItemsHolder): RecyclerView.Adapter<TodoItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
                .inflate(R.layout.row_todo_item, parent, false)
        return TodoItemHolder(view)
    }

    override fun getItemCount(): Int {
        return _todosHolder.getCurrentItems().size
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        val todo = _todosHolder.getCurrentItems()[position]
        holder.description.text = todo.description
        holder.checkBox.isChecked = todo.status == TodoItem.Status.DONE
    }
}