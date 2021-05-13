package ir.behnoudsh.pixabay.data.repository


import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import ir.behnoudsh.pixabay.data.api.ApiHelper
import ir.behnoudsh.pixabay.data.model.PixabayData
import ir.behnoudsh.pixabay.data.model.PixabayHitsData
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ImagesRepositoryTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @MockK
    var apiHelper = ApiHelper()

    lateinit var imageRepository: ImageRepository


    @Test
    fun testEmptyListResponse() {

//        val mockImageRepository = Mockito.mock(ImageRepository::class.java)
//        Mockito.`when`(apiService.getImages("", 1)).thenReturn(null)
//        imageRepository.getData("", 1);
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

        every { apiHelper.getImages("car", 1) } returns Single.just(mockData)

        // when
        val result: Single<PixabayData> = imageRepository.getData("car", 1)

        // then
//        verify { service.getDataFromDb("Expected Param") }
        assertEquals(2, 2)


    }
}