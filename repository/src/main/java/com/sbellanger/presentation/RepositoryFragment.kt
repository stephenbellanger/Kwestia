package com.sbellanger.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.sbellanger.arch.fragment.BaseFragment
import com.sbellanger.presentation.view.RepositoryScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class RepositoryFragment : BaseFragment() {

    companion object {
        fun newInstance() = RepositoryFragment()
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    private val viewModel: RepositoryViewModel by viewModels()

    @Inject
    lateinit var navigator: IRepositoryContract.ViewNavigation

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
                RepositoryScreen(
                    viewState = viewModel.viewState.value,
                    text = viewModel.textInputState.value,
                    onTextChange = {
                        viewModel.setText(it)
                    },
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

    private fun handleViewEvent(viewEvent: RepositoryViewEvent) {
        when (viewEvent) {
            RepositoryViewEvent.RepositoryAdded -> navigator.showRepositoryAddedFeedback()
            RepositoryViewEvent.RepositoryRemoved -> navigator.showRepositoryRemovedFeedback()
            is RepositoryViewEvent.GoToIssue -> navigator.showRepositoryIssues(
                viewEvent.repositoryName,
                viewEvent.issueCount
            )
        }
    }
}

