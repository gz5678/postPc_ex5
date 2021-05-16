package exercise.android.reemh.todo_items

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemHolder(view: View): RecyclerView.ViewHolder(view) {
    val description: TextView = view.findViewById(R.id.description)
    val checkBox: CheckBox = view.findViewById(R.id.progressCheckBox)
}