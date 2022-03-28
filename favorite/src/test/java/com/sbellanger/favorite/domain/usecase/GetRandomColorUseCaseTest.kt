package com.sbellanger.favorite.domain.usecase

import android.graphics.Color
import androidx.annotation.CallSuper
import io.mockk.*
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import toothpick.Toothpick
import toothpick.testing.ToothPickRule
import java.util.*

class GetRandomColorUseCaseTest {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var getRandomColorUseCase: GetRandomColorUseCase

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

        mockkStatic(Color::class)

        getRandomColorUseCase =
            toothPickRule.getInstance(GetRandomColorUseCase::class.java)
    }

    @After
    fun afterEachTest() {
        Toothpick.reset()
        unmockkStatic(Color::class)
        clearAllMocks()
        unmockkAll()
    }

    ///////////////////////////////////////////////////////////////////////////
    // TECHNICAL CASE
    ///////////////////////////////////////////////////////////////////////////

    @Test
    fun testGetRandomColorUseCase() {
        // GIVEN
        val random = mockk<Random>()
        every {
            random.nextInt(any())
        } returns 0

        every {
            Color.argb(any<Int>(), any(), any(), any())
        } returns 123456

        // WHEN
        val result = getRandomColorUseCase
            .execute()

        // THEN
        assertEquals(123456, result)
    }
}
