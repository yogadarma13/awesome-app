package com.yogadarma.core.data.source.remote

import com.yogadarma.core.data.source.remote.network.ApiResponse
import com.yogadarma.core.data.source.remote.network.ApiService
import com.yogadarma.core.data.source.remote.response.CuratedResponse
import com.yogadarma.core.data.source.remote.response.PhotoItem
import com.yogadarma.core.data.source.remote.response.Src
import com.yogadarma.core.utils.RxImmediateSchedulerRule
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/* Jika ingin menjalankan test berikut tolong comment terlebih dahulu kode Idling Resource
pada class RemoteDataSource */
class RemoteDataSourceTest {

    private val dummyCuratedResponse = CuratedResponse(
        "",
        1,
        1,
        listOf()
    )

    private val dummyPhotoItem = PhotoItem(
        Src(),
        100,
        "#000000",
        "Yoga Darma",
        "",
        10,
        "",
        11,
        false,
        100
    )

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()


    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSource(apiService)
    }

    /* Jika ingin menjalankan test berikut tolong comment terlebih dahulu kode Idling Resource
    pada class RemoteDataSource */
    @Test
    fun remoteDataSource_GetCuratedPhoto_ReturnPass() {
        `when`(apiService.getCuratedPhoto()).thenReturn(Flowable.just(dummyCuratedResponse))
        val result = remoteDataSource.getCuratedPhoto()

        val testSubscriber = TestSubscriber<ApiResponse<List<PhotoItem?>?>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
    }

    /* Jika ingin menjalankan test berikut tolong comment terlebih dahulu kode Idling Resource
    pada class RemoteDataSource */
    @Test
    fun remoteDataSource_GetPhotoDetail_ReturnPass() {
        `when`(apiService.getPhotoDetail(dummyPhotoItem.id!!)).thenReturn(Flowable.just(dummyPhotoItem))
        val result = remoteDataSource.getPhotoDetail(dummyPhotoItem.id!!)

        val testSubscriber = TestSubscriber<ApiResponse<PhotoItem?>>()
        result.subscribe(testSubscriber)

        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
    }
}