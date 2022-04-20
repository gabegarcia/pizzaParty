package com.zybooks.comic_collection

import android.content.Context
import android.widget.TextView
import java.io.*

class NewComic(var newTitle: String) {

    var title: String = newTitle
        set(value){
            field = value
        }

    override fun toString(): String {
        return this.title
    }


}