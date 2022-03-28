package com.sbellanger.favorite.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.favorite.data.IFavoriteRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class RemoveFavoriteUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    @MockK
    @field:MockK
    lateinit var repository: IFavoriteRepository

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
        removeFavoriteUseCase =
            toothPickRule.getInstance(RemoveFavoriteUseCase::class.java)
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
    fun testRemoveFavoriteUseCaseNominalCase() {
        // GIVEN
        val id = 1

        every {
            repository.removeFavorite(any())
        } returns Completable.complete()

        // WHEN
        val result = removeFavoriteUseCase
            .execute(id)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
        }

        verify(exactly = 1) {
            repository.removeFavorite(any())
        }
    }

    @Test
    fun testRemoveFavoriteUseCaseErrorCase() {
        // GIVEN
        val id = 1

        every {
            repository.removeFavorite(any())
        } returns Completable.error(Error("some error"))

        // WHEN
        val result = removeFavoriteUseCase
            .execute(id)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertErrorMessage("some error")
        }

        verify(exactly = 1) {
            repository.removeFavorite(any())
        }
    }
}
