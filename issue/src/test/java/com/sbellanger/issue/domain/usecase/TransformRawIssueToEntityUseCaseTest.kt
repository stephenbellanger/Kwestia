package com.sbellanger.issue.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Issue
import com.sbellanger.issue.domain.model.IssueEntity
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class TransformRawIssueToEntityUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var transformRawIssueToEntityUseCase: TransformRawIssueToEntityUseCase

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
        transformRawIssueToEntityUseCase =
            toothPickRule.getInstance(TransformRawIssueToEntityUseCase::class.java)
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
    fun testTransformRawIssueToEntityUseCase() {
        // GIVEN
        val raw = listOf(
            Issue(
                id = 1,
                number = 4,
                name = "name",
                description = "description",
                openedBy = "toto"
            )
        )

        // WHEN
        val result = transformRawIssueToEntityUseCase
            .execute(raw)

        // THEN
        val expectedResult = listOf(
            IssueEntity(
                name = "name",
                number = "#4",
                description = "description",
                openedBy = "Opened by toto"
            )
        )
        assertEquals(expectedResult, result)
    }
}
