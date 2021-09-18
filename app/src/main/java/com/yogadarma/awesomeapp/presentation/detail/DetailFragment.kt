package com.yogadarma.awesomeapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.yogadarma.awesomeapp.databinding.FragmentDetailBinding
import com.yogadarma.awesomeapp.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val args: DetailFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onView() {
        val photoId = args.photoId

    }
}