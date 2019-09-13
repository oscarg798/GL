package com.reyesmagos.gl.dashboard.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reyesmagos.gl.core.entities.ViewPost
import com.reyesmagos.gl.dashboard.R
import kotlinx.android.synthetic.main.activity_dashboard.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvName = itemView.findViewById<TextView>(R.id.tvName)
    private val tvDate =  itemView.findViewById<TextView>(R.id.tvDate)
    private val tvBody =  itemView.findViewById<TextView>(R.id.tvBody)

    fun bind(post: ViewPost) {
        tvName?.text = "${post.firstName} ${post.lastName}"
        tvDate?.text = post.date
        tvBody?.text = post.postBody
    }
}
