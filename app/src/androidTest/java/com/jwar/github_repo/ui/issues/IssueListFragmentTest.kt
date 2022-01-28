package com.jwar.github_repo.ui.issues

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jwar.github_repo.R
import com.jwar.github_repo.domain.model.IssueModel
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest


@RunWith(AndroidJUnit4::class)
class IssueListFragmentTest: AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<IssueListFragment>
    private val issueViewModel: IssueViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(module { viewModel { issueViewModel } })
        }
        scenario = launchFragmentInContainer()
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun shouldBindIssuesList() {
        scenario.onFragment { fragment ->
            fragment.bindIssues(
                arrayListOf<IssueModel>().apply {
                    for (i in 1..10) {
                        add(mockk(relaxed = true) {
                            every { title } returns "Issue $i"
                            every { state } returns if (i % 2 == 0) "open" else "closed"
                        })
                    }
                }
            )
        }

        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(10)))
        onView(withId(R.id.recyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("Issue 1")))))
            .check(matches(atPosition(0, hasDescendant(withText("CLOSED")))))
        onView(withId(R.id.recyclerView))
            .check(matches(atPosition(1, hasDescendant(withText("Issue 2")))))
            .check(matches(atPosition(1, hasDescendant(withText("OPEN")))))
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    private fun withItemCount(count: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count: $count")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount == count
            }
        }
    }
}