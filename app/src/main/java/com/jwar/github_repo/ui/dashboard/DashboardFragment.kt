package com.jwar.github_repo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jwar.github_repo.core.shared.toast
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        setUpViewModel()
    }

    private fun setUpViews() {
        binding.swipeRefresh.setOnRefreshListener {
            dashboardViewModel.refresh()
        }
    }

    private fun setUpViewModel() {
        dashboardViewModel.refresh()
        dashboardViewModel.isRefreshing.observe(viewLifecycleOwner, { status ->
            val (isRefreshing, message) = status
            binding.swipeRefresh.isRefreshing = isRefreshing
            message?.let { toast(it) }
        })
        dashboardViewModel.repo.observe(viewLifecycleOwner, { resource ->
            resource.data?.let { bindRepo(it) }
        })
    }

    fun bindRepo(repoModel: RepoModel) {
        with(binding) {
            cardWatchers.cardCount(repoModel.watchersCount)
            cardForks.cardCount(repoModel.forksCount)
            cardIssues.cardCount(repoModel.openIssuesCount)
            cardSubscribers.cardCount(repoModel.subscribersCount)
        }
    }

}