package ir.behnoudsh.pixabay.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.ui.viewmodels.ImagesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var imagesViewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagesViewModel = ViewModelProviders.of(this).get(ImagesViewModel::class.java)
        registerObservers()

        imagesViewModel.getAllImages("fruits", 1)
    }

    fun registerObservers() {
        imagesViewModel.imagesSuccessLiveData.observe(this, {
            for (item in it) {

                val a: Int = 10;

            }
            //    placesAdapter.placesList.add(item)

//            placesAdapter.notifyDataSetChanged()

        })
        imagesViewModel.imagesFailureLiveData.observe(this, {
//            pb_loading.visibility = View.GONE
//            if (it)
//                btn_loadmore.visibility = View.VISIBLE
//            else
//                btn_loadmore.visibility = View.GONE
        })
        imagesViewModel.loadingLiveData?.observe(this, {
            pb_loading.visibility = View.VISIBLE
        })

    }

}