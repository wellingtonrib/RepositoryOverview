package com.jwar.github_repo.ui.dashboard

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jwar.github_repo.R
import com.jwar.github_repo.domain.model.RepoModel
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

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest: AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<DashboardFragment>
    private val dashboardViewModel: DashboardViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(module { viewModel { dashboardViewModel } })
        }
        scenario = launchFragmentInContainer()
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun shouldBindRepoCounts() {
        scenario.onFragment { fragment ->
            fragment.bindRepo(
                mockk<RepoModel>(relaxed = true) {
                    every { watchersCount } returns 100
                    every { forksCount } returns 200
                    every { subscribersCount } returns 300
                    every { openIssuesCount } returns 400
                }
            )
        }

        onView(allOf(withId(R.id.textViewCount), isDescendantOfA(withId(R.id.cardWatchers))))
            .check(matches(withText("100")))

        onView(allOf(withId(R.id.textViewCount), isDescendantOfA(withId(R.id.cardForks))))
            .check(matches(withText("200")))

        onView(allOf(withId(R.id.textViewCount), isDescendantOfA(withId(R.id.cardSubscribers))))
            .check(matches(withText("300")))

        onView(allOf(withId(R.id.textViewCount), isDescendantOfA(withId(R.id.cardIssues))))
            .check(matches(withText("400")))
    }
}