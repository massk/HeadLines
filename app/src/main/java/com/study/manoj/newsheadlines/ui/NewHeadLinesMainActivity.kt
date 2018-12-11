package com.study.manoj.newsheadlines.ui

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_new_head_lines_main.*
import timber.log.Timber
import javax.inject.Inject
import com.study.manoj.newsheadlines.R
import com.study.manoj.newsheadlines.dataAgent.DataManager
import com.study.manoj.newsheadlines.network.model.Article
import com.study.manoj.newsheadlines.network.model.Headlines
import com.study.manoj.newsheadlines.utils.ViewModelFactory


class NewHeadLinesMainActivity : AppCompatActivity(), NewsAdapter.Callback {
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var customTabsIntent: CustomTabsIntent
    @Inject
    lateinit var newsAdapter: NewsAdapter
    @Inject
    lateinit var dataAgent = DataManager

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_head_lines_main)

        newsViewModel = ViewModelProviders.of(this,
                ViewModelFactory.getInstance(
                        application,
                        dataAgent)
        ).get(NewsViewModel::class.java)
        newsAdapter.setCallback(this)
        init()
    }

    private fun init() {

        setSupportActionBar(toolbar as Toolbar?)

        api_attribution_1.setHtml("Powered By <a href=\"https://newsapi.org\" title=\"News API\">News API</a>")

        refresh_layout.setOnRefreshListener {
            newsViewModel.getArticlesFromNetwork()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Headlines?> {
                        override fun onComplete() {
                            refresh_layout.isRefreshing = false
                        }

                        override fun onSubscribe(d: Disposable) {
                            refresh_layout.isRefreshing = true
                            compositeDisposable.add(d)
                        }

                        override fun onNext(t: Headlines) {
                            newsAdapter.addItems(t.articles as MutableList<Article>?)
                        }

                        override fun onError(e: Throwable) {
                            refresh_layout.isRefreshing = false
                            Timber.e(e)
                        }
                    })
        }

        article_list.layoutManager = linearLayoutManager
        article_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        article_list.adapter = newsAdapter

        getArticles()

    }

    private fun getArticles() {
        Observable.concat(
                newsViewModel.getArticlesFromDatabase(),
                newsViewModel.getArticlesFromNetwork()
        )
                .observeOn(AndroidSchedulers.mainThread(), true)
                .subscribe(object : Observer<Headlines?> {
                    override fun onComplete() {
                        refresh_layout.isRefreshing = false
                    }

                    override fun onSubscribe(d: Disposable) {
                        refresh_layout.isRefreshing = true
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: Headlines) {
                        newsAdapter.addItems(t.articles as MutableList<Article>?)
                    }

                    override fun onError(e: Throwable) {
                        refresh_layout.isRefreshing = false
                        Timber.e(e)
                    }
                })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_new_head_lines_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onArticleClick(url: String) {
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
