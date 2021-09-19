package com.yogadarma.awesomeapp.presentation.home

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.yogadarma.awesomeapp.R
import com.yogadarma.awesomeapp.databinding.FragmentHomeBinding
import com.yogadarma.awesomeapp.presentation.base.BaseFragment
import com.yogadarma.awesomeapp.utils.MarginGridItemDecoration
import com.yogadarma.awesomeapp.utils.gone
import com.yogadarma.awesomeapp.utils.showToast
import com.yogadarma.awesomeapp.utils.visible
import com.yogadarma.core.data.Resource
import com.yogadarma.core.domain.model.Photo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var coverImageAdapter: CoverImageAdapter
    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback

    private val sliderHandler = Handler(Looper.getMainLooper())
    private val sliderRunnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }
    private var listData: List<Photo>? = listOf()
    private var isList: Boolean = true

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onView() {
        setToolbar()
        photoAdapter = PhotoAdapter()

        setListView()
        setCoverImage()
        getCuratedPhoto()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.collapsingToolbarLayout.apply {
            setCollapsedTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setExpandedTitleColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.transparent
                )
            )
            setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.type_item -> {
                    homeViewModel.setListView(!isList)
                    true
                }
                else -> false
            }
        }
    }

    private fun setListView() {
        homeViewModel.isList().observe(viewLifecycleOwner, { isList ->
            this.isList = isList

            val menu = binding.toolbar.menu
            val menuItem = menu?.findItem(R.id.type_item)
            menuItem?.icon = if (isList) ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_grid_view
            ) else ContextCompat.getDrawable(requireContext(), R.drawable.ic_list_view)

            with(binding.rvPhoto) {
                layoutManager =
                    if (isList) LinearLayoutManager(context) else GridLayoutManager(context, 2)

                while (itemDecorationCount > 0) removeItemDecorationAt(0)
                if (!isList) addItemDecoration(
                    MarginGridItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.dimen_8),
                        2
                    )
                )

                setHasFixedSize(true)
                adapter = photoAdapter
            }

            photoAdapter.setData(listData, isList)
        })
    }

    private fun setCoverImage() {
        coverImageAdapter = CoverImageAdapter()
        coverImageAdapter.onItemClick = {

        }

        with(binding.viewPager) {
            adapter = coverImageAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        pageChangeCallback = object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }

        }

        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun getCuratedPhoto() {
        homeViewModel.getCuratedPhoto().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> binding.progressbar.visible()
                is Resource.Success -> {
                    binding.progressbar.gone()

                    response.data?.let { list ->
                        listData = list

                        if (list.size > 6)
                            coverImageAdapter.setData(
                                response.data?.subList(0, 6),
                                binding.viewPager
                            )
                        else if (list.isNotEmpty()) coverImageAdapter.setData(
                            response.data?.subList(0, list.lastIndex),
                            binding.viewPager
                        )
                        photoAdapter.setData(list, isList)
                    }
                }
                is Resource.Error -> {
                    binding.progressbar.gone()
                    showToast(response.message)
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()

        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}