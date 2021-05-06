package ir.behnoudsh.pixabay.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        registerObservers()
        initRecyclerView()

        imagesViewModel.getAllImages("fruits", 1)
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

    override fun onCellClickListener(place: PixabayImageItem) {
    }

}