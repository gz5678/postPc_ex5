package exercise.android.reemh.todo_items;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TodoItemsHolderImplTest {

    @Test
    public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_addingTodoItem_and_deletingIt_then_callingListShouldHaveThisItem(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        TodoItem item = holderUnderTest.getCurrentItems().get(0);
        holderUnderTest.deleteItem(item);

        // verify
        assertEquals(0, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_addingTodoItem_and_markingItDone_then_statusOfItemIsDone(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        TodoItem item = holderUnderTest.getCurrentItems().get(0);
        holderUnderTest.markItemDone(item);

        // verify
        assertEquals(TodoItem.Status.DONE, holderUnderTest.getCurrentItems().get(0).getStatus());
    }

    @Test
    public void when_addingTodoItem_and_markingItDoneThenAgainInProgress_then_statusOfItemIsInProgress(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        TodoItem item = holderUnderTest.getCurrentItems().get(0);
        holderUnderTest.markItemDone(item);
        holderUnderTest.markItemInProgress(item);

        // verify
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(0).getStatus());
    }

    @Test
    public void when_onlyAddingTodoItems_then_listShouldBeSortedByStatusAndTimeCreated(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("do jumping");
        holderUnderTest.addNewInProgressItem("do screaming");
        TodoItem item = holderUnderTest.getCurrentItems().get(1);
        holderUnderTest.markItemDone(item);

        // verify
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(0).getStatus());
        assertEquals("do screaming", holderUnderTest.getCurrentItems().get(0).getDescription());
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(1).getStatus());
        assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());
        assertEquals(TodoItem.Status.DONE, holderUnderTest.getCurrentItems().get(2).getStatus());
        assertEquals("do jumping", holderUnderTest.getCurrentItems().get(2).getDescription());
    }

    @Test
    public void when_onlyAddingNewItem_then_newItemShouldBeInProgress(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");

        // verify
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(0).getStatus());
    }

    @Test
    public void when_settingItems_then_listShouldHaveItemsAndSorted(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());
        ArrayList<TodoItem> newItems = new ArrayList<>();

        // test
        newItems.add(new TodoItem("buy tomatoes", System.currentTimeMillis(), TodoItem.Status.IN_PROGRESS));
        newItems.add(new TodoItem("buy chili", System.currentTimeMillis(), TodoItem.Status.DONE));
        newItems.add(new TodoItem("buy hummus", System.currentTimeMillis()+1, TodoItem.Status.IN_PROGRESS));
        holderUnderTest.setItems(newItems);

        // verify
        assertEquals(3, holderUnderTest.getCurrentItems().size());
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(0).getStatus());
        assertEquals("buy hummus", holderUnderTest.getCurrentItems().get(0).getDescription());
        assertEquals(TodoItem.Status.IN_PROGRESS, holderUnderTest.getCurrentItems().get(1).getStatus());
        assertEquals("buy tomatoes", holderUnderTest.getCurrentItems().get(1).getDescription());
        assertEquals(TodoItem.Status.DONE, holderUnderTest.getCurrentItems().get(2).getStatus());
        assertEquals("buy chili", holderUnderTest.getCurrentItems().get(2).getDescription());
    }

    @Test
    public void when_settingEmptyItemsList_then_listShouldBeEmpty(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());
        ArrayList<TodoItem> newItems = new ArrayList<>();

        // test
        holderUnderTest.setItems(newItems);

        // verify
        assertEquals(0, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_addingSameItemTwice_then_listShouldHaveBothInstances(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("do shopping");

        // verify
        assertEquals(2, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_deletingNonExistingItem_then_doNothing(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        TodoItem item = new TodoItem("buy hummus", System.currentTimeMillis(), TodoItem.Status.IN_PROGRESS);
        holderUnderTest.deleteItem(item);

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_deletingNull_then_doNothing(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.deleteItem(null);

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_markingNull_then_doNothing(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.markItemInProgress(null);
        holderUnderTest.markItemDone(null);

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    @Test
    public void when_addingSameItemTwice_and_anotherItem_then_listShouldAllInstances(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("do something else");

        // verify
        assertEquals(3, holderUnderTest.getCurrentItems().size());

    }
}