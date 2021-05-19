package exercise.android.reemh.todo_items

import android.graphics.Paint
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
        holder.description.paintFlags = if (todo.status != TodoItem.Status.DONE) 0 else Paint.STRIKE_THRU_TEXT_FLAG
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = todo.status == TodoItem.Status.DONE
        holder.checkBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if (!isChecked) {
                _todosHolder.markItemInProgress(_todosHolder.getCurrentItems()[ holder.adapterPosition ])
            } else {
                _todosHolder.markItemDone(_todosHolder.getCurrentItems()[ holder.adapterPosition ])
            }
            notifyDataSetChanged()
        }
        holder.itemView.isLongClickable = true
        holder.itemView.setOnLongClickListener{
            _todosHolder.getCurrentItems().removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            return@setOnLongClickListener true
        }
    }
}