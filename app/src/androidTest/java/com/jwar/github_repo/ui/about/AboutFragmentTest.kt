package com.jwar.github_repo.ui.about

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
import com.jwar.github_repo.domain.model.RepoModel
import io.mockk.every
import io.mockk.mockk
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
class AboutFragmentTest: AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<AboutFragment>
    private val aboutViewModel: AboutViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(module { viewModel { aboutViewModel } })
        }
        scenario = launchFragmentInContainer()
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun shouldBindRepoInfo() {
        scenario.onFragment { fragment ->
            fragment.bindRepo(
                mockk<RepoModel>(relaxed = true) {
                    every { name } returns "Kotlin"
                    every { fullName } returns "Jetbrains/Kotlin"
                    every { description } returns "The Kotlin Programming Language"
                    every { htmlUrl } returns "https://github.com/JetBrains/Kotlin"
                }
            )
        }

        onView(withId(R.id.textViewTitle))
            .check(matches(withText("Kotlin")))

        onView(withId(R.id.textViewSubtitle))
            .check(matches(withText("Jetbrains/Kotlin")))

        onView(withId(R.id.textViewDescription))
            .check(matches(withText("The Kotlin Programming Language")))

        onView(withId(R.id.textViewLink))
            .check(matches(withText("https://github.com/JetBrains/Kotlin")))
    }
}