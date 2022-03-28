package com.sbellanger.domain.usecase

import androidx.annotation.CallSuper
import androidx.room.EmptyResultSetException
import com.sbellanger.data.IRepositoryRepository
import com.sbellanger.domain.model.RepositoryEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class AddRepositoryUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var addRepositoryUseCase: AddRepositoryUseCase

    @MockK
    @field:MockK
    lateinit var repository: IRepositoryRepository

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
        addRepositoryUseCase =
            toothPickRule.getInstance(AddRepositoryUseCase::class.java)
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
    fun testAddRepositoryUseCaseNominalCase() {
        // GIVEN
        val repository = RepositoryEntity(
            name = "name",
            id = 1,
            description = "description",
            isFavorite = false,
            language = "Kotlin",
            openIssuesCount = 4,
            repositoryName = "kotlin/kotlin"
        )

        every {
            this@AddRepositoryUseCaseTest.repository.addRepository(any())
        } returns Completable.complete()

        // WHEN
        val result = addRepositoryUseCase
            .execute(repository)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertNoValues()
        }

        verify(exactly = 1) {
            this@AddRepositoryUseCaseTest.repository.addRepository(any())
        }
    }

    @Test
    fun testAddRepositoryUseCaseErrorCase() {
        // GIVEN
        val repository = RepositoryEntity(
            name = "name",
            id = 1,
            description = "description",
            isFavorite = false,
            language = "Kotlin",
            openIssuesCount = 4,
            repositoryName = "kotlin/kotlin"
        )

        every {
            this@AddRepositoryUseCaseTest.repository.addRepository(any())
        } returns Completable.error(EmptyResultSetException("some error"))

        // WHEN
        val result = addRepositoryUseCase
            .execute(repository)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertNoValues()
            assertErrorMessage("some error")
        }

        verify(exactly = 1) {
            this@AddRepositoryUseCaseTest.repository.addRepository(any())
        }
    }
}
