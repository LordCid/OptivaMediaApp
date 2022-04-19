package com.example.omapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.omapp.common.DataResponse
import com.example.omapp.domain.GetMoviesUseCase
import com.example.omapp.movie
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

import org.junit.Assert.*

class MovieListViewModelTest {

    private lateinit var sut: MovieListViewModel

    private val observer = mockk<Observer<MovieListViewState>>(relaxed = true)
    private val getMoviesUseCase = mockk<GetMoviesUseCase>()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val captor = mutableListOf<MovieListViewState>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        sut = MovieListViewModel(getMoviesUseCase, dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `WHEN get Movies THEN Loading ViewState`() {
        val page = 1

        sut.viewState.observeForever(observer)
        sut.getMovies(page)


        verify { observer.onChanged(capture(captor)) }
        assertTrue(captor[0] is MovieListViewState.Loading)
    }

    @Test
    fun `GIVEN movies from Use case WHEN get Movies THEN Show Data ViewState`() {
        val page = 1
        val expected = listOf(movie)
        coEvery { getMoviesUseCase.invoke(page) } returns DataResponse.Success(expected)

        sut.viewState.observeForever(observer)
        sut.getMovies(page)


        coVerify { getMoviesUseCase.invoke(page) }
        verify { observer.onChanged(capture(captor)) }
        assertTrue(captor[1] is MovieListViewState.ShowMovies)
        val viewState = captor[1] as MovieListViewState.ShowMovies
        assertEquals(expected, viewState.data)
    }

    @Test
    fun `GIVEN failure WHEN get Movies THEN Error ViewState`() {
        val page = 1
        coEvery { getMoviesUseCase.invoke(page) } returns DataResponse.Failure

        sut.viewState.observeForever(observer)
        sut.getMovies(page)


        coVerify { getMoviesUseCase.invoke(page) }
        verify { observer.onChanged(capture(captor)) }
        assertTrue(captor[1] is MovieListViewState.Error)
    }
}