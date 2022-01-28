package com.jwar.github_repo.ui.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jwar.github_repo.core.shared.Status
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.databinding.FragmentIssueListBinding
import com.jwar.github_repo.core.shared.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueListFragment : Fragment(), IssueListener {

    private lateinit var binding: FragmentIssueListBinding

    private val issueViewModel: IssueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        setUpViewModel()
    }

    private fun setUpViews() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                issueViewModel.refresh()
            }
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = IssueRecylerViewAdapter(this@IssueListFragment, listOf())
        }
    }

    private fun setUpViewModel() {
        issueViewModel.issues.observe(viewLifecycleOwner, { resource ->
            when(resource.status) {
                Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                Status.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    resource.data?.let { bindIssues(it) }
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    toast(resource.message ?: "Unknown Error")
                }
            }
        })
    }

    fun bindIssues(issues: List<IssueModel>) {
        (binding.recyclerView.adapter as IssueRecylerViewAdapter)
            .update(issues)
        binding.textViewEmpty.isVisible = issues.isEmpty()
    }

    override fun onSelect(issueModel: IssueModel) {
        findNavController().navigate(
                IssueListFragmentDirections.actionIssueDetail(issueModel)
        )
    }

}
