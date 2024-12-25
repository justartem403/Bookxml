package com.example.bookxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.bookxml.MainActivity
import com.example.bookxml.R

class BookEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = (activity as? MainActivity)?.getCurrentBook()

        val titleEdit = view.findViewById<EditText>(R.id.titleEdit)
        val authorEdit = view.findViewById<EditText>(R.id.authorEdit)
        val descriptionEdit = view.findViewById<EditText>(R.id.descriptionEdit)

        book?.let {
            titleEdit.setText(it.title)
            authorEdit.setText(it.author)
            descriptionEdit.setText(it.description)
        }

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            book?.let {
                it.title = titleEdit.text.toString()
                it.author = authorEdit.text.toString()
                it.description = descriptionEdit.text.toString()
                (activity as? MainActivity)?.updateBook(it)
                parentFragmentManager.popBackStack()
            }
        }
    }
}