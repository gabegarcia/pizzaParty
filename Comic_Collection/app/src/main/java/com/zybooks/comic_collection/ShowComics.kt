package com.zybooks.comic_collection

import android.content.Context
import android.icu.text.Collator.getInstance
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment



class ShowComics : Fragment(R.layout.fragment_comic_) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       // return inflater.inflate(R.layout.fragment_comic_, container, false)

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_comic_, container, false)

        //define a list of comics
        val bandList = ComicCollection.getInstance(requireContext()).comicList

        //fetch TextView Id
        val textView: TextView
        textView = view.findViewById(R.id.item_list2)
        textView.text = bandList.toString()

        return view


    }


}