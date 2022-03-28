package com.sbellanger.favorite.domain.usecase

import androidx.annotation.CallSuper
import com.sbellanger.arch.local.model.Repository
import com.sbellanger.favorite.domain.model.FavoriteEntity
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule

class TransformDaoFavoriteToEntityUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var transformDaoFavoriteToEntityUseCase: TransformDaoFavoriteToEntityUseCase

    @MockK
    @field:MockK
    lateinit var getRandomColorUseCase: GetRandomColorUseCase

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
        transformDaoFavoriteToEntityUseCase =
            toothPickRule.getInstance(TransformDaoFavoriteToEntityUseCase::class.java)
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
    fun testTransformDaoFavoriteToEntityUseCase() {
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

        every {
            getRandomColorUseCase.execute()
        } returns 1234

        // WHEN
        val result = transformDaoFavoriteToEntityUseCase
            .execute(raw)

        // THEN
        val expectedResult = listOf(
            FavoriteEntity(
                id = 1,
                name = "name",
                description = "description",
                language = "Kotlin",
                openedIssues = 4,
                color = 1234,
                repositoryName = "kotlin/kotlin"
            )
        )
        assertEquals(expectedResult, result)
    }
}
