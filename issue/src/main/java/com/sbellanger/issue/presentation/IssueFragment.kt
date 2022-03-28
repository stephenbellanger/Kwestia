package com.sbellanger.issue.presentation

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
import com.sbellanger.issue.presentation.view.IssueScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class IssueFragment : BaseFragment() {

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

    private val viewModel: IssueViewModel by viewModels()

    @Inject
    lateinit var navigator: IIssueContract.ViewNavigation

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
                IssueScreen(
                    viewState = viewModel.viewState.value,
                    requireArguments().getString(REPOSITORY_NAME_KEY, ""),
                    requireArguments().getInt(REPOSITORY_ISSUE_COUNT_KEY),
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

        arguments?.getString(REPOSITORY_NAME_KEY)?.let { repositoryName ->
            viewModel.setRepositoryName(repositoryName)
        }

        viewModel
            .viewEvent
            .observe(viewLifecycleOwner, this::handleViewEvent)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun handleViewEvent(viewEvent: IssueViewEvent) {
        when (viewEvent) {
            IssueViewEvent.GoBack -> navigator.goBack()
        }
    }
}
