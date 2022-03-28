package com.sbellanger.favorite.domain.usecase

import com.sbellanger.favorite.data.IFavoriteRepository
import com.sbellanger.favorite.domain.model.FavoriteEntity
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor() {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    @Inject
    lateinit var repository: IFavoriteRepository

    @Inject
    lateinit var transformDaoFavoriteToEntityUseCase: TransformDaoFavoriteToEntityUseCase

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(): Observable<List<FavoriteEntity>> {
        return repository
            .getFavorites()
            .map { transformDaoFavoriteToEntityUseCase.execute(it) }
    }
}
