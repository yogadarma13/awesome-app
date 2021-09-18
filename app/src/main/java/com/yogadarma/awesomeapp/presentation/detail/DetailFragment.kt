package com.yogadarma.awesomeapp.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yogadarma.awesomeapp.databinding.FragmentDetailBinding
import com.yogadarma.awesomeapp.presentation.base.BaseFragment
import com.yogadarma.awesomeapp.utils.showToast
import com.yogadarma.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onView() {
        val photoId = args.photoId

        getPhotoDetail(photoId)
    }

    private fun getPhotoDetail(photoId: Int) {
        detailViewModel.getPhotoDetail(photoId).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> { }
                is Resource.Success -> Log.d("DATAAA", response.data.toString())
                is Resource.Error -> showToast(response.message)
            }
        })
    }
}