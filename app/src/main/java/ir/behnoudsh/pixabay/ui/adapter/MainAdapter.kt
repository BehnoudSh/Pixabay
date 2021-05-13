package ir.behnoudsh.pixabay.ui.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.behnoudsh.pixabay.R
import ir.behnoudsh.pixabay.data.model.PixabayHitsData


class ImagesAdapter(
    val context: Activity,
    var imagesList: ArrayList<PixabayHitsData>,
    val cellClickListener: CellClickListener,
    val tagClickListener: TagClickListener
) :
    RecyclerView.Adapter<ImagesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesViewHolder {

        return ImagesViewHolder(context.layoutInflater.inflate(R.layout.item_image, parent, false))

    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {

        holder.tv_user.setText(imagesList.get(position).user)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(imagesList.get(position))
        }

        Picasso.get()
            .load(imagesList.get(position).webformatURL)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fit().centerCrop()
            .into(holder.iv_previewImg)

        Picasso.get()
            .load(imagesList.get(position).userImageURL)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fit().centerCrop()
            .into(holder.iv_userImg)

        val allTags = imagesList.get(position).tags?.split(',')

        val tagsAdapter: TagsAdapter =
            TagsAdapter(context, allTags as ArrayList<String>, tagClickListener)
        holder.rv_tags.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rv_tags.adapter = tagsAdapter

    }

}

class ImagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tv_user: TextView = view.findViewById(R.id.tv_user)
    val iv_previewImg: ImageView = view.findViewById(R.id.iv_previewImg)
    val iv_userImg: ImageView = view.findViewById(R.id.iv_userImg)
    val rv_tags: RecyclerView = view.findViewById(R.id.rv_tags)
}

interface CellClickListener {
    fun onCellClickListener(place: PixabayHitsData)
}


