package com.jwar.github_repo.ui.dashboard

import androidx.lifecycle.*
import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.core.shared.Status
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.domain.repositories.RepoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repoRepository: RepoRepository
) : ViewModel() {

    private val _repo = repoRepository
        .getRepo()
        .asLiveData(viewModelScope.coroutineContext)
    val repo: LiveData<Resource<RepoModel>> = _repo

    private val _isRefreshing = MutableLiveData<Pair<Boolean,String?>>()
    val isRefreshing: LiveData<Pair<Boolean,String?>> = _isRefreshing

    fun refresh() {
        viewModelScope.launch {
            repoRepository.fetchRepo().collect {
                _isRefreshing.postValue(
                    Pair(it.status == Status.LOADING, it.message)
                )
            }
        }
    }

}