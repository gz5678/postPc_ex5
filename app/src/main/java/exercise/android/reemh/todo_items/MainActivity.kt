package exercise.android.reemh.todo_items

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var holder: TodoItemsHolder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (holder == null) {
            holder = TodoItemsHolderImpl()
        }
        val addButton = findViewById<Button?>(R.id.buttonCreateTodoItem)
        val insertTaskTextField = findViewById<TextView?>(R.id.editTextInsertTask)
        addButton.setOnClickListener { _: View? ->
            val taskTest = insertTaskTextField.text.toString()
            // If the task is not empty, add it to the todo list
            if (taskTest.isEmpty()) {
                return@setOnClickListener
            }
            holder.addNewInProgressItem(taskTest)

            // Delete text in the text field so user can write new task
            insertTaskTextField.text = ""
        }
        // TODO: implement the specs as defined below
        //    (find all UI components, hook them up, connect everything you need)
    }
}