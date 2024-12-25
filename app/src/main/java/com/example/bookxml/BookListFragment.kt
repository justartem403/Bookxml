package com.example.bookxml

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class BookListFragment : Fragment() {
    interface OnBookSelectedListener {
        fun onBookSelected(book: Book)
    }

    private var listener: OnBookSelectedListener? = null
    private val books = mutableListOf(
        Book("1984", "Джордж Оруэлл", "Антиутопический роман..."),
        Book("Мастер и Маргарита", "Михаил Булгаков", "Роман о дьяволе...")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.bookListView)
        val emptyView = view.findViewById<TextView>(R.id.emptyView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = BookAdapter(books) { book ->
            listener?.onBookSelected(book)
        }
        recyclerView.adapter = adapter

        // Показываем emptyView, если список пуст
        if (books.isEmpty()) {
            recyclerView.visibility = GONE
            emptyView.visibility = VISIBLE
        } else {
            recyclerView.visibility = VISIBLE
            emptyView.visibility = GONE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnBookSelectedListener
    }
}