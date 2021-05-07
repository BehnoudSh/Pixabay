package ir.behnoudsh.pixabay.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem
import ir.behnoudsh.pixabay.ui.adapters.CellClickListener
import ir.behnoudsh.pixabay.ui.adapters.ImagesAdapter
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CellClickListener {
    private lateinit var imagesViewModel: ImagesViewModel
    val imagesAdapter = ImagesAdapter(this, ArrayList(), this)
    var isLoading = false
    var page: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        registerObservers()
        initRecyclerView()

        imagesViewModel.getAllImages("fruits", page)

        iv_search.setOnClickListener {

            imagesViewModel.getAllImages(et_searchword.text.toString(), page)

        }
    }

    fun initRecyclerView() {
        rv_imagesList.layoutManager = LinearLayoutManager(this)
        rv_imagesList.adapter = imagesAdapter
//        initScrollListener()
    }

//    private fun initScrollListener() {
//        rv_imagesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
//                if (!isLoading) {
//                    if (linearLayoutManager != null &&
//                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == rv_imagesList.adapter!!.itemCount - 1
//                    ) {
//                      //  placesViewModel.loadMore()
//                        isLoading = true
//                    }
//                }
//            }
//        })
//    }

    fun registerObservers() {
        imagesViewModel.imagesSuccessLiveData.observe(this, {
            if (page == 1)
                imagesAdapter.imagesList.clear()
            for (item in it) {
                imagesAdapter.imagesList.add(item)
            }
            imagesAdapter.notifyDataSetChanged()
            pb_loading.visibility = View.GONE
        })
        imagesViewModel.imagesFailureLiveData.observe(this, {
            pb_loading.visibility = View.GONE
        })
        imagesViewModel.loadingLiveData?.observe(this, {
            pb_loading.visibility = View.VISIBLE
        })
    }

    override fun onCellClickListener(image: PixabayImageItem) {


        val dialogFragment = ImageDetailsDialog(image)
        dialogFragment.show(supportFragmentManager, "imageDetails")

    }

}