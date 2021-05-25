package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static exercise.android.reemh.todo_items.TodoItemKt.stringToTodo;

public class LocalDatabaseTodoHolder implements TodoItemsHolder {

    public static class SortedTodoItems extends ArrayList<TodoItem> {

        @Override
        public boolean add(TodoItem todoItem) {
            int index = Collections.binarySearch(this, todoItem);
            if (index >= 0) {
                super.add(index, todoItem);
            }
            else {
                super.add(-(index+1), todoItem);
            }
            return true;
        }
    }

    private final ArrayList<TodoItem> todos = new SortedTodoItems();
    private final SharedPreferences sp;

    public LocalDatabaseTodoHolder(Context context, SharedPreferences sp) {
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
