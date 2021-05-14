package ir.behnoudsh.pixabay.ui.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.data.model.PixabayHitsData
import ir.behnoudsh.pixabay.databinding.ActivityMainBinding
import ir.behnoudsh.pixabay.di.component.DaggerViewModelComponent
import ir.behnoudsh.pixabay.di.component.ViewModelComponent
import ir.behnoudsh.pixabay.ui.adapter.CellClickListener
import ir.behnoudsh.pixabay.ui.adapter.ImagesAdapter
import ir.behnoudsh.pixabay.ui.adapter.TagClickListener
import ir.behnoudsh.pixabay.ui.viewmodel.MainViewModel
import ir.behnoudsh.pixabay.ui.viewmodel.ViewModelFactory
import ir.behnoudsh.pixabay.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity :
    AppCompatActivity(),
    CellClickListener,
    TagClickListener {

    private lateinit var databinding: ActivityMainBinding
    private lateinit var adapter: ImagesAdapter
    private var page: Int = 1
    private var isLoading = false

    @Inject
    lateinit var mainViewModel: MainViewModel

    init {
        val viewModelComponent: ViewModelComponent = DaggerViewModelComponent.create()
        viewModelComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        databinding.imagesViewModel = mainViewModel
        databinding.lifecycleOwner = this
        setupUI()
        setupViewModel()
        setupObservers()
        mainViewModel.fetchImages("fruits", page)
        et_searchword.setText("fruits")
        et_searchword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                page = 1
                mainViewModel.fetchImages(et_searchword.text.toString(), page)
                true
            } else {
                false
            }
        }
    }

    private fun renderList(images: List<PixabayHitsData>) {
        for (item in images) {
            adapter.imagesList.add(item)
        }
        adapter.notifyDataSetChanged()
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImagesAdapter(this, ArrayList(), this, this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        initScrollListener()
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory()
        mainViewModel = ViewModelProviders.of(this, factory)
            .get(MainViewModel::class.java)
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.adapter!!.itemCount - 5
                    ) {
                        page++
                        mainViewModel.fetchImages(et_searchword.text.toString(), page)
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun setupObservers() {
        mainViewModel.getImages().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    isLoading = false
                    progressBar.visibility = View.GONE
                    it.data?.let { pixabayData -> renderList(pixabayData.hits) }
                    if (it.data?.hits?.size == 0 && page == 1) {
                        ll_noResults.visibility = View.VISIBLE
                    } else
                        ll_noResults.visibility = View.GONE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    isLoading = false
                    progressBar.visibility = View.GONE
                    onSNACK(content, it.message.toString())
                }
            }
        })
        mainViewModel.getPageStatus().observe(this, {
            page = 1
            adapter.imagesList.clear()

        })
    }

    override fun onCellClickListener(image: PixabayHitsData) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setMessage("Do you want to see the details?")
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

    private fun onSNACK(view: View, message: String) {
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction("retry") {
            mainViewModel.fetchImages(et_searchword.text.toString(), page)
        }
        val snackbarView = snackbar.view
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textSize = 14f
        snackbar.show()
    }

    override fun onTagClickListener(tag: String) {
        et_searchword.setText(tag)
        page = 1
        mainViewModel.fetchImages(tag, page)
    }
}