package com.sbellanger.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Repository
import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.domain.model.RepositoryEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class GetRepositoriesUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @MockK
    @field:MockK
    lateinit var repository: IRepositoryRepository

    @MockK
    @field:MockK
    lateinit var transformRawRepositoryToEntityUseCase: TransformRawRepositoryToEntityUseCase

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
        getRepositoriesUseCase =
            toothPickRule.getInstance(GetRepositoriesUseCase::class.java)
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
    fun testGetRepositoriesUseCaseNominalCase() {
        // GIVEN
        val name = "flutter"

        every {
            repository.requestRepositories(any())
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
            repository.getRepositories()
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
            transformRawRepositoryToEntityUseCase.execute(any(), any())
        } returns listOf(
            RepositoryEntity(
                id = 1,
                name = "name",
                description = "description",
                isFavorite = true,
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )

        // WHEN
        val result = getRepositoriesUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it.isNotEmpty() }
        }

        verify(exactly = 1) {
            repository.getRepositories()
        }
    }

    @Test
    fun testGetRepositoriesUseCaseEmptyResult() {
        // GIVEN
        val name = "flutter"

        every {
            repository.requestRepositories(any())
        } returns Observable.just(
            emptyList()
        )

        every {
            repository.getRepositories()
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
            transformRawRepositoryToEntityUseCase.execute(any(), any())
        } returns emptyList()

        // WHEN
        val result = getRepositoriesUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it.isEmpty() }
        }

        verify(exactly = 1) {
            repository.getRepositories()
        }
    }

    @Test
    fun testGetRepositoriesUseCaseErrorCase() {
        // GIVEN
        val name = "flutter"

        every {
            repository.requestRepositories(any())
        } returns Observable.error(Error("some error"))

        every {
            repository.getRepositories()
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

        // WHEN
        val result = getRepositoriesUseCase
            .execute(name)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertErrorMessage("some error")
        }

        verify(exactly = 1) {
            repository.getRepositories()
        }
    }
}
