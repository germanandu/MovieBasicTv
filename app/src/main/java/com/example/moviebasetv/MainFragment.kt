package com.example.moviebasetv

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter

class MainFragment: BrowseSupportFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = getString(R.string.browse)

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        (1..5).forEach{ categoryId ->
            val categoryTitle = "Category $categoryId"

            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            listRowAdapter.addAll(0, (1..10).map {"Title $it"})

            val header = HeaderItem(categoryId.toLong(), categoryTitle)
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }

        adapter = rowsAdapter
    }
}

class CardPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        var cardView = ImageCardView(parent?.context)
        cardView.isFocusable =true
        cardView.isFocusableInTouchMode = true

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val title = item as String
        val cardView = viewHolder?.view as ImageCardView

        cardView.titleText = title
        cardView.setMainImageDimensions(176,313)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("Not yet implemented")
    }

}