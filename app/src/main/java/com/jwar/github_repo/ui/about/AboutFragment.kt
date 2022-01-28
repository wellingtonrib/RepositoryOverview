package com.jwar.github_repo.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.databinding.FragmentAboutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    private val aboutViewModel: AboutViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        aboutViewModel.repo.observe(viewLifecycleOwner, { resource ->
            resource.data?.let { bindRepo(it) }
        })
    }

    fun bindRepo(repoModel: RepoModel) {
        with(binding) {
            textViewTitle.text = repoModel.name
            textViewSubtitle.text = repoModel.fullName
            textViewDescription.text = repoModel.description
            textViewLink.text = repoModel.htmlUrl
        }
    }
}