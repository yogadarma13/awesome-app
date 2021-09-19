package com.yogadarma.awesomeapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yogadarma.awesomeapp.R
import com.yogadarma.awesomeapp.databinding.FragmentDetailBinding
import com.yogadarma.awesomeapp.presentation.base.BaseFragment
import com.yogadarma.awesomeapp.utils.gone
import com.yogadarma.awesomeapp.utils.loadImage
import com.yogadarma.awesomeapp.utils.showToast
import com.yogadarma.awesomeapp.utils.visible
import com.yogadarma.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onView() {
        setToolbar()

        val photoId = args.photoId

        getPhotoDetail(photoId)
    }

    private fun setToolbar() {
        binding.appbar.bringToFront()

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_back_button)
    }

    private fun getPhotoDetail(photoId: Int) {
        detailViewModel.getPhotoDetail(photoId).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> binding.progressbar.visible()
                is Resource.Success -> {
                    with(binding) {
                        progressbar.gone()
                        imgPhotoDetail.loadImage(response.data?.portraitPhotoUrl)
                        tvPhotographer.text = response.data?.photographer
                    }
                }
                is Resource.Error -> {
                    binding.progressbar.gone()
                    showToast(response.message)
                }
            }
        })
    }
}