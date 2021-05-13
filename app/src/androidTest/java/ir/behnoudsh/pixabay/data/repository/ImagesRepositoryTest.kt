package ir.behnoudsh.pixabay.data.repository

import io.reactivex.Single
import ir.behnoudsh.pixabay.data.api.ApiHelper
import ir.behnoudsh.pixabay.data.api.ApiService
import ir.behnoudsh.pixabay.data.model.PixabayData
import ir.behnoudsh.pixabay.data.model.PixabayHitsData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryTest : TestCase() {

    lateinit var apiService: ApiService

    lateinit var imageRepository: ImageRepository

    lateinit var apiHelper: ApiHelper


    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        imageRepository = ImageRepository()
        apiHelper = mock(ApiHelper::class.java)
    }

    @Test
    fun testEmptyListResponse() {

        val mockImageRepository = Mockito.mock(ImageRepository::class.java)
        Mockito.`when`(apiService.getImages("", 1)).thenReturn(null)
        imageRepository.getData("", 1);
//        assertEquals(Resource<>, imageRepository.getData("", 1))


//        when(imageRepository.getData("fruits",1))
//        // given
//        when(apiService.syncGenres()).thenReturn(Observable.just(Collections.emptyList());
//        // when
//        splashPresenter.syncGenres();
//        // then
//        verify(... // for example:, verify call to splashView.navigateToHome()
    }

    @Test
    fun testDataListResponse() {

        val pixabayHitData1: PixabayHitsData = PixabayHitsData(
            "", "", "", "tags",
            120, 331, 341, ""
        )


        val pixabayHitData2: PixabayHitsData = PixabayHitsData(
            "", "", "", "tags",
            120, 331, 341, ""
        )


        val mockArrays: ArrayList<PixabayHitsData> = ArrayList<PixabayHitsData>()
        mockArrays.add(pixabayHitData1)
        mockArrays.add(pixabayHitData2)

        val mockData: PixabayData = PixabayData(mockArrays)


        //        Mockito.when(api.syncGenres()).thenReturn(Single.just(mockedGenres));

        Mockito.`when`(apiHelper.getImages("car", 1)).thenReturn(Single.just(mockData))
        imageRepository.getData("car", 1);
        assertEquals(2, mockData.hits.size)

    }
}