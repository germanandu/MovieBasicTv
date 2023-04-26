package com.example.moviebasetv

import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.lifecycleScope
import com.example.moviebasetv.ui.data.server.RemoteConection
import com.example.moviebasetv.ui.data.server.toDomainMovie
import com.example.moviebasetv.ui.main.CardPresenter
import kotlinx.coroutines.launch
class MainFragment: BrowseSupportFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = getString(R.string.browse)

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        viewLifecycleOwner.lifecycleScope.launch {

            val movies = RemoteConection.service
                .listPopularMovies(getString(R.string.apy_key))
                .results.map { it.toDomainMovie() }

            (1..5).forEach{ categoryId ->
                val categoryTitle = "Category $categoryId"

                val listRowAdapter = ArrayObjectAdapter(CardPresenter())
                listRowAdapter.addAll(0, movies)

                val header = HeaderItem(categoryId.toLong(), categoryTitle)
                rowsAdapter.add(ListRow(header, listRowAdapter))
            }

            adapter = rowsAdapter

        }
    }
}

