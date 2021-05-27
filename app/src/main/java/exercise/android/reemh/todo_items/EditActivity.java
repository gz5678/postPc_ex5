package exercise.android.reemh.todo_items;

import android.os.Bundle;

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

    }
}
