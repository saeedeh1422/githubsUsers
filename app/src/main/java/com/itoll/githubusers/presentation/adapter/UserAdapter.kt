package com.itoll.githubusers.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itoll.githubusers.R
import com.itoll.githubusers.common.ImageLoader
import com.itoll.githubusers.databinding.RowUserBinding
import com.itoll.githubusers.domain.ui_model.User

class UserAdapter(
    private val itemSelect: ((item: User) -> Unit)
) : RecyclerView.Adapter<UserAdapter.VH>() {

    private val mList = mutableListOf<User?>()

    fun addAll(list: List<User?>) {
        mList.clear()
        mList.addAll(list)
        notifyItemRangeChanged(0, mList.size)
    }
    fun clearAddAll(list: List<User?>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)

        view.setOnClickListener {
            view.tag?.let { tag ->
                if (tag is User) {
                    itemSelect(tag)
                }
            }
        }
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        mList[position]?.let { holder.onBind(it) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(user: User) {
            RowUserBinding.bind(itemView).apply {
                userNameTxt.text = user.userName
                ImageLoader.load(
                    root.context,
                    user.avatarUrl,
                    avatarImg,
                    isCircle = true,
                )
                root.tag = user
            }


        }
    }

}