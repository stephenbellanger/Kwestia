package com.sbellanger.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sbellanger.arch.fragment.KtpVbBaseFragment
import com.sbellanger.favorite.databinding.FavoriteFragmentBinding
import com.sbellanger.favorite.domain.model.FavoriteEntity
import com.sbellanger.favorite.presentation.view.FavoriteAdapter
import toothpick.config.Module
import javax.inject.Inject

class FavoriteFragment :
    KtpVbBaseFragment<FavoriteFragmentBinding>(),
    IFavoriteContract.ViewCapability,
    IFavoriteContract.ViewEvent.IFavoriteListener {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var viewModel: IFavoriteContract.ViewModel

    @Inject
    lateinit var navigator: IFavoriteContract.ViewNavigation

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val modules: Array<Module>
        get() = arrayOf(FavoriteFragmentModule(this@FavoriteFragment))

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FavoriteFragmentBinding {
        return FavoriteFragmentBinding.inflate(inflater, container, false)
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
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun showLoader(shouldShow: Boolean) {
        binding.loader.visibility = if (shouldShow) View.VISIBLE else View.GONE
    }

    override fun showFavorites(viewState: FavoriteViewState.Loaded) {
        binding.noResult.visibility = View.GONE
        binding.errorView.visibility = View.GONE
        favoriteAdapter.setData(viewState.favorites)
    }

    override fun showError() {
        binding.errorView.visibility = View.VISIBLE
        favoriteAdapter.clear()
    }

    override fun showNoResult() {
        binding.noResult.visibility = View.VISIBLE
        favoriteAdapter.clear()
    }

    override fun onFavoriteClick(favorite: FavoriteEntity) {
        navigator.showRepositoryIssues(favorite.repositoryName, favorite.openedIssues)
    }

    override fun onFavoriteRemovedClick(id: Int) {
        viewModel.removeFavorite(id)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun handleViewState(viewState: FavoriteViewState) {
        when (viewState) {
            FavoriteViewState.Error -> {
                showLoader(false)
                showError()
            }
            is FavoriteViewState.Loaded -> {
                showLoader(false)
                showFavorites(viewState)
            }
            FavoriteViewState.Loading -> showLoader(true)
            FavoriteViewState.NoResult -> {
                showLoader(false)
                showNoResult()
            }
        }
    }

    private fun handleViewEvent(viewEvent: FavoriteViewEvent) {
        when (viewEvent) {
            FavoriteViewEvent.RepositoryRemoved -> navigator.showRemoveFavoriteFeedback()
        }
    }

    private fun setupRecyclerView() {
        binding.favoriteResult.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = favoriteAdapter.apply {
                setListener(this@FavoriteFragment)
            }
        }
    }
}
