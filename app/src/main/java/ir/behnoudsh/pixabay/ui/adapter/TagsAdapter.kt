package ir.behnoudsh.pixabay.ui.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.behnoudsh.pixabay.R


class TagsAdapter(
    val context: Activity,
    var tagsList: ArrayList<String>,
    val tagClickListener: TagClickListener
) :
    RecyclerView.Adapter<TagsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagsViewHolder {

        return TagsViewHolder(context.layoutInflater.inflate(R.layout.item_tags, parent, false))

    }

    override fun getItemCount(): Int {
        return tagsList.size
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {

        holder.tv_tag.setText(tagsList.get(position).trim())
        holder.itemView.setOnClickListener {
            tagClickListener.onTagClickListener(tagsList.get(position))
        }
    }

}

class TagsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tv_tag: TextView = view.findViewById(R.id.tv_tag)

}

interface TagClickListener {
    fun onTagClickListener(tag: String)
}


