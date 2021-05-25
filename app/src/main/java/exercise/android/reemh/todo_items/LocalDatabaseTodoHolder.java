package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static exercise.android.reemh.todo_items.TodoItemKt.stringToTodo;

public class LocalDatabaseTodoHolder implements TodoItemsHolder {

    private final ArrayList<TodoItem> todos = new ArrayList<>();
    private final Context context;
    private final SharedPreferences sp;

    public LocalDatabaseTodoHolder(Context context, SharedPreferences sp) {
        this.context = context;
        this.sp = sp;

        // Load data from sp to Todos list
        initializeFromSp();
    }

    private void initializeFromSp() {
        Set<String> keys = sp.getAll().keySet();
        for (String key: keys) {
            String todoSavedAsString = sp.getString(key, null);
            TodoItem item = stringToTodo(todoSavedAsString);
            if (item != null) {
                todos.add(item);
            }
        }
    }


    @NotNull
    @Override
    public List<TodoItem> getCurrentItems() {
        return new ArrayList<>(todos); // Return shallow copy of todos list
    }

    @Override
    public void addNewInProgressItem(@NotNull String description) {

    }

    @Override
    public void markItemDone(@Nullable TodoItem item) {

    }

    @Override
    public void markItemInProgress(@Nullable TodoItem item) {

    }

    @Override
    public void deleteItem(@Nullable TodoItem item) {

    }

    @Override
    public void setItems(@NotNull List<TodoItem> items) {

    }
}
