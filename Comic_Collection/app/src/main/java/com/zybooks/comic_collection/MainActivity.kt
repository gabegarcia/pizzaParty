package com.zybooks.comic_collection

//import ComicCollection
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var newComic: NewComic
    private var newComicCollection = ComicCollection()
    //private lateinit var itemEditText: EditText
    private lateinit var comicTitle: EditText
    private lateinit var seriesNumber: EditText
    private lateinit var issueNumber: EditText
    private lateinit var listTextView: TextView
    private var series: Int  = 0
    lateinit var title: String
    private  var issue: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        comicTitle = findViewById(R.id.comic_title)
        seriesNumber = findViewById(R.id.series)
        issueNumber = findViewById(R.id.issue_number)
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
       // newComicCollection.saveToFile(this)
    }

    private fun addButtonClick() {

        // Ignore any leading or trailing spaces
        //val item = itemEditText.text.toString().trim()

        title = comicTitle.text.toString()
        series = seriesNumber.text.toString().toInt()
        issue = issueNumber.text.toString().toInt()
        newComic = NewComic(title, series, issue)
        //newComic = NewComic(title, series)

        // Clear the EditText so it's ready for another item
       comicTitle.setText("")
       seriesNumber.setText("")
        issueNumber.setText("")

        // Add the item to the list and display it
        newComicCollection.addItem(newComic)

        //if (newComic.title != "No Title" && newComic.newSeries != 0) {
       //         newComicCollection.addItem(newComic)
            //displayList()
       // } else newComicCollection.addItem(NewComic())


        displayList()
    }

    private fun displayList() {

        // Display a numbered list of items
        val itemText = StringBuffer()
        val items = newComicCollection.getItems()
        val lineSeparator = System.getProperty("line.separator")

        for (i in items.indices) {
            itemText.append("Series: ").append(items[i].seriesNumber).append(lineSeparator).append("Title: ").append(items[i].title).append(lineSeparator).append("Issue: ").append(items[i].issueNumber).append(" ").append(lineSeparator)
        }

        listTextView.text = itemText.toString()
    }

   private fun clearButtonClick() {
        newComicCollection.clear()
        displayList()
    }
}