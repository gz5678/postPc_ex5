package exercise.android.reemh.todo_items

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    var holder: TodoItemsHolder = TodoItemsHolderImpl()
    private val adapter = TodoAdapter(holder)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<FloatingActionButton?>(R.id.buttonCreateTodoItem)
        val insertTaskTextField = findViewById<TextView?>(R.id.editTextInsertTask)

        val todoRecycler: RecyclerView = findViewById(R.id.recyclerTodoItemsList)
        todoRecycler.adapter = adapter
        todoRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        addButton.setOnClickListener { _: View? ->
            val taskTest = insertTaskTextField.text.toString()
            // If the task is not empty, add it to the todo list
            if (taskTest.isEmpty()) {
                return@setOnClickListener
            }
            holder.addNewInProgressItem(taskTest)
            adapter.notifyDataSetChanged()
            // Delete text in the text field so user can write new task
            insertTaskTextField.text = ""
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val insertTaskTextField = findViewById<TextView?>(R.id.editTextInsertTask)
        outState.putString("todo_in_text_field", insertTaskTextField.text.toString())
        outState.putSerializable("items", holder.getCurrentItems() as Serializable)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val insertTaskTextField = findViewById<TextView?>(R.id.editTextInsertTask)
        insertTaskTextField.text = savedInstanceState.getString("todo_in_text_field")
        val todos = savedInstanceState.getSerializable("items") as MutableList<TodoItem>
        holder.setItems(todos)
        adapter.notifyDataSetChanged()
    }
}

/*
SPECS:
- the screen starts out empty (no items shown, edit-text input should be empty)
- every time the user taps the "add TODO item" button:
    * if the edit-text is empty (no input), nothing happens
    * if there is input:
        - a new TodoItem (checkbox not checked) will be created and added to the items list
        - the new TodoItem will be shown as the first item in the Recycler view
        - the edit-text input will be erased
- the "TodoItems" list is shown in the screen
  * every operation that creates/edits/deletes a TodoItem should immediately be shown in the UI
  * the order of the TodoItems in the UI is:
    - all IN-PROGRESS items are shown first. items are sorted by creation time,
      where the last-created item is the first item in the list
    - all DONE items are shown afterwards, no particular sort is needed (but try to think about what's the best UX for the user)
  * every item shows a checkbox and a description. you can decide to show other data as well (creation time, etc)
  * DONE items should show the checkbox as checked, and the description with a strike-through text
  * IN-PROGRESS items should show the checkbox as not checked, and the description text normal
  * upon click on the checkbox, flip the TodoItem's state (if was DONE will be IN-PROGRESS, and vice versa)
  * add a functionality to remove a TodoItem. either by a button, long-click or any other UX as you want
- when a screen rotation happens (user flips the screen):
  * the UI should still show the same list of TodoItems
  * the edit-text should store the same user-input (don't erase input upon screen change)
Remarks:
- you should use the `holder` field of the activity
- you will need to create a class extending from RecyclerView.Adapter and use it in this activity
- notice that you have the "row_todo_item.xml" file and you can use it in the adapter
- you should add tests to make sure your activity works as expected. take a look at file `MainActivityTest.java`
(optional, for advanced students:
- save the TodoItems list to file, so the list will still be in the same state even when app is killed and re-launched
)
*/