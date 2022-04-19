package com.zybooks.comic_collection

//import ComicCollection
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var newComic = NewComic()
    private var newComicCollection = ComicCollection()
    //private lateinit var itemEditText: EditText
    private lateinit var comicTitle: EditText
    private lateinit var comicSeries: EditText
    private lateinit var listTextView: TextView
    private var series: Int  = 0
    lateinit var title: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        comicTitle = findViewById(R.id.comic_title)
        comicSeries = findViewById(R.id.series)
        listTextView = findViewById(R.id.item_list)
        findViewById<Button>(R.id.add_button).setOnClickListener { addButtonClick() }
        findViewById<Button>(R.id.clear_button).setOnClickListener { clearButtonClick() }
    }

   /* override fun onResume() {
        super.onResume()

        // Attempt to load a previously saved list
        newComicCollection.readFromFile()
        displayList()
    }*/

    override fun onPause() {
        super.onPause()

        // Save list for later
        newComicCollection.saveToFile(this)
    }

    private fun addButtonClick() {

        // Ignore any leading or trailing spaces
        //val item = itemEditText.text.toString().trim()

        title = comicTitle.text.toString()
        series = comicSeries.text.toString().toInt()

        newComic = NewComic(title, series)

        // Clear the EditText so it's ready for another item
        comicTitle.setText("")
        comicSeries.setText("")

        // Add the item to the list and display it
        if (newComic.title != "No Title" && newComic.newSeries != 0) {
                newComicCollection.addItem(newComic)
            displayList()
        }
    }

    private fun displayList() {

        // Display a numbered list of items
        val itemText = StringBuffer()
        val items = newComicCollection.getItems()
        val lineSeparator = System.getProperty("line.separator")

        for (i in items.indices) {
            itemText.append(i + 1).append(". ").append(items[i]).append(lineSeparator)
        }

        listTextView.text = itemText.toString()
    }

    private fun clearButtonClick() {
        newComicCollection.clear()
        displayList()
    }
}