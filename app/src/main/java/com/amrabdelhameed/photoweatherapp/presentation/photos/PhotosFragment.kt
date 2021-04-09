package com.amrabdelhameed.photoweatherapp.presentation.photos

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.amrabdelhameed.photoweatherapp.BR
import com.amrabdelhameed.photoweatherapp.R
import com.amrabdelhameed.photoweatherapp.databinding.FragmentPhotosBinding
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo
import com.amrabdelhameed.photoweatherapp.presentation.MainActivity
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseFragment
import com.amrabdelhameed.photoweatherapp.presentation.base.NavigationCommand
import com.amrabdelhameed.photoweatherapp.presentation.photo_details.PhotoDataItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>(),
    PhotosAdapterListener {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_photos
    override val viewModel: PhotosViewModel by viewModel()
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var choosePhotoHelper: ChoosePhotoHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(getViewDataBinding().toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        photosAdapter = PhotosAdapter(arrayListOf(), this)
        getViewDataBinding().photosRecyclerView.adapter = photosAdapter

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asBitmap()
            .withState(savedInstanceState)
            .build { bitmap ->
                navigate(NavigationCommand.To(PhotosFragmentDirections.toFragmentAddPhoto(bitmap!!)))
            }

        getViewDataBinding().fab.setOnClickListener { choosePhotoHelper.showChooser() }
    }

    override fun onItemClick(item: Photo) {
        navigate(
            NavigationCommand.To(
                PhotosFragmentDirections.toFragmentPhotoDetails(
                    PhotoDataItem(
                        item.id,
                        item.path
                    )
                )
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        choosePhotoHelper.onSaveInstanceState(outState)
    }
}