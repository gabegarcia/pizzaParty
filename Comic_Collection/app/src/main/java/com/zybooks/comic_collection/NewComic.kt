package com.zybooks.comic_collection

import android.content.Context
import java.io.*

class NewComic(var newTitle: String = "No Title", var newSeries: Int = 0) {

    var title: String = newTitle
        set(value){
            field = value
        }
        get(){
            return title
        }
    var series: Int = newSeries
        set(value){
            field = value
        }
    get(){
        return series
    }


}