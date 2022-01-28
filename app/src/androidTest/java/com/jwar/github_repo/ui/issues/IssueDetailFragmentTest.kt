package com.jwar.github_repo.ui.issues

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jwar.github_repo.R
import com.jwar.github_repo.domain.model.IssueModel
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import java.util.*

@RunWith(AndroidJUnit4::class)
class IssueDetailFragmentTest: AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<IssueDetailFragment>
    private val issueViewModel: IssueViewModel = mockk(relaxed = true)
    private val issue = mockk<IssueModel>(relaxed = true) {
        every { title } returns "Issue 1"
        every { createdAt } returns
                Calendar.getInstance().apply {
                    set(2022,0,1,1,1)
                }.time
    }

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(module { viewModel { issueViewModel } })
        }
        scenario = launchFragmentInContainer(bundleOf("Issue" to issue))
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun shouldBindRepoCounts() {
        scenario.onFragment { fragment ->
            fragment.bindIssue(issue)
        }

        onView(allOf(withId(R.id.textViewIssueTitle)))
            .check(matches(withText("Issue 1")))
        onView(allOf(withId(R.id.textViewIssueDate)))
            .check(matches(withText("01/01/2022 01:01")))
    }
}