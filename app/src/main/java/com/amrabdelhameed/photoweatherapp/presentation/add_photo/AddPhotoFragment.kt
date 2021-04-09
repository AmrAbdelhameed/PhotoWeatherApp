package com.amrabdelhameed.photoweatherapp.presentation.add_photo

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.view.View
import com.amrabdelhameed.photoweatherapp.BR
import com.amrabdelhameed.photoweatherapp.R
import com.amrabdelhameed.photoweatherapp.databinding.FragmentAddPhotoBinding
import com.amrabdelhameed.photoweatherapp.presentation.base.BaseFragment
import com.amrabdelhameed.photoweatherapp.utils.AppConstants
import com.amrabdelhameed.photoweatherapp.utils.getAddress
import com.amrabdelhameed.photoweatherapp.utils.getFileName
import com.example.easywaylocation.EasyWayLocation
import com.example.easywaylocation.Listener
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.nmd.screenshot.Screenshot
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPhotoFragment : BaseFragment<FragmentAddPhotoBinding, AddPhotoViewModel>(), Listener {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_add_photo
    override val viewModel: AddPhotoViewModel by viewModel()
    private lateinit var easyWayLocation: EasyWayLocation
    lateinit var screenshot: Screenshot

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val bitmap = arguments?.getParcelable<Bitmap>(AppConstants.BITMAP)
            getViewDataBinding().imageView.setImageBitmap(bitmap)
        }

        easyWayLocation = EasyWayLocation(activity, false, this)
        Dexter.withContext(activity)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    easyWayLocation.startLocation()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {}

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                }
            }).check()

        screenshot = Screenshot(activity)
        screenshot.fileName = getFileName()
        screenshot.setCallback { success, filePath, bitmap ->
            if (success) {
                viewModel.insertPhoto(filePath)
            }
        }
        getViewDataBinding().fab.setOnClickListener {
            getViewDataBinding().fab.visibility = View.GONE
            screenshot.takeScreenshot()
        }
    }

    override fun locationOn() {}

    override fun currentLocation(location: Location?) {
        viewModel.address = location?.latitude?.let {
            getAddress(
                activity,
                it, location.longitude
            )
        }.toString()
        location?.latitude?.let { viewModel.getCurrentWeather(it, location.longitude) }
        easyWayLocation.endUpdates()
    }

    override fun locationCancelled() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EasyWayLocation.LOCATION_SETTING_REQUEST_CODE) {
            easyWayLocation.onActivityResult(resultCode)
        }
    }
}