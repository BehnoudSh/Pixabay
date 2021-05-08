package ir.behnoudsh.pixabay.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem
import kotlinx.android.synthetic.main.dialog_image_details.*

class ImageDetailsDialog(var model: PixabayImageItem) :
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


        Glide.with(view).load(model.largeImageURL).into(iv_image)

        tv_user.text = model.user

        tv_tags.text = model.tags

        tv_comments.text = model.comments.toString()

        tv_favorites.text = model.favorites.toString()

        tv_likes.text = model.likes.toString()


    }

}