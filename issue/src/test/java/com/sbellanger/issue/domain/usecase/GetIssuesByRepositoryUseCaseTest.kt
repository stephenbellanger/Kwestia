package com.sbellanger.issue.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Issue
import com.sbellanger.issue.data.IIssueRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class GetIssuesByRepositoryUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getIssuesByRepositoryUseCase: GetIssuesByRepositoryUseCase

    @MockK
    @field:MockK
    lateinit var repository: IIssueRepository

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
        getIssuesByRepositoryUseCase =
            toothPickRule.getInstance(GetIssuesByRepositoryUseCase::class.java)
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
    fun testGetIssuesByRepositoryUseCaseNominalCase() {
        // GIVEN
        val repositoryName = "flutter"
        val issueNameFilter = "issue"

        every {
            repository.getIssues(any(), any())
        } returns Single.just(
            listOf(
                Issue(
                    id = 1,
                    name = "title",
                    number = 4,
                    description = "description",
                    openedBy = "toto"
                )
            )
        )

        // WHEN
        val result = getIssuesByRepositoryUseCase
            .execute(repositoryName, issueNameFilter)
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
            repository.getIssues(any(), any())
        }
    }

    @Test
    fun testGetIssuesByRepositoryUseCaseErrorCase() {
        // GIVEN
        val repositoryName = "flutter"
        val issueNameFilter = "issue"

        every {
            repository.getIssues(any(), any())
        } returns Single.error(Error("some error"))

        // WHEN
        val result = getIssuesByRepositoryUseCase
            .execute(repositoryName, issueNameFilter)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertErrorMessage("some error")
            assertError { it is Error }
            assertTerminated()
        }

        verify(exactly = 1) {
            repository.getIssues(any(), any())
        }
    }
}
