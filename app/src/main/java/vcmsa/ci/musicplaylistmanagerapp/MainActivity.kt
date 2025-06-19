package vcmsa.ci.musicplaylistmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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

        //Button functions declared using on click listener
        //Button 1: Add Song button, when clicked it opens up a layout for the user to enter song details and add it to a list
        btnAddSong.setOnClickListener {

        }

        //Button 2: Next Screen button, when clicked allows the user to navigate to the second screen
        btnNextScreen.setOnClickListener {
            //Intent used for navigation to the next page
            val intent = Intent(this, DetailedViewScreen::class.java)
            startActivity(intent)
        }

        //Button 3: Exit App button, when clicked allows the user to exit the app
        btnExitApp.setOnClickListener {
            finish()
        }

    }// end of onCreate
}// end of MainActivity