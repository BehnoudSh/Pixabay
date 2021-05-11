package ir.behnoudsh.pixabay.ui.views

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.behnoudsh.pixabay.BuildConfig
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.databinding.ActivityMainBinding
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem
import ir.behnoudsh.pixabay.ui.adapters.CellClickListener
import ir.behnoudsh.pixabay.ui.adapters.ImagesAdapter
import ir.behnoudsh.pixabay.ui.adapters.TagClickListener
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModel
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity :
    AppCompatActivity(),
    CellClickListener,
    TagClickListener {

    private lateinit var databinding: ActivityMainBinding

    @Inject
    private lateinit var imagesViewModel: ImagesViewModel

    @Inject
    val imagesAdapter: ImagesAdapter = ImagesAdapter(this, ArrayList(), this, this)
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
        et_searchword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                page = 1
                imagesViewModel.getAllImages(et_searchword.text.toString(), page)
                true
            } else {
                false
            }
        }
    }

    fun initRecyclerView() {


        rv_imagesList.layoutManager = LinearLayoutManager(this)
        rv_imagesList.adapter = imagesAdapter
        rv_imagesList.setItemViewCacheSize(100);
        rv_imagesList.setDrawingCacheEnabled(true);
        rv_imagesList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
            pb_loading.visibility = View.GONE
            for (item in it) {
                imagesAdapter.imagesList.add(item)
            }
            imagesAdapter.notifyDataSetChanged()
            if (it.size == 0 && page == 1) {
                ll_noResults.visibility = View.VISIBLE
            } else
                ll_noResults.visibility = View.GONE
        })
        imagesViewModel.imagesFailureLiveData.observe(this, {
            isLoading = false
            pb_loading.visibility = View.GONE

            onSNACK(content, "Error: check internet connection and try again!")
        })
        imagesViewModel.showloadingLiveData?.observe(this, {
            pb_loading.visibility = View.VISIBLE
        })
        imagesViewModel.resetPage?.observe(this, {
            page = 1
            imagesAdapter.imagesList.clear()
        })
    }

    override fun onCellClickListener(image: PixabayImageItem) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setMessage("Do you want to see the details?")// for Message
        alertDialog.setPositiveButton("Yes") { dialog, id ->
            val dialogFragment = ImageDetailsDialog(image)
            dialogFragment.show(supportFragmentManager, "imageDetails")
        }
        alertDialog.setNegativeButton("Cancel") { dialog, id ->
        }
        val alert = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    fun onSNACK(view: View, message: String) {
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("retry") {
            imagesViewModel.getAllImages(et_searchword.text.toString(), page)
        }
        val snackbarView = snackbar.view
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textSize = 14f
        snackbar.show()
    }

    override fun onTagClickListener(tag: String) {
        et_searchword.setText(tag)
        imagesViewModel.getAllImages(tag, 1)
    }
}