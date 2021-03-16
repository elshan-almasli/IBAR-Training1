package com.anushka.newsapiclient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.newsapiclient.data.util.Resource
import com.anushka.newsapiclient.databinding.FragmentNewsBinding
import com.anushka.newsapiclient.presentation.adapter.ExampleLoadStateAdapter
import com.anushka.newsapiclient.presentation.adapter.NewsAdapter
import com.anushka.newsapiclient.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }
        initRecyclerView()
//        viewNewsList()
        getPagingList()
    }

    private fun viewNewsList() {

        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    showProgressBar(false)
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.articles.toList().size}")
//                         newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar(true)
                }

            }
        })
    }

    private fun getPagingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getNewsData(country).collectLatest {
                newsAdapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch{
            newsAdapter.loadStateFlow.collectLatest { loadState ->
                showProgressBar(loadState.refresh is LoadState.Loading)
            }
        }
    }

    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter.withLoadStateFooter(
                footer = ExampleLoadStateAdapter(newsAdapter::retry)
            )
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar(visibility: Boolean) {
        fragmentNewsBinding.progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }


}
















