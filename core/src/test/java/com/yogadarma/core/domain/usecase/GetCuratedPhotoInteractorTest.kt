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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetCuratedPhotoInteractorTest {

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

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var appRepository: AppRepository

    private lateinit var curatedPhotoInteractor: GetCuratedPhotoInteractor

    @Before
    fun setUp() {
        curatedPhotoInteractor = GetCuratedPhotoInteractor((appRepository))
    }

    @Test
    fun curatedInteractor_GetCuratedPhoto_ReturnPass() {
        Mockito.`when`(appRepository.getCuratedPhoto()).thenReturn(
            Flowable.just(
                Resource.Success(
                    dummyPhoto
                )
            )
        )
        val result = curatedPhotoInteractor.getCuratedPhoto()

        val testSubscriber = TestSubscriber<Resource<List<Photo>>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        val actualResult = testSubscriber.values()[0]
        assertEquals(dummyPhoto, actualResult.data)
        assertEquals(dummyPhoto.size, actualResult.data?.size)
        assertEquals(dummyPhoto[0].id, actualResult.data?.get(0)?.id)
        assertEquals(dummyPhoto[0].photographer, actualResult.data?.get(0)?.photographer)
    }
}