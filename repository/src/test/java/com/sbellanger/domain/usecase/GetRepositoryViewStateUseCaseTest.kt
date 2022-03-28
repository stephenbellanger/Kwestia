package com.sbellanger.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.domain.model.RepositoryEntity
import com.sbellanger.presentation.RepositoryViewState
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class GetRepositoryViewStateUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getRepositoryViewStateUseCase: GetRepositoryViewStateUseCase

    @MockK
    @field:MockK
    lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

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
        getRepositoryViewStateUseCase =
            toothPickRule.getInstance(GetRepositoryViewStateUseCase::class.java)
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
    fun testGetRepositoryViewStateUseCaseNominalCase() {
        // GIVEN
        val name = "flutter"

        every {
            getRepositoriesUseCase.execute(any())
        } returns Observable.just(
            listOf(
                RepositoryEntity(
                    id = 1,
                    name = "name",
                    description = "description",
                    isFavorite = false,
                    language = "Kotlin",
                    openIssuesCount = 4,
                    repositoryName = "kotlin/kotlin"
                )
            )
        )

        // WHEN
        val result = getRepositoryViewStateUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is RepositoryViewState.Loaded }
        }

        verify(exactly = 1) {
            getRepositoriesUseCase.execute(any())
        }
    }

    @Test
    fun testGetRepositoryViewStateUseCaseNoResult() {
        // GIVEN
        val name = "flutter"

        every {
            getRepositoriesUseCase.execute(any())
        } returns Observable.just(
            emptyList()
        )

        // WHEN
        val result = getRepositoryViewStateUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is RepositoryViewState.NoResult }
        }

        verify(exactly = 1) {
            getRepositoriesUseCase.execute(any())
        }
    }

    @Test
    fun testGetRepositoryViewStateUseCaseErrorCase() {
        // GIVEN
        val name = "flutter"

        every {
            getRepositoriesUseCase.execute(any())
        } returns Observable.error(Error("some error"))

        // WHEN
        val result = getRepositoryViewStateUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is RepositoryViewState.Error }
        }

        verify(exactly = 1) {
            getRepositoriesUseCase.execute(any())
        }
    }
}
