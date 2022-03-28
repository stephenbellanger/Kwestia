package com.sbellanger.issue.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.issue.domain.model.IssueEntity
import com.sbellanger.issue.presentation.IssueViewState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class GetIssueViewStateUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getIssueViewStateUseCase: GetIssueViewStateUseCase

    @MockK
    @field:MockK
    lateinit var getIssuesByRepositoryUseCase: GetIssuesByRepositoryUseCase

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
        getIssueViewStateUseCase =
            toothPickRule.getInstance(GetIssueViewStateUseCase::class.java)
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
    fun testGetIssueViewStateUseCaseNominalCase() {
        // GIVEN
        val repositoryName = "flutter"
        val issueNameFilter = "issue"

        every {
            getIssuesByRepositoryUseCase.execute(any(), any())
        } returns Single.just(
            listOf(
                IssueEntity(
                    name = "title",
                    number = "4",
                    description = "description",
                    openedBy = "toto"
                )
            )
        )

        // WHEN
        val result = getIssueViewStateUseCase
            .execute(repositoryName, issueNameFilter)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is IssueViewState.Loaded }
        }
    }

    @Test
    fun testGetIssueViewStateUseCaseNoResult() {
        // GIVEN
        val repositoryName = "flutter"
        val issueNameFilter = "issue"

        every {
            getIssuesByRepositoryUseCase.execute(any(), any())
        } returns Single.just(
            emptyList()
        )

        // WHEN
        val result = getIssueViewStateUseCase
            .execute(repositoryName, issueNameFilter)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is IssueViewState.NoIssue }
        }
    }

    @Test
    fun testGetIssueViewStateUseCaseErrorCase() {
        // GIVEN
        val repositoryName = "flutter"
        val issueNameFilter = "issue"

        every {
            getIssuesByRepositoryUseCase.execute(any(), any())
        } returns Single.error(Error("some error"))

        // WHEN
        val result = getIssueViewStateUseCase
            .execute(repositoryName, issueNameFilter)
            .test()

        // THEN
        result?.run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertTerminated()
            assertValue { it is IssueViewState.Error }
        }
    }
}
