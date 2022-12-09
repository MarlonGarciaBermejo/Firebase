package com.firstapp.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.DropBoxManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddItemActivity1 : AppCompatActivity() {


    lateinit var db : FirebaseFirestore
    lateinit var nameView : EditText
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item1)

        auth = Firebase.auth
        db = Firebase.firestore

        nameView = findViewById(R.id.nameTextView)

        val button = findViewById<Button>(R.id.saveButton)
        button.setOnClickListener {
            saveItem()
        }

    }

     fun saveItem() {
         val item = ShoppingItems(name = nameView.text.toString())
         nameView.setText("")

         val user = auth.currentUser
         if(user == null){
             return
         }

         db.collection("users").document(user.uid).collection("items").add(item)
     }
}
