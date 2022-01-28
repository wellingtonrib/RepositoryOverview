package com.jwar.github_repo.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.domain.repositories.RepoRepository

class AboutViewModel(
    private val repoRepository: RepoRepository
) : ViewModel() {

    private val _repo = repoRepository
        .getRepo()
        .asLiveData(viewModelScope.coroutineContext)
    val repo: LiveData<Resource<RepoModel>> = _repo

}