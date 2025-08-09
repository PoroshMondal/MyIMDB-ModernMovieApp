package com.ifarm.porosh.myimdb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifarm.porosh.myimdb.databinding.FragmentWishListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishList : Fragment() {

    private lateinit var binding: FragmentWishListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}