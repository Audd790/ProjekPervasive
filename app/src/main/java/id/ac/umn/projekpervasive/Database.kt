package id.ac.umn.projekpervasive

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Database {
    private val databaseReference: DatabaseReference

    init {
        databaseReference = FirebaseDatabase.getInstance().reference
    }

    fun readDataFromFirebase(path: String, listener: ValueEventListener) {
        databaseReference.child(path).addListenerForSingleValueEvent(listener)
    }

    data class fish(val lvlAir: String, val suhuAir: String, val phAir: String)
}
