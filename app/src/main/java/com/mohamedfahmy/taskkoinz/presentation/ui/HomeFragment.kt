package com.mohamedfahmy.taskkoinz.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohamedfahmy.taskkoinz.R
import com.mohamedfahmy.taskkoinz.data.api.SessionManager
import com.mohamedfahmy.taskkoinz.data.model.Photo
import com.mohamedfahmy.taskkoinz.databinding.FragmentHomeBinding
import com.tayyar.delivery.data.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sessionManager: SessionManager

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var checkNetworkAvailable: CheckNetworkAvailable

    @Inject
    lateinit var adapterValue: CustomHolderHome

    var checkDoOneTime: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        sessionManager = SessionManager(requireContext())

        initRecyclerView()

        // if condition for if back from full screen put checkDoOneTime = false until not get items automatic
        if (viewModel.items.value != null) {
            checkDoOneTime = false
        }
        viewModel.items.observe(viewLifecycleOwner) { response ->
            // if condition for if back from full screen not get items automatic
            if (checkDoOneTime) {
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            try {

                                Log.i("success", "Photos********* $it")
                                viewModel.numPageViewModel.postValue(
                                    sessionManager.getInt("page").toString()
                                )

                                adapterValue.setData(it)


                            } catch (e: Exception) {
                                Log.i("errorCatch", "Photos********* ${e.message}")
                            }
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let {
                            Toast.makeText(
                                context,
                                "An error occurred Photos  $it",
                                Toast.LENGTH_SHORT
                            ).show()

                            Log.i("error", "Photos********* $it")
                        }
                    }
                    is Resource.Loading -> {
                        Log.i("Loading", "Photos*********")
                    }
                }
            }
        }

    }

    private fun initRecyclerView() {
        binding.recItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterValue

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1) && dy > 0) {
                        if (checkNetworkAvailable.isNetworkAvailable()) {
                            checkDoOneTime = true

                            val map = HashMap<String, String>()
                            Log.i(
                                "numPageViewModel2",
                                "*********** ${viewModel.numPageViewModel.value!!}"
                            )
                            map["page"] = viewModel.numPageViewModel.value!!
                            map["method"] = "flickr.photos.search"
                            map["format"] = "json"
                            map["nojsoncallback"] = "50"
                            map["text"] = "Color"
                            map["per_page"] = "20"
                            map["api_key"] = "d17378e37e555ebef55ab86c4180e8dc"
                            viewModel.getItemsFromApi(map)
                        } else {
                            Toast.makeText(requireContext(), "no more data", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })
        }

        adapterValue.setOnItemClickListener { item: Photo ->
            val bundle =
                bundleOf("url" to "https://farm${item.farm}.static.flickr.com/${item.server}/${item.id}_${item.secret}.jpg")

            Navigation.findNavController(requireView())
                .navigate(R.id.action_homeFragment_to_fullScreenFragment, bundle)
        }


        displayItems()

    }

    private fun displayItems() {
        if (viewModel.items.value == null) {
            checkDoOneTime = true
            val map = HashMap<String, String>()

            if (sessionManager.getInt("page")!! > 0)
                map["page"] = sessionManager.getInt("page").toString()
            else
                map["page"] = viewModel.numPageViewModel.value!!

            map["method"] = "flickr.photos.search"
            map["format"] = "json"
            map["nojsoncallback"] = "50"
            map["text"] = "Color"
            map["per_page"] = "20"
            map["api_key"] = "d17378e37e555ebef55ab86c4180e8dc"
            viewModel.getItemsFromApi(map)
        }
    }
}