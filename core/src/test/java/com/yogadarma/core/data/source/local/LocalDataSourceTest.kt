package com.yogadarma.core.data.source.local

import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.local.room.AppDao
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

class LocalDataSourceTest {

    private val dummyPhotoEntity = listOf(
        PhotoEntity(
            1,
            "",
            "",
            "Yoga"
        ),
        PhotoEntity(
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
    private lateinit var appDao: AppDao

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        localDataSource = LocalDataSource(appDao)
    }

    @Test
    fun localDataSource_GetAllPhoto_ReturnPass() {
        `when`(appDao.getAllPhoto()).thenReturn(Flowable.just(dummyPhotoEntity))
        val result = localDataSource.getAllPhoto()

        val testSubscriber = TestSubscriber<List<PhotoEntity>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        testSubscriber.assertValue(dummyPhotoEntity)

        val actualResult = testSubscriber.values()[0]
        assertEquals(dummyPhotoEntity.size, actualResult.size)
        assertEquals(dummyPhotoEntity[0].id, actualResult[0].id)
        assertEquals(dummyPhotoEntity[0].photographer, actualResult[0].photographer)
    }

    @Test
    fun localDataSource_GetPhotoDetail_ReturnPass() {
        `when`(appDao.getPhotoDetail(dummyPhotoEntity[0].id)).thenReturn(
            Flowable.just(
                dummyPhotoEntity[0]
            )
        )
        val result = localDataSource.getPhotoDetail(dummyPhotoEntity[0].id)

        val testSubscriber = TestSubscriber<PhotoEntity>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        testSubscriber.assertValue(dummyPhotoEntity[0])

        val actualResult = testSubscriber.values()[0]
        assertEquals(dummyPhotoEntity[0].id, actualResult.id)
        assertEquals(dummyPhotoEntity[0].photographer, actualResult.photographer)
    }
}