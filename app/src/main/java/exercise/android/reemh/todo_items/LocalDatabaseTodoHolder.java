package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class LocalDatabaseTodoHolder implements TodoItemsHolder {

    private final ArrayList<TodoItem> todos = new ArrayList<>();
    private final Context context;
    private final SharedPreferences sp;

    public LocalDatabaseTodoHolder(Context context, SharedPreferences sp) {
        this.context = context;
        this.sp = sp;

        // Load data from sp to Todos list
    }
}
