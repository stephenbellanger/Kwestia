package com.sbellanger.issue.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sbellanger.arch.viewmodel.KtpBaseViewModel
import com.sbellanger.issue.domain.usecase.GetIssueViewStateUseCase
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class IssueViewModel @Inject constructor(application: Application) :
    KtpBaseViewModel(application),
    IIssueContract.ViewModel {

    companion object {
        private const val TEXT_INPUT_DEBOUNCE = 500L
        private const val ISSUE_NAME_MIN_LENGTH = 3
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getIssueViewStateUseCase: GetIssueViewStateUseCase

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState = MutableLiveData<IssueViewState>()

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
            .doOnSubscribe { viewState.postValue(IssueViewState.Loading) }
            .doOnSuccess { viewState.postValue(it) }
            .bindSubAndLog("GetIssueViewStateUseCase")
    }

    override fun setRepositoryName(repositoryName: String) {
        this.repositoryName = repositoryName
    }

    override fun setText(text: String) {
        textInput.onNext(text)
    }
}
