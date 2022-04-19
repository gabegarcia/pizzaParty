package com.zybooks.comic_collection

import android.content.Context
import java.io.*
import java.util.*

const val FILENAME = "MyComicCollection.txt"

class ComicCollection () {

   // var comic: NewComic = myNewComic

    private var comicList: MutableList<NewComic> = mutableListOf()

    fun addItem(myNewComic: NewComic) {
        comicList.add(myNewComic)
    }

    fun getItems(): List<NewComic> {
        return Collections.unmodifiableList(comicList)
    }

    fun clear() {
        comicList.clear()
    }

    fun saveToFile(ctx: Context) {
        val OStream: FileOutputStream
        // Write list to file in internal storage
        //val outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE)
        OStream = ctx.openFileOutput(FILENAME,  Context.MODE_PRIVATE)
        writeListToStream(OStream)
    }

    /*fun readFromFile() {
        try {
            // Read in list from file in internal storage
            val inputStream: FileInputStream = comic.openFileInput(FILENAME)
            val reader = inputStream.bufferedReader()
            comicList.clear()
            reader.forEachLine { comicList.add(ComicCollection(newTitle, series)) }
        } catch (ex: FileNotFoundException) {
            // Ignore
        }
    }*/

    private fun writeListToStream(outputStream: FileOutputStream) {
        val writer = PrintWriter(outputStream)
        for (item in comicList) {
            writer.println(item)
        }
        writer.close()
    }
}


