package com.example.myapplication44.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication44.R
import com.example.myapplication44.adapter.UserAdapter
import com.example.myapplication44.databinding.FragmentMainBinding
import com.example.myapplication44.pojo.User
import com.example.myapplication44.util.State
import com.example.myapplication44.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var listModel = mutableListOf<User>()
    private val viewModel: UserViewModel by viewModels()
    private var mAdapter: UserAdapter? = null
    private val userId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        setupRecyclerView()
        getData()
        observeState()

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun setupRecyclerView() {
        val mAdapter = UserAdapter()
        binding.rvShowData.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())

        }
        val album = view?.findViewById<Button>(R.id.album)
        album?.setOnClickListener {
                var action =MainFragmentDirections.actionMainFragmentToDetailFragment(userId!!)
                findNavController().navigate(action)
        }

    }

    private fun getData() {
        viewModel.getUser()
    }
    private fun getAlbom(userId: Int) {
        viewModel.getAlbum(userId)
    }
    private fun observeState() {
        viewModel.mState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Success -> {
                    mAdapter?.setList(listModel)

                }
            }
        })
    }

}


