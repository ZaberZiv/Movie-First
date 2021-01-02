package com.zivapp.kotlinmoviefirst.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zivapp.kotlinmoviefirst.adapters.MovieAdapter
import com.zivapp.kotlinmoviefirst.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), View.OnClickListener {

    private val TAG = "SearchFragment"
    private val viewModel: SearchViewModel by activityViewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMovieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.fragSearchButton.setOnClickListener(this)
        updateUI()
        viewModel.findMovie("terminator")

        return view
    }

    override fun onClick(v: View?) {
        val title = binding.fragSearchEditField.text.toString().trim()
        viewModel.findMovie(title)
    }

    private fun updateUI() {
        viewModel.mutableData.observe(viewLifecycleOwner, {
            createMovieRecycler(binding.fragSearchRecycler)
            mMovieAdapter.movieInfoList = it
        })
    }

    private fun createMovieRecycler(recycler: RecyclerView) {
        mMovieAdapter = MovieAdapter()
        recycler.layoutManager = setGridLayout()
        recycler.adapter = mMovieAdapter
    }

    private fun setGridLayout(): GridLayoutManager {
        return GridLayoutManager(
            activity,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
    }
}