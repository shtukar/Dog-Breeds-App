package com.gmail.shtukarrv.dogbreedsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmail.shtukarrv.dogbreedsapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseUnitTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
}