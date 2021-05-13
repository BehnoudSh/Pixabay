package ir.behnoudsh.pixabay.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.data.model.PixabayHitsData
import kotlinx.android.synthetic.main.dialog_image_details.*

class ImageDetailsDialog(var model: PixabayHitsData) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_image_details, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_close.setOnClickListener {
            dismiss()
        }

        Picasso.get()
            .load(model.largeImageURL)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fit().centerInside()
            .into(iv_image)

        tv_user.text = model.user

        tv_tags.text = model.tags

        tv_comments.text = model.comments.toString()

        tv_favorites.text = model.favorites.toString()

        tv_likes.text = model.likes.toString()

    }
}
