package com.zybooks.comic_collection

//import ComicCollection
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.zybooks.comic_collection.databinding.ActivityMainBinding

/**
 * global variables
 */
private lateinit var newComic: NewComic
private var newComicCollection = ComicCollection()

/**
 * MainActivity class
 */

class MainActivity : AppCompatActivity() {

    /**
     * local variables
     */
    lateinit var binding: ActivityMainBinding
    private lateinit var comicTitle: EditText
    private lateinit var seriesNumber: EditText
    private lateinit var issueNumber: EditText
    private lateinit var listTextView: TextView
    private var series: Int = 0
    lateinit var title: String
    private var issue: Int = 0

    /**
     * onCreate function
     * calls layout.activity_main
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.fragmentButton.setOnClickListener{

            replaceFragment(ShowComics())
        }



        /**
         * instantiate variables from fields
         */
        comicTitle = findViewById(R.id.comic_title)
        seriesNumber = findViewById(R.id.series)
        issueNumber = findViewById(R.id.issue_number)
        listTextView = findViewById(R.id.item_list)

        /**
         * onclick listeners
         */
        findViewById<Button>(R.id.add_button).setOnClickListener { addButtonClick() }
        findViewById<Button>(R.id.display_button).setOnClickListener { displayList() }

    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }
    /**
     * unused function
     */
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

    /**
     * addButtonCLick function
     * convert EditText values to string
     * clear EditText fields
     * activate alert
     */
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
        //newComicCollection.addItem(newComic)

        //if (newComic.title != "No Title" && newComic.newSeries != 0) {
        //         newComicCollection.addItem(newComic)
        //displayList()
        // } else newComicCollection.addItem(NewComic())

        val dialog = Alert1()
        dialog.show(supportFragmentManager, "warningDialog")


    }

     fun addComicToList() {
        newComicCollection.addItem(newComic)
        displayList()
    }

    /**
     * displayList function
     * populate items with objects from newComicCollection
     * iterate through items
     * populate listTextView to display items
     */
    private fun displayList() {

        // Display a numbered list of items
        val itemText = StringBuffer()
        val items = newComicCollection.getItems()
        val lineSeparator = System.getProperty("line.separator")

        for (i in items.indices) {
            itemText.append("Series: ").append(items[i].seriesNumber).append(lineSeparator)
                .append("Title: ").append(items[i].title).append(lineSeparator).append("Issue: ")
                .append(items[i].issueNumber).append(" ").append(lineSeparator)
        }

        listTextView.text = itemText.toString()
    }

    private fun clearButtonClick() {
        //val dialog = Alert1()
        //dialog.show(supportFragmentManager, "warningDialog")

        newComicCollection.clear()
        displayList()
    }
}

/**
 * Alert1 class
 * displays an alert
 * prompts user to confirm adding a new NewComic
 */
class Alert1 : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.warning)
        builder.setMessage(R.string.warning_message)
        builder.setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
            // User clicked OK button
            newComicCollection.addItem(newComic)


        })
        /* builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
         comicTitle.setText("")
         seriesNumber.setText("")
         issueNumber.setText("")
     })*/


        return builder.create()
    }


}


