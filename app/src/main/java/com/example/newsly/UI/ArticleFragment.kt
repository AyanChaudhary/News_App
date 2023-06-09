package com.example.newsly.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsly.MainActivity
import com.example.newsly.R
import com.example.newsly.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar

class ArticleFragment: Fragment(R.layout.fragment_article) {
    lateinit var viewModel:NewsViewModel
    lateinit var binding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentArticleBinding.inflate(layoutInflater,container,false)
        viewModel=(activity as MainActivity).viewModel

        val article=args.article
        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(article.url!!)
        }

        binding.fabFavourite.setOnClickListener {
            viewModel.saveArticle(article)
            view?.let { it1 -> Snackbar.make(it1,"Article saved successfully",Snackbar.LENGTH_SHORT).show() }
        }
        return binding.root
    }
}