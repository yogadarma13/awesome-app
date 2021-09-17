package com.yogadarma.awesomeapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yogadarma.awesomeapp.databinding.FragmentHomeBinding
import com.yogadarma.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCuratedPhoto()
    }

    private fun getCuratedPhoto() {
        homeViewModel.getCuratedPhoto().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> Log.d("DATAAAAAA", response.data.toString())
                is Resource.Error -> Toast.makeText(
                    requireContext(),
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}