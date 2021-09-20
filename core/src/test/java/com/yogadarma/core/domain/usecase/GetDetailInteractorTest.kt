package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.Resource
import com.yogadarma.core.data.source.AppRepository
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.utils.RxImmediateSchedulerRule
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetDetailInteractorTest {

    private val dummyPhoto = Photo(
        1,
        "",
        "",
        "Yoga"
    )

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var appRepository: AppRepository

    private lateinit var detailInteractor: GetDetailInteractor

    @Before
    fun setUp() {
        detailInteractor = GetDetailInteractor((appRepository))
    }

    @Test
    fun detailInteractor_GetPhotoDetail_ReturnPass() {
        `when`(appRepository.getPhotoDetail(dummyPhoto.id)).thenReturn(
            Flowable.just(
                Resource.Success(
                    dummyPhoto
                )
            )
        )
        val result = detailInteractor.getPhotoDetail(dummyPhoto.id)

        val testSubscriber = TestSubscriber<Resource<Photo>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        val actualResult = testSubscriber.values()[0]
        assertEquals(dummyPhoto.id, actualResult.data?.id)
        assertEquals(dummyPhoto.photographer, actualResult.data?.photographer)
    }
}