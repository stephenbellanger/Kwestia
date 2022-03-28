package com.sbellanger.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Repository
import com.sbellanger.domain.model.RepositoryEntity
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class TransformRawRepositoryToEntityUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var transformRawRepositoryToEntityUseCase: TransformRawRepositoryToEntityUseCase

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
        transformRawRepositoryToEntityUseCase =
            toothPickRule.getInstance(TransformRawRepositoryToEntityUseCase::class.java)
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
    fun testTransformRawRepositoryToEntityUseCaseHasFavorite() {
        // GIVEN
        val raw = listOf(
            Repository(
                id = 1,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )

        val favoriteRepositories = listOf(
            Repository(
                id = 1,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )

        // WHEN
        val result = transformRawRepositoryToEntityUseCase
            .execute(raw, favoriteRepositories)

        // THEN
        val expectedResult = listOf(
            RepositoryEntity(
                name = "name",
                id = 1,
                description = "description",
                isFavorite = true,
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )
        assertEquals(expectedResult, result)
    }

    @Test
    fun testTransformRawRepositoryToEntityUseCaseNoFavorite() {
        // GIVEN
        val raw = listOf(
            Repository(
                id = 1,
                name = "name",
                description = "description",
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )

        val favoriteRepositories = emptyList<Repository>()

        // WHEN
        val result = transformRawRepositoryToEntityUseCase
            .execute(raw, favoriteRepositories)

        // THEN
        val expectedResult = listOf(
            RepositoryEntity(
                name = "name",
                id = 1,
                description = "description",
                isFavorite = false,
                language = "Kotlin",
                openIssuesCount = 4,
                repositoryName = "kotlin/kotlin"
            )
        )
        assertEquals(expectedResult, result)
    }
}
