package id.ac.umn.projekpervasive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Firebase.database.reference

        if(false)
        {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
        else
        {
            val dataListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                  val post = dataSnapshot.getValue<Any>()
                    Log.i("Data perangkat", "hasil_tarik_data" + post)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("Data perangkat", "loadPost:onCancelled", databaseError.toException())
                }
            }
            val sdf = SimpleDateFormat("dd-MM-yyyy")
//            val curDate = sdf.format(java.util.Calendar.getInstance().time)
            val curDate = "1-10-2023"
            Log.i("Date", "date: " + curDate)
            database.child("Perangkat")
                .child("A1")
                .child("Log")
                .child(curDate)
                .addValueEventListener(dataListener)
        }
    }
}