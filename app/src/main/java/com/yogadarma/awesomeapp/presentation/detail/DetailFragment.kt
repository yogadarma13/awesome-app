package com.yogadarma.awesomeapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yogadarma.awesomeapp.databinding.FragmentDetailBinding
import com.yogadarma.awesomeapp.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onView() {

    }
}