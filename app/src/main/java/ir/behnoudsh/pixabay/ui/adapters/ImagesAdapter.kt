package ir.behnoudsh.pixabay.ui.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.domain.model.PixabayImageItem

class ImagesAdapter(
    val context: Activity,
    var imagesList: ArrayList<PixabayImageItem>,
    val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<PlacesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlacesViewHolder {

        return PlacesViewHolder(context.layoutInflater.inflate(R.layout.item_image, parent, false))

    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        holder.tv_tags.setText(imagesList.get(position).tags)
        holder.tv_user.setText(imagesList.get(position).user)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(imagesList.get(position))
        }

        Glide.with(context).load(imagesList.get(position).webformatURL).into(holder.iv_previewImg);
    }

}

class PlacesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tv_user: TextView = view.findViewById(R.id.tv_user)
    val tv_tags: TextView = view.findViewById(R.id.tv_tags)
    val iv_previewImg: ImageView = view.findViewById(R.id.iv_previewImg)

}

interface CellClickListener {
    fun onCellClickListener(place: PixabayImageItem)
}


