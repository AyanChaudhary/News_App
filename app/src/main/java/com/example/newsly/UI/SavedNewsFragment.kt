package com.example.newsly.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly.Adapter.NewsAdapter
import com.example.newsly.MainActivity
import com.example.newsly.R
import com.example.newsly.databinding.FragmentSavedNewsBinding
import com.example.newsly.databinding.FragmentSearchNewsBinding
import com.google.android.material.snackbar.Snackbar


class SavedNewsFragment: Fragment(R.layout.fragment_saved_news) {
    lateinit var viewModel:NewsViewModel
    lateinit var binding: FragmentSavedNewsBinding
    lateinit var newsAdapter: NewsAdapter
    var TAG="searchNews"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSavedNewsBinding.inflate(layoutInflater,container,false)
        viewModel=(activity as MainActivity).viewModel

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_articleFragment,bundle)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                    Snackbar.make(view!!,"Article deleted successfully",Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            viewModel.saveArticle(article)
                        }
                    }.show()

            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }


        viewModel.getArticles().observe(viewLifecycleOwner, Observer {articles->
            newsAdapter.differ.submitList(articles)
        })
        return binding.root
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility= View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility= View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter= NewsAdapter()
        binding.rvSavedNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }
}