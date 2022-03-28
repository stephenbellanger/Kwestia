package com.sbellanger.favorite.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.favorite.domain.model.FavoriteEntity
import com.sbellanger.favorite.presentation.FavoriteViewState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class GetFavoriteViewStateUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getFavoriteViewStateUseCase: GetFavoriteViewStateUseCase

    @MockK
    @field:MockK
    lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @get:Rule
    var toothPickRule = ToothPickRule(this, "Test")

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    @Before
    @CallSuper
    fun beforeEachTest() {
        MockKAnnotations.init(this)
        toothPickRule.inject(this)
        getFavoriteViewStateUseCase =
            toothPickRule.getInstance(GetFavoriteViewStateUseCase::class.java)
    }

    @After
    fun afterEachTest() {
        Toothpick.reset()
        clearAllMocks()
        unmockkAll()
    }

    ///////////////////////////////////////////////////////////////////////////
    // TECHNICAL CASE
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun testGetFavoriteViewStateUseCaseNominalCase() {
        // GIVEN
        every {
            getFavoritesUseCase.execute()
        } returns Observable.just(
            listOf(
                FavoriteEntity(
                    id = 1,
                    name = "name",
                    description = "description",
                    language = "Kotlin",
                    openedIssues = 3,
                    color = 0,
                    repositoryName = "kotlin/kotlin"
                )
            )
        )

        // WHEN
        val result = getFavoriteViewStateUseCase
            .execute()
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is FavoriteViewState.Loaded }
        }
    }

    @Test
    fun testGetFavoriteViewStateUseCaseNoResult() {
        // GIVEN
        every {
            getFavoritesUseCase.execute()
        } returns Observable.just(
            emptyList()
        )

        // WHEN
        val result = getFavoriteViewStateUseCase
            .execute()
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is FavoriteViewState.NoResult }
        }
    }

    @Test
    fun testGetFavoriteViewStateUseCaseErrorCase() {
        // GIVEN
        every {
            getFavoritesUseCase.execute()
        } returns Observable.error(Error("some error"))
        // WHEN
        val result = getFavoriteViewStateUseCase
            .execute()
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertValue { it is FavoriteViewState.Error }
        }
    }
}
