package com.sbellanger.favorite.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.sbellanger.arch.fragment.BaseFragment
import com.sbellanger.favorite.presentation.view.FavoriteScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class FavoriteFragment : BaseFragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    private val viewModel: FavoriteViewModel by viewModels()

    @Inject
    lateinit var navigator: IFavoriteContract.ViewNavigation

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("MissingSuperCall")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FavoriteScreen(
                    viewState = viewModel.viewState.value,
                    onEventHandler = viewModel::requestViewAction
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.requestViewState()

        viewModel
            .viewEvent
            .observe(viewLifecycleOwner, this::handleViewEvent)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun handleViewEvent(viewEvent: FavoriteViewEvent) {
        when (viewEvent) {
            FavoriteViewEvent.RepositoryRemoved -> navigator.showRemoveFavoriteFeedback()
            is FavoriteViewEvent.GoToIssue -> navigator.showRepositoryIssues(
                viewEvent.repositoryName,
                viewEvent.issueCount
            )
        }
    }
}
