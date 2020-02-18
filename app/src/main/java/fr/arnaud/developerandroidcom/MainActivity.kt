package fr.arnaud.developerandroidcom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


// Access a Cloud Firestore instance from your Activity
val db = FirebaseFirestore.getInstance()

const val EXTRA_MESSAGE = "fr.arnaud.developerandroidcom.MESSAGE"

class MainActivity : AppCompatActivity() {

    private val TAG = "MyActivity"
    var array = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("hello " + db);

        println("--------------------- GET DATA START")

        var i = 1
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    array += "${document.id} => ${document.data} "
                }
                println(array)
                println("--------------------- GET DATA END")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                println("--------------------- GET DATA END")
            }
    }

        /** Called when the user tape the Send button */
        fun sendMessage(view: View) {
            // Do something in response to button

            val editText = findViewById<EditText>(R.id.editText)
            val message = editText.text.toString()
            val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
        startActivity(intent)

    }
}
