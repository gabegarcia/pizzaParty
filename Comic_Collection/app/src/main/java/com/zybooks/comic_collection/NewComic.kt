package com.zybooks.comic_collection

import android.content.Context
import android.widget.TextView
import java.io.*

class NewComic(var newTitle: String, var newSeriesNumber: Int, var newIssueNumber: Int) {

    var title: String = newTitle
        set(value){
            field = value
        }

    var seriesNumber: Int = newSeriesNumber
        set(value){
            field = value
        }

    var issueNumber: Int = newIssueNumber
        set(value){
            field = value
        }

    override fun toString(): String {
        return this.title
    }


}