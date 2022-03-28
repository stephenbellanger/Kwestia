package com.sbellanger.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.sbellanger.arch.viewmodel.BaseViewModel
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.domain.usecase.AddRepositoryUseCase
import com.sbellanger.domain.usecase.GetRepositoryViewStateUseCase
import com.sbellanger.domain.usecase.RemoveRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(application: Application) :
    BaseViewModel(application),
    IRepositoryContract.ViewModel {

    companion object {
        private const val TEXT_INPUT_DEBOUNCE = 500L
        private const val REPOSITORY_NAME_MIN_LENGTH = 3
        private const val EMPTY_TEXT = ""
    }

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var addRepositoryUseCase: AddRepositoryUseCase

    @Inject
    lateinit var removeRepositoryUseCase: RemoveRepositoryUseCase

    @Inject
    lateinit var getRepositoryViewStateUseCase: GetRepositoryViewStateUseCase

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState = mutableStateOf<RepositoryViewState>(RepositoryViewState.Init)
    override val textInputState = mutableStateOf("")
    override val viewEvent = MutableLiveData<RepositoryViewEvent>()

    private val textInput = BehaviorSubject.create<String>()

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        textInput
            .subscribeOn(Schedulers.io())
            .debounce(TEXT_INPUT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() && it.length >= REPOSITORY_NAME_MIN_LENGTH }
            .doOnNext { requestViewState(it) }
            .bindSubAndLog("textInput")
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun requestViewState(name: String) {
        getRepositoryViewStateUseCase
            .execute(name)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.value = RepositoryViewState.Loading }
            .doOnNext { viewState.value = it }
            .bindSubAndLog("GetRepositoryViewStateUseCase")
    }

    override fun removeRepository(id: Int) {
        removeRepositoryUseCase
            .execute(id)
            .subscribeOn(Schedulers.io())
            .doOnComplete { viewEvent.postValue(RepositoryViewEvent.RepositoryRemoved) }
            .bindSubAndLog("RemoveRepositoryUseCase")
    }

    override fun addRepository(repository: RepositoryEntity) {
        addRepositoryUseCase
            .execute(repository)
            .subscribeOn(Schedulers.io())
            .doOnComplete { viewEvent.postValue(RepositoryViewEvent.RepositoryAdded) }
            .bindSubAndLog("AddRepositoryUseCase")
    }

    override fun setText(text: String) {
        textInputState.value = text
        textInput.onNext(text)
    }

    override fun requestViewAction(viewAction: RepositoryViewAction) {
        when (viewAction) {
            is RepositoryViewAction.RepositoryAdded -> addRepository(viewAction.repository)
            is RepositoryViewAction.RepositoryRemoved -> removeRepository(viewAction.repository.id)
            is RepositoryViewAction.GoToIssue -> viewEvent.postValue(
                RepositoryViewEvent.GoToIssue(
                    viewAction.repositoryName,
                    viewAction.issueCount
                )
            )
            RepositoryViewAction.ClearSearch -> setText(EMPTY_TEXT)
        }
    }
}
