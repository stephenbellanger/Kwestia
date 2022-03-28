package com.sbellanger.data.mapper

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Repository
import com.sbellanger.arch.network.model.RawRepository
import com.sbellanger.arch.network.model.RawRepositoryItem
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

class TransformRawRepositoryToDaoUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var transformRawRepositoryToDaoUseCase: TransformRawRepositoryToDaoUseCase

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
        transformRawRepositoryToDaoUseCase =
            toothPickRule.getInstance(TransformRawRepositoryToDaoUseCase::class.java)
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
    fun testTransformRawRepositoryToDaoUseCase() {
        // GIVEN
        val raw = RawRepository(
            items = listOf(
                RawRepositoryItem(
                    id = 1,
                    name = "name",
                    fullName = "kotlin/kotlin",
                    description = "description",
                    openIssuesCount = 4,
                    hasIssues = false,
                    language = "Kotlin"
                ),
                RawRepositoryItem(
                    id = 2,
                    name = "name",
                    fullName = "kotlin/kotlin",
                    description = "description",
                    openIssuesCount = 4,
                    hasIssues = false,
                    language = "Kotlin"
                ),
                RawRepositoryItem(
                    id = 3,
                    name = "name",
                    fullName = "kotlin/kotlin",
                    description = "description",
                    openIssuesCount = 4,
                    hasIssues = false,
                    language = "Kotlin"
                )
            )
        )

        // WHEN
        val result = transformRawRepositoryToDaoUseCase
            .execute(raw)

        // THEN
        val expectedResult = listOf(
            Repository(
                id = 1,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            ),
            Repository(
                id = 2,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            ),
            Repository(
                id = 3,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )
        assertEquals(expectedResult, result)
    }
}
