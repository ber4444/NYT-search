package com.example.gabor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.repository.NewsRepository
import com.example.gabor.viewmodel.MainActivityViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val testCoroutineRule = CoroutinesRule()
    }
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: NewsRepository
    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        repository = FakeNewsRepository()
        viewModel = MainActivityViewModel(repository)
    }

    @Test
    fun testViewModel() = testCoroutineRule.runBlockingTest {
        viewModel.pullArticles("blah")
        val r = viewModel.data.value
        assertTrue(r is NetworkCallOutput.Success)
        assertTrue((r as NetworkCallOutput.Success).data.size == 10)
    }
}