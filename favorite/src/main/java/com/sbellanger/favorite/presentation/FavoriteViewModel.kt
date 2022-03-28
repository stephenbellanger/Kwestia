package com.sbellanger.favorite.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sbellanger.arch.viewmodel.KtpBaseViewModel
import com.sbellanger.favorite.domain.usecase.GetFavoriteViewStateUseCase
import com.sbellanger.favorite.domain.usecase.RemoveFavoriteUseCase
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(application: Application) :
    KtpBaseViewModel(application),
    IFavoriteContract.ViewModel {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var getFavoriteViewStateUseCase: GetFavoriteViewStateUseCase

    @Inject
    lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState = MutableLiveData<FavoriteViewState>()
    override val viewEvent = MutableLiveData<FavoriteViewEvent>()

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    init {
        requestViewState()
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun requestViewState() {
        getFavoriteViewStateUseCase
            .execute()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.postValue(FavoriteViewState.Loading) }
            .doOnNext { viewState.postValue(it) }
            .bindSubAndLog("GetFavoriteViewStateUseCase")
    }

    override fun removeFavorite(id: Int) {
        removeFavoriteUseCase
            .execute(id)
            .subscribeOn(Schedulers.io())
            .doOnComplete { viewEvent.postValue(FavoriteViewEvent.RepositoryRemoved) }
            .bindSubAndLog("RemoveFavoriteUseCase")
    }
}
