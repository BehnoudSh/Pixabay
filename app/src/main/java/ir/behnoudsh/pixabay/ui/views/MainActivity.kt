package ir.behnoudsh.pixabay.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.databinding.ActivityMainBinding
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem
import ir.behnoudsh.pixabay.ui.adapters.CellClickListener
import ir.behnoudsh.pixabay.ui.adapters.ImagesAdapter
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModel
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CellClickListener {

    private lateinit var databinding: ActivityMainBinding

    @Inject
    var imagesViewModel: ImagesViewModel = ImagesViewModel()

    @Inject
    val imagesAdapter: ImagesAdapter = ImagesAdapter(this, ArrayList(), this)
    var page: Int = 1
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val factory = ImagesViewModelFactory()
        imagesViewModel = ViewModelProviders.of(this, factory)
            .get(ImagesViewModel::class.java)

        databinding.imagesViewModel = imagesViewModel
        databinding.lifecycleOwner = this
        registerObservers()
        initRecyclerView()
        imagesViewModel.getAllImages("fruits", page)
    }

    fun initRecyclerView() {
        rv_imagesList.layoutManager = LinearLayoutManager(this)
        rv_imagesList.adapter = imagesAdapter
        initScrollListener()
    }

    private fun initScrollListener() {
        rv_imagesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == rv_imagesList.adapter!!.itemCount - 5
                    ) {
                        page++
                        imagesViewModel.getAllImages(et_searchword.text.toString(), page)
                        isLoading = true
                    }
                }
            }
        })
    }

    fun registerObservers() {
        imagesViewModel.imagesSuccessLiveData.observe(this, {
            isLoading = false
            for (item in it) {
                imagesAdapter.imagesList.add(item)
            }
            imagesAdapter.notifyDataSetChanged()
            pb_loading.visibility = View.GONE
        })
        imagesViewModel.imagesFailureLiveData.observe(this, {
            isLoading = false
            pb_loading.visibility = View.GONE
        })
        imagesViewModel.loadingLiveData?.observe(this, {
            pb_loading.visibility = View.VISIBLE
        })
        imagesViewModel.resetPage?.observe(this, {
            page = 1
            imagesAdapter.imagesList.clear()
        })

    }

    override fun onCellClickListener(image: PixabayImageItem) {
        val dialogFragment = ImageDetailsDialog(image)
        dialogFragment.show(supportFragmentManager, "imageDetails")
    }
}