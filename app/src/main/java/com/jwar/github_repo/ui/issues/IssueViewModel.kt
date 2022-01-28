package com.jwar.github_repo.ui.issues

import androidx.lifecycle.*
import com.jwar.github_repo.core.shared.RefreshableLiveData
import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.repositories.RepoRepository

class IssueViewModel(
    private val repoRepository: RepoRepository
) : ViewModel() {

    private val _issues = RefreshableLiveData {
        repoRepository.fetchIssues().asLiveData(viewModelScope.coroutineContext)
    }
    val issues: LiveData<Resource<List<IssueModel>>> = _issues

    private val _issueSelected = MutableLiveData<IssueModel>()
    val issueSelected: LiveData<IssueModel> = _issueSelected

    fun select(issueModel: IssueModel) {
        _issueSelected.postValue(issueModel)
    }

    fun refresh() { _issues.refresh() }

}