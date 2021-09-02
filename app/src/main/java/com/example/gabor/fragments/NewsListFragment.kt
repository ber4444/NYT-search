package com.example.gabor.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gabor.R
import com.example.gabor.adapter.NewsListAdapter
import com.example.gabor.api.NetworkCallOutput
import com.example.gabor.databinding.FragmentNewsListBinding
import com.example.gabor.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.gabor.utils.Helpers.openWebsite

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewsListBinding.bind(view)

        val newsAdapter = NewsListAdapter()
        newsAdapter.onItemClick = {
            it.url?.let { url -> openWebsite(this.requireContext(), url) }
            //findNavController().navigate(
            //    NewsListFragmentDirections.listToDetail(it.title.orEmpty(), it.thumbnailUrl.orEmpty()))
        }

        binding.apply {
            recyclerView.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                ))
            }

            searchPhrase.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.pullArticles(searchPhrase.text.toString())
                    val imm: InputMethodManager = view.context
                        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                    return@setOnEditorActionListener true
                }
                false
            }
        }

        viewModel.data.observe(viewLifecycleOwner, {
            when (it) {
                is NetworkCallOutput.Loading -> binding.progressBar.visibility = View.VISIBLE
                is NetworkCallOutput.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("Search api error", it.error)
                    Snackbar.make(binding.recyclerView, it.error, Snackbar.LENGTH_LONG).show()
                }
                is NetworkCallOutput.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.submitList(it.data)
                }
            }
        })
    }
}