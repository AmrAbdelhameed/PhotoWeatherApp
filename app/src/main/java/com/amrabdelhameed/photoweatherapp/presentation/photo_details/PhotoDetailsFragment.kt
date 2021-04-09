package com.amrabdelhameed.photoweatherapp.presentation.photo_details

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.amrabdelhameed.photoweatherapp.BR
import com.amrabdelhameed.photoweatherapp.R
import com.amrabdelhameed.photoweatherapp.databinding.FragmentPhotoDetailsBinding
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseFragment
import com.amrabdelhameed.photoweatherapp.utils.AppConstants
import com.amrabdelhameed.photoweatherapp.utils.shareClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoDetailsFragment : BaseFragment<FragmentPhotoDetailsBinding, PhotoDetailsViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_photo_details
    override val viewModel: PhotoDetailsViewModel by viewModel()
    private lateinit var photoDataItem: PhotoDataItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            photoDataItem = arguments?.getParcelable(AppConstants.PHOTO_DATA_ITEM)!!
            viewModel.photoPath.value = photoDataItem.path
        }

        getViewDataBinding().share.setOnClickListener {
            activity?.let { it1 -> photoDataItem.path?.let { it2 -> shareClick(it1, it2) } }
        }

        getViewDataBinding().delete.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.are_you_sure))
            builder.setPositiveButton(R.string.yes) { _, _ -> delete() }
            builder.setNegativeButton(R.string.no) { _, _ -> }
            builder.show()
        }
    }

    private fun delete() {
        photoDataItem.path?.let { it1 ->
            Photo(
                it1,
                photoDataItem.id
            )
        }?.let { it2 ->
            viewModel.deletePhoto(
                it2
            )
        }
    }
}