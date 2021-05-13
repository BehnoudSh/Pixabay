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

    @MockK
    lateinit var apiHelper : ApiHelper

    lateinit var imageRepository: ImageRepository

    @Before
    fun setUp() {
        apiHelper = ApiHelper()
        imageRepository = ImageRepository()
        MockKAnnotations.init(this)
    }


    @Test
    fun testDataListResponse() {

        val pixabayHitData1 = PixabayHitsData(
            "", "", "", "tags",
            120, 331, 341, ""
        )
        val pixabayHitData2 = PixabayHitsData(
            "", "", "", "tags",
            120, 331, 341, ""
        )

        val mockArrays: ArrayList<PixabayHitsData> = ArrayList()
        mockArrays.add(pixabayHitData1)
        mockArrays.add(pixabayHitData2)

        val mockData = PixabayData(mockArrays)

        every { apiHelper.getImages("car", 1) } returns Single.just(mockData)
        val result: Single<PixabayData> = imageRepository.getData("car", 1)
        assertEquals(2, 2)


    }
}