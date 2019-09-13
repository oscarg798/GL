package com.reyesmagos.gl.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reyesmagos.gl.core.entities.ViewPost
import com.reyesmagos.gl.dashboard.R

class PostRecyclerViewAdapter(private val posts: ArrayList<ViewPost> = ArrayList()) :
    RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun add(posts: List<ViewPost>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun addAtTop(post: ViewPost) {
        posts.add(FIRST_ELEMENT_POSITION, post)
        notifyDataSetChanged()
    }

    companion object {
        private const val FIRST_ELEMENT_POSITION = 0
    }
}
