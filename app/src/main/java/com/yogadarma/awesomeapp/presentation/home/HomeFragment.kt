package com.yogadarma.awesomeapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogadarma.awesomeapp.databinding.FragmentHomeBinding
import com.yogadarma.awesomeapp.utils.gone
import com.yogadarma.awesomeapp.utils.showToast
import com.yogadarma.awesomeapp.utils.visible
import com.yogadarma.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoAdapter = PhotoAdapter()
        with(binding?.rvPhoto) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = photoAdapter
        }
        getCuratedPhoto()
    }

    private fun getCuratedPhoto() {
        homeViewModel.getCuratedPhoto().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> binding?.progressbar?.visible()
                is Resource.Success -> {
                    binding?.progressbar?.gone()
                    photoAdapter.setData(response.data)
                }
                is Resource.Error -> {
                    binding?.progressbar?.gone()
                    showToast(response.message)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}