package com.example.bookxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), BookListFragment.OnBookSelectedListener {
    private var currentBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, BookListFragment())
                .commit()
        }
    }

    override fun onBookSelected(book: Book) {
        currentBook = book
        val detailFragment = BookDetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    fun getCurrentBook(): Book? = currentBook

    fun updateBook(book: Book) {
        currentBook = book
    }
}