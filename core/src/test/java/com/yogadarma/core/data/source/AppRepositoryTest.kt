package com.yogadarma.core.data.source

import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.local.entity.PhotoEntity
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.utils.RxImmediateSchedulerRule
import io.reactivex.rxjava3.core.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class AppRepositoryTest {

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

    private val localDataSource = mock(LocalDataSource::class.java)

    private val remoteDataSource = mock(RemoteDataSource::class.java)

    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {

        appRepository = AppRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun appRepository_GetCuratedPhoto_ReturnPass() {
        `when`(localDataSource.getAllPhoto()).thenReturn(Flowable.just(dummyPhotoEntity))

        appRepository.getCuratedPhoto()

        verify(localDataSource).getAllPhoto()
    }
}