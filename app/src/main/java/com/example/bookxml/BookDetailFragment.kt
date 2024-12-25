package com.example.bookxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bookxml.MainActivity
import com.example.bookxml.R

class BookDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = (activity as? MainActivity)?.getCurrentBook()

        book?.let {
            view.findViewById<TextView>(R.id.titleText).text = it.title
            view.findViewById<TextView>(R.id.authorText).text = it.author
            view.findViewById<TextView>(R.id.descriptionText).text = it.description
        }

        view.findViewById<Button>(R.id.editButton).setOnClickListener {
            val editFragment = BookEditFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, editFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}