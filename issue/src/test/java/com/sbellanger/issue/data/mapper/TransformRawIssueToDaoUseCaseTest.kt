package com.sbellanger.issue.data.mapper

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Issue
import com.sbellanger.arch.network.model.RawIssue
import com.sbellanger.arch.network.model.RawUserIssue
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class TransformRawIssueToDaoUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var transformRawIssueToDaoUseCase: TransformRawIssueToDaoUseCase

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
        transformRawIssueToDaoUseCase =
            toothPickRule.getInstance(TransformRawIssueToDaoUseCase::class.java)
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
    fun testTransformRawIssueToDaoUseCase() {
        // GIVEN
        val raw = listOf(
            RawIssue(
                id = 1,
                title = "title",
                body = "body",
                user = RawUserIssue(
                    id = 1,
                    login = "toto",
                    pictureProfileUrl = "https://test.png"
                ),
                number = 15
            ),
            RawIssue(
                id = 2,
                title = "title",
                body = "body",
                user = RawUserIssue(
                    id = 1,
                    login = "toto",
                    pictureProfileUrl = "https://test.png"
                ),
                number = 15
            ),
            RawIssue(
                id = 3,
                title = "title",
                body = "body",
                user = RawUserIssue(
                    id = 1,
                    login = "toto",
                    pictureProfileUrl = "https://test.png"
                ),
                number = 15
            )
        )

        // WHEN
        val result = transformRawIssueToDaoUseCase
            .execute(raw)

        // THEN
        val expectedResult = listOf(
            Issue(
                id = 1,
                name = "title",
                number = 15,
                description = "body",
                openedBy = "toto"
            ),
            Issue(
                id = 2,
                name = "title",
                number = 15,
                description = "body",
                openedBy = "toto"
            ),
            Issue(
                id = 3,
                name = "title",
                number = 15,
                description = "body",
                openedBy = "toto"
            )
        )
        assertEquals(expectedResult, result)
    }
}
