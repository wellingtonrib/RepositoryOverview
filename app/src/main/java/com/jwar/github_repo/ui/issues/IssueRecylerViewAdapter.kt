package com.jwar.github_repo.ui.issues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jwar.github_repo.R
import com.jwar.github_repo.databinding.AdapterIssueBinding
import com.jwar.github_repo.domain.model.IssueModel
import java.util.*

interface IssueListener {
    fun onSelect(issueModel: IssueModel)
}

class IssueViewHolder(val binding: AdapterIssueBinding) : RecyclerView.ViewHolder(binding.root)

class IssueRecylerViewAdapter(var listener: IssueListener, var data: List<IssueModel>):
        RecyclerView.Adapter<IssueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = AdapterIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding).apply {
            itemView.setOnClickListener {
                listener.onSelect(data[adapterPosition])
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val issue = data[position]
        with(holder.binding) {
            textViewIssueTitle.text = issue.title
            textViewIssueState.apply {
                text = issue.state.toUpperCase(Locale.getDefault())
                setBackgroundResource(
                    when(issue.state) {
                        "open" -> R.drawable.state_open_background
                        "closed" -> R.drawable.state_closed_background
                        else -> R.drawable.state_default_background
                    }
                )
            }
        }
    }

    fun update(issueModels: List<IssueModel>) {
        this.data = issueModels
        notifyDataSetChanged()
    }

}