package exercise.android.reemh.todo_items

import junit.framework.TestCase
import org.junit.Test

class TodoItemsHolderImplTest : TestCase() {
    @Test
    fun test_when_addingTodoItem_then_callingListShouldHaveThisItem() {
        // setup
        val holderUnderTest = TodoItemsHolderImpl()
        assertEquals(0, holderUnderTest.getCurrentItems().size)

        // test
        holderUnderTest.addNewInProgressItem("do shopping")

        // verify
        assertEquals(1, holderUnderTest.getCurrentItems().size)
    } // TODO: add at least 10 more tests to verify correct behavior of your implementation of `TodoItemsHolderImpl` class
}