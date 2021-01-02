package com.zivapp.kotlinmoviefirst.view.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.zivapp.kotlinmoviefirst.R
import com.zivapp.kotlinmoviefirst.databinding.FragmentFavoriteBinding
import com.zivapp.kotlinmoviefirst.databinding.FragmentHomeBinding
import com.zivapp.kotlinmoviefirst.view.home.HomeViewModel

class FavoriteFragment : Fragment() {

    private val TAG = "FavoriteFragment"
    private val viewModel: FavoriteViewModel by activityViewModels()
    private lateinit var mBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = mBinding.root



        return view
    }
}