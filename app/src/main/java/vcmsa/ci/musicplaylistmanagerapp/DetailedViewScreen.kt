package vcmsa.ci.musicplaylistmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// end of ViewCompat

        //Declaring GUI variables for the Detailed View Screen
        val tvDisplayAll = findViewById<TextView>(R.id.tvDisplayAll)
        val btnList = findViewById<Button>(R.id.btnList)
        val btnCalculateAvg = findViewById<Button>(R.id.btnCalculateAvg)
        val btnBackScreen = findViewById<Button>(R.id.btnBackScreen)

        //Passing Parallel Arrays through intents to the Detailed View screen
        val songTitle = intent.getStringArrayListExtra("Song Title") ?: arrayListOf()
        val artistName = intent.getStringArrayListExtra("Artist Name") ?: arrayListOf()
        val songRating = intent.getStringArrayListExtra("Rating (1-5)") ?: arrayListOf()
        val userComments = intent.getStringArrayListExtra("User Comments") ?: arrayListOf()

        //method/function created for the Display List button
        fun displayAll() {
            val output = StringBuilder()
            //Table headers with fixed-width columns
            output.append(String.format("%-5s%-5s%-5s%-5s%-20s\n", "Song Title", "Artist Name", "Rating (1-5)", "Comments"))
            output.append("----\n")

            //for loop created to display all the song list details added by the user
            for (i in songTitle.indices) {
                output.append(
                    String.format(
                        "%-5s%-15s%-15s%-10s%-20s\n",
                        i + 1,
                        songTitle[i],
                        artistName[i],
                        songRating[i],
                        userComments[i]
                    )
                )

            }
            tvDisplayAll.text = output.toString()
        }

        fun calculateAverage() {
            
        }

        //Display All button function called using on click listener
        btnList.setOnClickListener {
            displayAll()
        }

        //Calculate Average button function called
        btnCalculateAvg.setOnClickListener {
            //Calculate Average rating for songs in playlist  - use loop

        }

        //Back Screen button function called to return to Main Screen
        btnBackScreen.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }// end of onCreate
}// end of DetailedViewScreen