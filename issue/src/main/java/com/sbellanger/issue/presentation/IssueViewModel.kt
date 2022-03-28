package com.sbellanger.issue.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.sbellanger.arch.viewmodel.BaseViewModel
import com.sbellanger.issue.domain.usecase.GetIssueViewStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(application: Application) :
    BaseViewModel(application),
    IIssueContract.ViewModel {

    companion object {
        private const val TEXT_INPUT_DEBOUNCE = 500L
        private const val ISSUE_NAME_MIN_LENGTH = 3
        private const val EMPTY_TEXT = ""
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getIssueViewStateUseCase: GetIssueViewStateUseCase

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState = mutableStateOf<IssueViewState>(IssueViewState.Init)
    override val viewEvent = MutableLiveData<IssueViewEvent>()
    override val textInputState = mutableStateOf("")

    private var repositoryName: String? = null
    private val textInput = BehaviorSubject.create<String>()

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        textInput
            .subscribeOn(Schedulers.io())
            .debounce(TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() && it.length >= ISSUE_NAME_MIN_LENGTH }
            .doOnNext { text ->
                repositoryName?.let {
                    requestViewState(it, text)
                }
            }
            .bindSubAndLog("textInput")
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun requestViewState(repositoryName: String, issueNameFilter: String) {
        getIssueViewStateUseCase
            .execute(repositoryName, issueNameFilter)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.value = IssueViewState.Loading }
            .doOnSuccess { viewState.value = it }
            .bindSubAndLog("GetIssueViewStateUseCase")
    }

    override fun setRepositoryName(repositoryName: String) {
        this.repositoryName = repositoryName
    }

    override fun setText(text: String) {
        textInput.onNext(text)
        textInputState.value = text
    }

    override fun requestViewAction(viewAction: IssueViewAction) {
        when (viewAction) {
            IssueViewAction.ClearSearch -> setText(EMPTY_TEXT)
            IssueViewAction.GoBack -> viewEvent.postValue(IssueViewEvent.GoBack)
        }
    }


}
