package exercise.android.reemh.todo_items;

import android.app.Application;

public class TodoApp extends Application {

    private TodoItemsHolderImpl dataBase;

    public TodoItemsHolderImpl getDataBase() {
        return dataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
