package exercise.android.reemh.todo_items

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class MainActivityTest : TestCase() {
    private var activityController: ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity::class.java)
    private var mockHolder: TodoItemsHolder = Mockito.mock(TodoItemsHolder::class.java)
    @Before
    fun setup() {
        mockHolder = Mockito.mock(TodoItemsHolder::class.java)
        // when asking the `mockHolder` to get the current items, return an empty list
        Mockito.`when`(mockHolder.getCurrentItems())
                .thenReturn(ArrayList())
        activityController = Robolectric.buildActivity(MainActivity::class.java)

        // let the activity use our `mockHolder` as the TodoItemsHolder
        val activityUnderTest = activityController.get()
        activityUnderTest.holder = mockHolder
    }

    @Test
    fun when_activityIsLaunched_then_theEditTextStartsEmpty() {
        // setup
        activityController.create().visible()
        val activityUnderTest = activityController.get()
        val editText = activityUnderTest.findViewById<EditText?>(R.id.editTextInsertTask)
        val userInput = editText.text.toString()
        // verify
        assertTrue(userInput.isEmpty())
    }

    @Test
    fun when_userPutInputAndClicksButton_then_activityShouldCallAddItem() {
        // setup
        val userInput = "Call my grandma today at 18:00"
        activityController.create().visible() // let the activity think it is being shown
        val activityUnderTest = activityController.get()
        val editText = activityUnderTest.findViewById<EditText?>(R.id.editTextInsertTask)
        val fab = activityUnderTest.findViewById<View?>(R.id.buttonCreateTodoItem)

        // test - mock user interactions
        editText.setText(userInput)
        fab.performClick()

        // verify: verify that `mockHolder.addNewInProgressItem()` was called, with exactly same string
        Mockito.verify(mockHolder).addNewInProgressItem(ArgumentMatchers.eq(userInput))
    }

    @Test
    fun when_userPutInputAndClicksButton_then_inputShouldBeErasedFromEditText() {
        //    TODO: implement the test.
        //     to set up the test, take a look at `when_userPutInputAndClicksButton_then_activityShouldCallAddItem()`
        //     to verify, use methods such as "assertEquals(...)" or "assertTrue(...)"
    }

    @Test
    fun when_holderSaysNoItems_then_recyclerViewShowsZeroItems() {
        // setup
        Mockito.`when`(mockHolder.getCurrentItems())
                .thenReturn(ArrayList())

        // test - let the activity think it is being shown
        activityController.create().visible()

        // verify
        val activityUnderTest = activityController.get()
        val recyclerView = activityUnderTest.findViewById<RecyclerView>(R.id.recyclerTodoItemsList)
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertEquals(0, adapter?.itemCount)
    }

    @Test
    fun when_holderSays1ItemOfTypeInProgress_then_activityShouldShow1MatchingViewInRecyclerView() {
        // setup

        // when asking the `mockHolder` to get the current items, return a list with 1 item of type "in progress"
        val itemsReturnedByHolder = ArrayList<TodoItem>()
        Mockito.`when`(mockHolder.getCurrentItems())
                .thenReturn(itemsReturnedByHolder)
        val itemInProgress = TodoItem(description = "do homework", timestampCreated = System.currentTimeMillis(), status = TodoItem.Status.IN_PROGRESS)
        itemsReturnedByHolder.add(itemInProgress)

        // test - let the activity think it is being shown
        activityController.create().visible()

        // verify: make sure that the activity shows a matching subview in the recycler view
        val activityUnderTest = activityController.get()
        val recyclerView = activityUnderTest.findViewById<RecyclerView?>(R.id.recyclerTodoItemsList)

        // 1. verify that adapter says there should be 1 item showing
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertEquals(1, adapter?.itemCount)

        // 2. verify that the shown view has a checkbox being not-checked and has a TextView showing the correct description
        val viewInRecycler = recyclerView.findViewHolderForAdapterPosition(0)?.itemView
        // TODO: implement.
        //  use `viewInRecycler.findViewById(...)` to find the checkbox and the description subviews,
        //  and make sure the checkbox is not checked and the TextView shows the correct description
    }

    @Test
    fun when_holderSays1ItemOfTypeDone_then_activityShouldShow1MatchingViewInRecyclerView() {
        // setup

        // when asking the `mockHolder` to get the current items, return a list with 1 item of type "DONE"
        val itemsReturnedByHolder = ArrayList<TodoItem>()
        Mockito.`when`(mockHolder.getCurrentItems())
                .thenReturn(itemsReturnedByHolder)
        val itemDone = TodoItem(description = "buy tomatoes", timestampCreated = System.currentTimeMillis(), status = TodoItem.Status.DONE)
        // TODO: customize `itemDone` to have type DONE and description "buy tomatoes"
        itemsReturnedByHolder.add(itemDone)

        // test - let the activity think it is being shown
        activityController.create().visible()

        // verify: make sure that the activity shows a matching subview in the recycler view
        val activityUnderTest = activityController.get()
        val recyclerView = activityUnderTest.findViewById<RecyclerView?>(R.id.recyclerTodoItemsList)

        // 1. verify that adapter says there should be 1 item showing
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        assertEquals(1, adapter?.itemCount)

        // 2. verify that the shown view has a checkbox being checked and has a TextView showing the correct description
        val viewInRecycler = recyclerView.findViewHolderForAdapterPosition(0)?.itemView
        // TODO: implement.
        //  use `viewInRecycler.findViewById(...)` to find the checkbox and the description subviews,
        //  and make sure the checkbox is checked and the TextView shows the correct description
    }
}