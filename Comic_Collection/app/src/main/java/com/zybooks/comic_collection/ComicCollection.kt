package com.zybooks.comic_collection

import android.content.Context
import java.io.*
import java.util.*

/**
 * global variable
 */
const val FILENAME = "MyComicCollection.txt"

/**
 * ComicCollection class
 */
class ComicCollection () {

    /**
     * list of type NewComic
     */

    private var comicList: MutableList<NewComic> = mutableListOf()

    /**
     * addItem function
     * add a NewComic to comicList
     */
    fun addItem(myNewComic: NewComic) {
        comicList.add(myNewComic)
    }

    /**
     * getter
     */
    fun getItems(): List<NewComic> {
        return Collections.unmodifiableList(comicList)
    }

    /**
     * override toString()
     */
    override fun toString(): String {
        return super.toString()
    }

    /**
     * clear function
     * clear comicList
     */
    fun clear() {
        comicList.clear()
    }

    /**
     * saveToFile function
     * attempting to store comicList in a text file
     */
    fun saveToFile(ctx: Context) {
        val OStream: FileOutputStream
        // Write list to file in internal storage
        //val outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE)
        OStream = ctx.openFileOutput(FILENAME,  Context.MODE_PRIVATE)
        writeListToStream(OStream)
    }

    /**
     * readFromFile function
     * attempting to read from local file
     */
    /*fun readFromFile() {
        try {
            // Read in list from file in internal storage
            val inputStream: FileInputStream = comicList.openFileInput(FILENAME)
            val reader = inputStream.bufferedReader()
            comicList.clear()
            reader.forEachLine { comicList.add(ComicCollection(newTitle, series)) }
        } catch (ex: FileNotFoundException) {
            // Ignore
        }
    }*/

    /**
     * writeListToStream function
     * attempting to print data from file
     */
    private fun writeListToStream(outputStream: FileOutputStream) {
        val writer = PrintWriter(outputStream)
        for (item in comicList) {
            writer.println(item)
        }
        writer.close()
    }
}


