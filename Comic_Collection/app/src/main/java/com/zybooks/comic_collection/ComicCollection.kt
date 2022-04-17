package com.zybooks.comic_collection

import android.content.Context
import java.io.*
import java.util.*

const val FILENAME = "MyComicCollection.txt"

class ComicCollection (newComic: NewComic) {
    var comic: NewComic = newComic

    private var comicList: MutableList<NewComic> = mutableListOf()

    fun addItem(newComic: NewComic) {
        comicList.add(comic)
    }

    fun getItems(): List<NewComic> {
        return Collections.unmodifiableList(comicList)
    }

    fun clear() {
        comicList.clear()
    }

    fun saveToFile() {

        // Write list to file in internal storage
        val outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE)
        writeListToStream(outputStream)
    }

    fun readFromFile() {
        try {
            // Read in list from file in internal storage
            val inputStream: FileInputStream = context.openFileInput(FILENAME)
            val reader = inputStream.bufferedReader()
            comicList.clear()
            reader.forEachLine { comicList.add(ComicCollection(context, series)) }
        } catch (ex: FileNotFoundException) {
            // Ignore
        }
    }

    private fun writeListToStream(outputStream: FileOutputStream) {
        val writer = PrintWriter(outputStream)
        for (item in comicList) {
            writer.println(item)
        }
        writer.close()
    }
}


