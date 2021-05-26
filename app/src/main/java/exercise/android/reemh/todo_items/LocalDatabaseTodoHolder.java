package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    private final MutableLiveData<List<TodoItem>> todosLiveDataMutable = new MutableLiveData<>();
    public final LiveData<List<TodoItem>> todosLiveDataPublic = todosLiveDataMutable;

    public LocalDatabaseTodoHolder(SharedPreferences sp) {
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
        todosLiveDataMutable.setValue(new ArrayList<>(todos));
    }


    @NotNull
    @Override
    public List<TodoItem> getCurrentItems() {
        return new ArrayList<>(todos); // Return shallow copy of todos list
    }

    @Override
    public void addNewInProgressItem(@NotNull String description) {
        // Create new item
        UUID id = UUID.randomUUID();
        TodoItem item = new TodoItem(description,
                System.currentTimeMillis(),
                TodoItem.Status.IN_PROGRESS,
                id);
        // Add item to todo list
        todos.add(item);

        // Add item to database
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(id.toString(), item.serialize());
        editor.apply();

        todosLiveDataMutable.setValue(new ArrayList<>(todos));
    }

    @Override
    public void markItemDone(@Nullable TodoItem item) {
        if (item != null && todos.contains(item) && item.getStatus() != TodoItem.Status.DONE) {
            TodoItem markedItem = new TodoItem(item.getDescription(),
                    item.getTimestampCreated(),
                    TodoItem.Status.DONE,
                    item.getId());
            todos.remove(item);
            todos.add(markedItem);

            SharedPreferences.Editor editor = sp.edit();
            editor.putString(markedItem.getId().toString(), markedItem.serialize());
            editor.apply();

            todosLiveDataMutable.setValue(new ArrayList<>(todos));
        }
    }

    @Override
    public void markItemInProgress(@Nullable TodoItem item) {
        if (item != null && todos.contains(item) && item.getStatus() != TodoItem.Status.IN_PROGRESS) {
            TodoItem markedItem = new TodoItem(item.getDescription(),
                    item.getTimestampCreated(),
                    TodoItem.Status.IN_PROGRESS,
                    item.getId());
            todos.remove(item);
            todos.add(markedItem);

            SharedPreferences.Editor editor = sp.edit();
            editor.putString(markedItem.getId().toString(), markedItem.serialize());
            editor.apply();

            todosLiveDataMutable.setValue(new ArrayList<>(todos));
        }
    }

    @Override
    public void deleteItem(@Nullable TodoItem item) {
        if (item != null && todos.remove(item)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(item.getId().toString());
            editor.apply();

            todosLiveDataMutable.setValue(new ArrayList<>(todos));
        }
    }

    @Override
    public void setItems(@NotNull List<TodoItem> items) {
    }
}
