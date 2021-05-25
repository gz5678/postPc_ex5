package exercise.android.reemh.todo_items;

import android.app.Application;

public class TodoApp extends Application {

    private TodoItemsHolder dataBase;

    public TodoItemsHolder getDataBase() {
        return dataBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private static TodoApp instance = null;

    public static TodoApp getInstance() {
        return instance;
    }
}
