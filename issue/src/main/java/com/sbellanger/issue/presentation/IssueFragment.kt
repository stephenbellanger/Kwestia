package com.sbellanger.issue.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.arch.fragment.KtpVbBaseFragment
import com.sbellanger.issue.R
import com.sbellanger.issue.databinding.IssueFragmentBinding
import com.sbellanger.issue.domain.model.IssueEntity
import com.sbellanger.issue.presentation.view.IssueAdapter
import com.sbellanger.ui_kit.view.SearchView
import toothpick.config.Module
import javax.inject.Inject

class IssueFragment :
    KtpVbBaseFragment<IssueFragmentBinding>(),
    IIssueContract.ViewCapability,
    SearchView.IOnSearchListener {

    companion object {
        private const val REPOSITORY_NAME_KEY = "REPOSITORY_NAME_KEY"
        private const val REPOSITORY_ISSUE_COUNT_KEY = "REPOSITORY_ISSUE_COUNT_KEY"

        fun newInstance(repositoryName: String, issueCount: Int) = IssueFragment().apply {
            arguments = Bundle().apply {
                putString(REPOSITORY_NAME_KEY, repositoryName)
                putInt(REPOSITORY_ISSUE_COUNT_KEY, issueCount)
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var viewModel: IIssueContract.ViewModel

    @Inject
    lateinit var navigator: IIssueContract.ViewNavigation

    @Inject
    lateinit var issueAdapter: IssueAdapter

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val modules: Array<Module>
        get() = arrayOf(IssueFragmentModule(this@IssueFragment))

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?): IssueFragmentBinding {
        return IssueFragmentBinding.inflate(inflater, container, false)
    }

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString(REPOSITORY_NAME_KEY)?.let { repositoryName ->
                viewModel.setRepositoryName(repositoryName)
                setTitle(repositoryName)
            }
            setIssueIndicator(it.getInt(REPOSITORY_ISSUE_COUNT_KEY))
        }

        viewModel
            .viewState
            .distinctUntilChanged()
            .map { (it as? IssueViewState.Loaded)?.issues ?: emptyList() }
            .observe(viewLifecycleOwner, this::showIssues)

        viewModel
            .viewState
            .distinctUntilChanged()
            .map { it is IssueViewState.Loading }
            .observe(viewLifecycleOwner, binding.loader::isVisible::set)

        viewModel
            .viewState
            .distinctUntilChanged()
            .map { it is IssueViewState.Error }
            .observe(viewLifecycleOwner, binding.errorView::isVisible::set)

        viewModel
            .viewState
            .distinctUntilChanged()
            .map { it is IssueViewState.NoIssue }
            .observe(viewLifecycleOwner) { if (it) showNoIssue() }

        binding
            .backArrow
            .setOnClickListener { navigator.goBack() }

        setupRecyclerView()

        binding.searchField.addSearchListener(this@IssueFragment)
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun setTitle(repositoryName: String) {
        binding.title.text = getString(R.string.issue_title, repositoryName)
    }

    override fun setIssueIndicator(issueNumber: Int) {
        binding.issueIndicator.setIssueNumber(issueNumber)
    }

    override fun showIssues(issues: List<IssueEntity>) {
        hideErrorViews()
        issueAdapter.setData(issues)
    }

    override fun showNoIssue() {
        binding.noResult.visibility = View.VISIBLE
        issueAdapter.clear()
    }

    override fun onClear() {
        issueAdapter.clear()
        hideErrorViews()
    }

    override fun onTextChanged(text: String) {
        viewModel.setText(text)
    }

    override fun afterTextChanged(text: String) {
        if (text.isEmpty()) {
            onClear()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun setupRecyclerView() {
        binding.issueResult.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = issueAdapter
        }
    }

    private fun hideErrorViews() {
        binding.noResult.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }
}
