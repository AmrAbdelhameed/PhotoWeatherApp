package com.amrabdelhameed.photoweatherapp.presentation.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import com.amrabdelhameed.photoweatherapp.databinding.PhotoItemBinding
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseRecyclerViewAdapter
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseViewHolder

class PhotosAdapter(items: MutableList<Photo>, listener: PhotosAdapterListener) :
    BaseRecyclerViewAdapter<Photo>(items, listener) {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class PhotoViewHolder(private val mBinding: PhotoItemBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            val photo = items[position]
            mBinding.photo = photo
            mBinding.item = PhotoItemView(itemListener as PhotosAdapterListener)
            mBinding.executePendingBindings()
        }
    }
}