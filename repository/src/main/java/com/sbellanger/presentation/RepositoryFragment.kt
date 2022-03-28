package com.sbellanger.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.arch.fragment.KtpVbBaseFragment
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.presentation.view.RepositoryAdapter
import com.sbellanger.repository.databinding.RepositoryFragmentBinding
import com.sbellanger.ui_kit.view.SearchView
import toothpick.config.Module
import javax.inject.Inject

class RepositoryFragment :
    KtpVbBaseFragment<RepositoryFragmentBinding>(),
    IRepositoryContract.ViewCapability,
    SearchView.IOnSearchListener,
    IRepositoryContract.ViewEvent.IRepositoryListener {

    companion object {
        fun newInstance() = RepositoryFragment()
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var viewModel: IRepositoryContract.ViewModel

    @Inject
    lateinit var navigator: IRepositoryContract.ViewNavigation

    @Inject
    lateinit var repositoryAdapter: RepositoryAdapter

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val modules: Array<Module>
        get() = arrayOf(RepositoryFragmentModule(this@RepositoryFragment))

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RepositoryFragmentBinding {
        return RepositoryFragmentBinding.inflate(inflater, container, false)
    }

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
            .viewState
            .distinctUntilChanged()
            .observe(viewLifecycleOwner, this::handleViewState)

        viewModel
            .viewEvent
            .distinctUntilChanged()
            .observe(viewLifecycleOwner, this::handleViewEvent)

        setupRecyclerView()

        binding.searchField.addSearchListener(this@RepositoryFragment)
    }

    override fun onClear() {
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
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun showLoader(shouldShow: Boolean) {
        binding.loader.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    override fun showResults(viewState: RepositoryViewState.Loaded) {
        hideErrorViews()
        repositoryAdapter.setData(viewState.repositories)
    }

    override fun showError() {
        binding.errorView.visibility = View.VISIBLE
        repositoryAdapter.clear()
    }

    override fun showNoResult() {
        binding.noResult.visibility = View.VISIBLE
        repositoryAdapter.clear()
    }

    override fun onFavoriteClicked(repository: RepositoryEntity, isFavorite: Boolean) {
        if (isFavorite) {
            viewModel.addRepository(repository)
        } else {
            viewModel.removeRepository(repository.id)
        }
    }

    override fun onRepositoryClicked(repository: RepositoryEntity) {
        navigator.showRepositoryIssues(repository.repositoryName, repository.openIssuesCount)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun handleViewState(viewState: RepositoryViewState) {
        when (viewState) {
            RepositoryViewState.Error -> {
                showLoader(false)
                showError()
            }
            is RepositoryViewState.Loaded -> {
                showLoader(false)
                showResults(viewState)
            }
            RepositoryViewState.Loading -> showLoader(true)
            RepositoryViewState.NoResult -> {
                showLoader(false)
                showNoResult()
            }
        }
    }

    private fun handleViewEvent(viewEvent: RepositoryViewEvent) {
        when (viewEvent) {
            RepositoryViewEvent.RepositoryAdded -> navigator.showRepositoryAddedFeedback()
            RepositoryViewEvent.RepositoryRemoved -> navigator.showRepositoryRemovedFeedback()
        }
    }

    private fun hideErrorViews() {
        binding.noResult.visibility = View.GONE
        binding.errorView.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.searchResult.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = repositoryAdapter.apply {
                setListener(this@RepositoryFragment)
            }
        }
    }
}

