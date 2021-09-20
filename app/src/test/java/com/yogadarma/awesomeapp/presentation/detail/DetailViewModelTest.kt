package com.yogadarma.awesomeapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.domain.usecase.GetDetailInteractor
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
class DetailViewModelTest {

    private val dummyPhoto = Photo(
        1,
        "",
        "",
        "Yoga"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var detailInteractor: GetDetailInteractor

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(detailInteractor)
    }

    @Test
    fun homeViewModel_GetCuratedPhoto_ReturnPass() {
        `when`(detailInteractor.getPhotoDetail(dummyPhoto.id)).thenReturn(
            Flowable.just(
                Resource.Success(
                    dummyPhoto
                )
            )
        )
        detailViewModel.getPhotoDetail(dummyPhoto.id).value

        verify(detailInteractor).getPhotoDetail(dummyPhoto.id)
    }
}