package com.sbellanger.favorite.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Repository
import com.sbellanger.favorite.data.IFavoriteRepository
import com.sbellanger.favorite.domain.model.FavoriteEntity
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

class GetFavoritesUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @MockK
    @field:MockK
    lateinit var repository: IFavoriteRepository

    @MockK
    @field:MockK
    lateinit var transformDaoFavoriteToEntityUseCase: TransformDaoFavoriteToEntityUseCase

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
        getFavoritesUseCase =
            toothPickRule.getInstance(GetFavoritesUseCase::class.java)
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
    fun testGetFavoritesUseCase() {
        // GIVEN
        every {
            repository.getFavorites()
        } returns Observable.just(
            listOf(
                Repository(
                    id = 1,
                    name = "name",
                    description = "description",
                    language = "Kotlin",
                    openIssuesCount = 4,
                    repositoryName = "kotlin/kotlin"
                )
            )
        )

        every {
            transformDaoFavoriteToEntityUseCase.execute(any())
        } returns listOf(
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

        // WHEN
        val result = getFavoritesUseCase
            .execute()
            .test()

        // THEN
        val expectedResult = listOf(
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
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue(expectedResult)
        }
    }

    @Test
    fun testGetFavoritesUseCaseEmptyResult() {
        // GIVEN
        every {
            repository.getFavorites()
        } returns Observable.just(
            emptyList()
        )

        every {
            transformDaoFavoriteToEntityUseCase.execute(any())
        } returns emptyList()

        // WHEN
        val result = getFavoritesUseCase
            .execute()
            .test()

        // THEN
        val expectedResult = emptyList<FavoriteEntity>()
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue(expectedResult)
        }
    }

    @Test
    fun testGetFavoritesUseCaseErrorCase() {
        // GIVEN
        every {
            repository.getFavorites()
        } returns Observable.error(Error("some error"))

        every {
            transformDaoFavoriteToEntityUseCase.execute(any())
        } returns emptyList()

        // WHEN
        val result = getFavoritesUseCase
            .execute()
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertErrorMessage("some error")
        }
    }
}
