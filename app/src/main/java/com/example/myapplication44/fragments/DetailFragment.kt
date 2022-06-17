package com.example.myapplication44.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication44.R
import com.example.myapplication44.adapter.PhotoAdapter
import com.example.myapplication44.adapter.UserAdapter
import com.example.myapplication44.databinding.FragmentDetailBinding
import com.example.myapplication44.pojo.Photo
import com.example.myapplication44.pojo.User
import com.example.myapplication44.util.State
import com.example.myapplication44.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!
    private val viewModel: UserViewModel by viewModels()
    private var queryText = ""
    private var mAdapter: PhotoAdapter? = null
    private var listModel = mutableListOf<Photo>()
    private val albumId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        observeState()
        setupRecyclerView()
        getPhotos(albumId!!)
        binding.searchKeyword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                queryText = binding.searchKeyword.text.toString().trim().toLowerCase()
                mAdapter?.setList(listModel)

                true
            } else {
                false
            }
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        val mAdapter = UserAdapter()
        binding.photoData.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())

        }
    }

    private fun getPhotos(albumId: Int) {
        viewModel.getPhoto(albumId)
    }

    private fun observeState() {
        viewModel.pState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Success -> {
                    mAdapter?.setList(listModel)

                }
            }
        })
    }

}