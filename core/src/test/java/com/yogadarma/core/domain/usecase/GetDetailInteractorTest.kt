package com.yogadarma.core.domain.usecase

import com.yogadarma.core.data.Resource
import com.yogadarma.core.data.source.AppRepository
import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.domain.model.Photo
import com.yogadarma.core.utils.RxImmediateSchedulerRule
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetDetailInteractorTest {

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

    private lateinit var detailInteractor: GetDetailInteractor

    @Before
    fun setUp() {
        detailInteractor = GetDetailInteractor((appRepository))
    }

    @Test
    fun detailInteractor_GetPhotoDetail_ReturnPass() {
        `when`(appRepository.getPhotoDetail(dummyPhoto[0].id)).thenReturn(Flowable.just(Resource.Success(dummyPhoto[0])))
        val result = detailInteractor.getPhotoDetail(dummyPhoto[0].id)

        val testSubscriber = TestSubscriber<Resource<Photo>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        val actualResult = testSubscriber.values()[0]
        assertEquals(dummyPhoto[0].id, actualResult.data?.id)
        assertEquals(dummyPhoto[0].photographer, actualResult.data?.photographer)
    }
}