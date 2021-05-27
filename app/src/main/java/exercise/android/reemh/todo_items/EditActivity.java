package exercise.android.reemh.todo_items;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class EditActivity extends AppCompatActivity {
    private LocalDatabaseTodoHolder database = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        // Get database
        if (database == null) database = TodoApp.getInstance().getDataBase();

        // Find todo according to id from intent
        UUID idFromTodo = UUID.fromString(getIntent().getSerializableExtra("todo_id").toString());
        TodoItem todoToEdit = null;
        for (TodoItem item: database.getCurrentItems()) {
            if (idFromTodo == item.getId()) {
                todoToEdit = item;
            }
        }

        // Get UI fields
        TextView timeCreated = findViewById(R.id.timeCreatedText);
        TextView lastModified = findViewById(R.id.lastModifiedText);
        CheckBox statusCheckbox = findViewById(R.id.statusCheckbox);
        EditText editText = findViewById(R.id.editTodoText);

        // Put data into UI fields
        timeCreated.setText(String.valueOf(todoToEdit.getTimestampCreated()));
        lastModified.setText(String.valueOf(todoToEdit.getLastModified()));
        statusCheckbox.setChecked(todoToEdit.getStatus() == TodoItem.Status.DONE);
    }
}
