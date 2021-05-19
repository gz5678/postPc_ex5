package exercise.android.reemh.todo_items;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;

public class TodoItemsHolderImplTest extends TestCase {
    public void test_when_addingTodoItem_then_callingListShouldHaveThisItem(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size());
    }

    public void test_when_addingTodoItem_and_deletingIt_then_callingListShouldHaveThisItem(){
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

    public void test_when_addingTodoItem_and_markingItDone_then_statusOfItemIsDone(){
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

    public void test_when_addingTodoItem_and_markingItDoneThenAgainInProgress_then_statusOfItemIsInProgress(){
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

    public void test_when_onlyAddingTodoItems_then_listShouldBeSortedByStatusAndTimeCreated(){
        // setup
        TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
        assertEquals(0, holderUnderTest.getCurrentItems().size());

        // test
        holderUnderTest.addNewInProgressItem("do shopping");
        holderUnderTest.addNewInProgressItem("do jumping");
        holderUnderTest.addNewInProgressItem("do screaming");
        TodoItem item = holderUnderTest.getCurrentItems().get(1);
        holderUnderTest.markItemDone(item);
        List<TodoItem> beforeSort = holderUnderTest.getCurrentItems();
        holderUnderTest.sortList();

        // verify
        for (int i = 0; i < holderUnderTest.getCurrentItems().size(); i++) {
            assertEquals(beforeSort.get(i), holderUnderTest.getCurrentItems().get(i));
        }
    }

    // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class
}