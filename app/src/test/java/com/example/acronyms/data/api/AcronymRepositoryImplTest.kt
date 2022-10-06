package com.example.acronyms.data.api

import com.example.acronyms.hilt.Module
import com.example.acronyms.utils.ApiState
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AcronymRepositoryImplTest {

    private lateinit var classUnderTest: AcronymRepositoryImpl
    private var module = Module()

    @Before
    fun setUp() {
        classUnderTest = AcronymRepositoryImpl(module.provideApiService())
    }

    @After
    fun tearDown() {}

    @Test
    fun check_when_input_is_empty() {
        runTest(StandardTestDispatcher()) {
            val actual = classUnderTest.fetchAbbreviation("")
            Assert.assertEquals(ApiState.Error::class, actual.last()::class)
        }
    }

    @Test
    fun check_when_input_is_single_character() {
        runTest(StandardTestDispatcher()) {
            val actual = classUnderTest.fetchAbbreviation("a")
            Assert.assertEquals(ApiState.Error::class, actual.last()::class)
        }
    }

    @Test
    fun check_when_input_is_acronym() {
        runTest(StandardTestDispatcher()) {
            val actual = classUnderTest.fetchAbbreviation("hm")
            Assert.assertEquals(ApiState.Success::class, actual.last()::class)
        }
    }
}
