package com.jwar.github_repo.ui.issues


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jwar.github_repo.core.shared.load
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.databinding.FragmentIssueDetailBinding
import com.jwar.github_repo.core.shared.toStringFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueDetailFragment : Fragment() {

    private lateinit var binding: FragmentIssueDetailBinding

    private val args: IssueDetailFragmentArgs by navArgs()
    private val issueViewModel: IssueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        issueViewModel.select(args.Issue)
        issueViewModel.issueSelected.observe(viewLifecycleOwner, {
            bindIssue(it)
        })
    }

    fun bindIssue(issueModel: IssueModel) {
        with(binding) {
            imageViewUserAvatar.load(issueModel.user.avatarUrl)
            textViewIssueTitle.text = issueModel.title
            textViewIssueDate.text = issueModel.createdAt.toStringFormat()
            buttonIssueView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW)
                        .setData((Uri.parse(issueModel.htmlUrl))))
            }
        }
    }

}
