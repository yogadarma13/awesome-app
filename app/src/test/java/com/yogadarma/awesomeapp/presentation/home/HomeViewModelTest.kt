package com.yogadarma.awesomeapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.usecase.GetCuratedPhotoInteractor
import io.reactivex.rxjava3.core.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private val dummyPhoto = listOf(
        Photo(
            1,
            "",
            "",
            "Yoga"
        ),
        Photo(
            2,
            "",
            "",
            "Darma"
        )
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var curatedPhotoInteractor: GetCuratedPhotoInteractor

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(curatedPhotoInteractor)
    }

    @Test
    fun homeViewModel_GetCuratedPhoto_ReturnPass() {
        `when`(curatedPhotoInteractor.getCuratedPhoto()).thenReturn(
            Flowable.just(
                Resource.Success(
                    dummyPhoto
                )
            )
        )
        homeViewModel.getCuratedPhoto().value

        verify(curatedPhotoInteractor).getCuratedPhoto()
    }
}