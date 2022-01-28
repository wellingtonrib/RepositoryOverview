package com.jwar.github_repo.core.di

import com.jwar.github_repo.ui.about.AboutViewModel
import com.jwar.github_repo.ui.dashboard.DashboardViewModel
import com.jwar.github_repo.ui.issues.IssueViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        DashboardViewModel(get())
    }

    viewModel {
        IssueViewModel(get())
    }

    viewModel {
        AboutViewModel(get())
    }

}