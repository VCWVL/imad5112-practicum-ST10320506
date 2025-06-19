package vcmsa.ci.musicplaylistmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Declare FOUR Parallel Arrays to store song details once added
    val songTitle = ArrayList<String>()
    val artistName = ArrayList<String>()
    val songRating = ArrayList<Int>()
    val userComments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// end of ViewCompat

        //Student Number: ST10320506 and Full Name: Tamara Jada Kaligan

        //Declaring GUI variables in Main Screen
        val btnAddSong = findViewById<Button>(R.id.btnAddSong)
        val btnNextScreen = findViewById<Button>(R.id.btnNextScreen)
        val btnExitApp = findViewById<Button>(R.id.btnExitApp)

        //Button functions called and declared using on click listener
        //Button 1: Add Song button, when clicked it opens up a layout for the user to enter song details and add it to a list
        btnAddSong.setOnClickListener {
            //Display an addDialogBox once clicked to open a dialog box for users to enter song details
            showAddDialogBox()
        }

        //Button 2: Next Screen button, when clicked allows the user to navigate to the second screen
        btnNextScreen.setOnClickListener {
            //Intent used for navigation to the next page
            val intent = Intent(this, DetailedViewScreen::class.java)
            //Declare the Parallel Arrays that will be passed to the Detailed View Screen
            intent.putStringArrayListExtra("Song Title", songTitle)
            intent.putStringArrayListExtra("Artist's Name", artistName)
            intent.putIntegerArrayListExtra("Rating (1-5)", ArrayList(songRating))
            intent.putStringArrayListExtra("User Comments", userComments)
            startActivity(intent)
        }

        //Button 3: Exit App button, when clicked allows the user to exit the app
        btnExitApp.setOnClickListener {
            finish()
        }

    }// end of onCreate

    //Method/function for the Add Song button (user's enter song details in here)
    private fun showAddDialogBox() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Song Details")
        val layout = layoutInflater.inflate(R.layout.dialog_layout, null)
        builder.setView(layout)

        //Declare the GUI variables of the dialog layout box
        val edtSongTitle = layout.findViewById<android.widget.EditText>(R.id.edtSongTitle)
        val edtArtistName = layout.findViewById<android.widget.EditText>(R.id.edtArtistName)
        val edtComments = layout.findViewById<android.widget.EditText>(R.id.edtComments)
        val edtRating = layout.findViewById<android.widget.EditText>(R.id.edtRating)

        //Adding a positive button function to the dialog box so when clicked it adds the song details to the list
        builder.setPositiveButton("Add") { _, _ ->
            val song = edtSongTitle.text.toString()
            val artist = edtArtistName.text.toString()
            val rating = edtRating.text.toString().toInt()
            val comments = edtComments.text.toString()

            //IF statement to check if any of the input boxes are empty
            //If song is empty OR artist is empty OR comments are empty then a toast message is displayed
            if (song.isEmpty() || artist.isEmpty() || comments.isEmpty() ) {
                //Toast message is displayed to prompt the user to enter details in all the fields
                Toast.makeText(this, "Please enter details in all fields.", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            //Arrays are added with the details user has added to the list
            songTitle.add(song)
            artistName.add(artist)
            songRating.add(rating)
            userComments.add(comments)
            //Toast message is displayed to show the user that song details were added successfully
            Toast.makeText(this, "Song Details added successfully!", Toast.LENGTH_SHORT).show()
        }
        //Adding a negative button function to the dialog box so when clicked it closes the dialog box and nothing is added to list
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }// end of showAddDialogBox
}// end of MainActivity