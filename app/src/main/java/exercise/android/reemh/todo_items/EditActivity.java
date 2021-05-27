package exercise.android.reemh.todo_items;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
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
        final UUID idFromTodo = UUID.fromString(getIntent().getSerializableExtra("todo_id").toString());
        TodoItem todoToEdit = database.getTodoById(idFromTodo);

        // Get UI fields
        TextView timeCreated = findViewById(R.id.timeCreatedText);
        TextView lastModified = findViewById(R.id.lastModifiedText);
        CheckBox statusCheckbox = findViewById(R.id.statusCheckbox);
        EditText editText = findViewById(R.id.editTodoText);

        // Put data into UI fields
        timeCreated.setText(_millisToDateString(todoToEdit.getTimestampCreated(), false));
        lastModified.setText(_millisToDateString(todoToEdit.getLastModified(), true));
        statusCheckbox.setChecked(todoToEdit.getStatus() == TodoItem.Status.DONE);
        editText.setText(todoToEdit.getDescription());

        // Set listener for checkbox
        statusCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TodoItem finalTodoToEdit = database.getTodoById(idFromTodo);
                if (isChecked) {
                    database.markItemDone(finalTodoToEdit);
                }
                else {
                    database.markItemInProgress(finalTodoToEdit);
                }
                finalTodoToEdit = database.getTodoById(idFromTodo);
                // Change last modified field in ui
                lastModified.setText(_millisToDateString(finalTodoToEdit.getLastModified(), true));
            }
        });

        // Set listener for edit text
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String newDescription = editText.getText().toString();
                database.editItemDescription(database.getTodoById(idFromTodo), newDescription);
                lastModified.setText(_millisToDateString(database.getTodoById(idFromTodo).getLastModified(), true));
            }
        });
    }

    private String _millisToDateString(long timeInMillis, boolean isDifferenceFromNow) {
        Calendar calendar = Calendar.getInstance();
        if (isDifferenceFromNow) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            int dayOfCurrent = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.setTimeInMillis(timeInMillis);
            int dayOfModified = calendar.get(Calendar.DAY_OF_MONTH);
            if (dayOfCurrent != dayOfModified) {

                return new java.sql.Date(timeInMillis) + " at " + calendar.getTime().toString().split(" ")[3];
            }
            long diff = System.currentTimeMillis() - timeInMillis;
            long diffMinutes = diff / (60*1000);
            if (diffMinutes < 60) {
                return diffMinutes + " minutes ago";
            }
            return "Today at " + calendar.getTime().toString().split(" ")[3];
        }
        else {
            calendar.setTimeInMillis(timeInMillis);
            return "" + calendar.getTime();
        }
    }
}
