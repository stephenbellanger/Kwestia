package com.sbellanger.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sbellanger.arch.viewmodel.KtpBaseViewModel
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.domain.usecase.AddRepositoryUseCase
import com.sbellanger.domain.usecase.GetRepositoryViewStateUseCase
import com.sbellanger.domain.usecase.RemoveRepositoryUseCase
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(application: Application) :
    KtpBaseViewModel(application),
    IRepositoryContract.ViewModel {

    companion object {
        private const val TEXT_INPUT_DEBOUNCE = 500L
        private const val REPOSITORY_NAME_MIN_LENGTH = 3
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

    override val viewState = MutableLiveData<RepositoryViewState>()
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
            .doOnSubscribe { viewState.postValue(RepositoryViewState.Loading) }
            .doOnNext { viewState.postValue(it) }
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
        textInput.onNext(text)
    }
}
