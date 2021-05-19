"I pledge the highest level of ethical principles in support of academic excellence.  I ensure that all of my work reflects my own abilities and not those of someone else."

Question
---------
We didn't define any UX flow to let users edit a descrption on an existing TODO item.
Which UX flow will you define?
In your response notice the following:
1. how easy is it for users to figure out this flow in their first usage? (for example, a glowing button is more discoverable then a swipe-left gesture)
2. how hard to implement will your solution be?
3. how consistent is this flow with regular "edit" flows in the Android world?

Answer
------
The UX I would define would be the following:
When hovering over a TODO, a pencil button will appear next to the checkbox.
The user can press the button and a new screen will be opened with the todos text in an editable
TextView. The user can edit the todo there and press Submit or press Cancel.
Regarding figuring out this flow, it's pretty straightforward: the pencil button is known for
editing it's pretty consistent with regular edit flows in the Android world.
Implementation might be a little hard: A new activity needs to be opened and the current todo text
need to be sent with an intent to the activity. When the user finished, the changed text needs
to be sent back to the original activity and changed in the relevant view in the adapter, probably
via broadcast (or whatever method is currently used).