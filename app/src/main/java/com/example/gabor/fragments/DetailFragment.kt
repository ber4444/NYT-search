package com.example.gabor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.gabor.R
import com.example.gabor.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : DialogFragment() {
    private lateinit var text: String
    private lateinit var image: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            text = args.articleText
            image = args.imageUrl
        }
        return inflater.inflate(
            R.layout.detail_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailFragmentBinding.bind(view)

        Glide.with(binding.imageViewDetail)
            .load(image)
            .into(binding.imageViewDetail)
        binding.textViewTitleDetail.text = text
    }
}