package com.sbellanger.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.data.IRepositoryRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class RemoveRepositoryUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var removeRepositoryUseCase: RemoveRepositoryUseCase

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
        removeRepositoryUseCase =
            toothPickRule.getInstance(RemoveRepositoryUseCase::class.java)
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
    fun testRemoveRepositoryUseCaseNominalCase() {
        // GIVEN
        val id = 1

        every {
            repository.removeRepository(any())
        } returns Completable.complete()

        // WHEN
        val result = removeRepositoryUseCase
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
            repository.removeRepository(any())
        }
    }

    @Test
    fun testRemoveRepositoryUseCaseErrorCase() {
        // GIVEN
        val id = 1

        every {
            repository.removeRepository(any())
        } returns Completable.error(Error("some error"))

        // WHEN
        val result = removeRepositoryUseCase
            .execute(id)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertTerminated()
            assertErrorMessage("some error")
        }

        verify(exactly = 1) {
            repository.removeRepository(any())
        }
    }
}
