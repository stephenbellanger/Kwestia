package com.sbellanger.favorite.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import com.sbellanger.arch.fragment.KtpBaseFragment
import com.sbellanger.favorite.presentation.view.FavoriteScreen
import toothpick.config.Module
import javax.inject.Inject

@ExperimentalAnimationApi
class FavoriteFragment : KtpBaseFragment() {

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

    ///////////////////////////////////////////////////////////////////////////
    // CONFIGURATION
    ///////////////////////////////////////////////////////////////////////////

    override val modules: Array<Module>
        get() = arrayOf(FavoriteFragmentModule(this@FavoriteFragment))

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
