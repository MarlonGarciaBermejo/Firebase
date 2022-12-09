package com.firstapp.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var emailView: EditText
    lateinit var passwordView: EditText

    val shoppingList = mutableListOf<ShoppingItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = Firebase.auth
        emailView = findViewById(R.id.emailEditText)
        passwordView = findViewById(R.id.passwordEditText)

        val signupButton = findViewById<Button>(R.id.signupButton)
        signupButton.setOnClickListener {
            signUp()
        }
        val signinButton = findViewById<Button>(R.id.signinButton)
        signinButton.setOnClickListener {
            signIn()

        }

        if (auth.currentUser != null) {
            goToAddActivity()
        }
    }
        fun goToAddActivity(){
            val intent = Intent(this, AddItemActivity1::class.java)
            startActivity(intent)

    }

    fun signIn() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return

        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "Sign in success")
                    goToAddActivity()
                } else {
                    Log.d("!!!", "user not signed in ${task.exception}")
                }
            }
                }


  fun signUp() {
      val email = emailView.text.toString()
      val password = passwordView.text.toString()

      if (email.isEmpty() || password.isEmpty()) {
          return

      }

      auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
              if (task.isSuccessful) {
                  Log.d("!!!", "Create success")
                  goToAddActivity()
              } else {
                  Log.d("!!!", "user not created ${task.exception}")
              }
          }

  }
}



/*
        val db = Firebase.firestore

        /*  val item1 = ShoppingItems("gurka", false, "grönsak")
        val item2 = ShoppingItems("mjölk", false, "mejeri")
        val item3 = ShoppingItems("bröd", false, "frukost")
        val item4 = ShoppingItems("skinka", false, "kött")

        db.collection("items").add(item1)
        db.collection("items").add(item2)
        db.collection("items").add(item3)
        db.collection("items").add(item4) */

        val docRef = db.collection("items")

        docRef.get().addOnSuccessListener { documentSnapshot ->
             for (document in documentSnapshot.documents) {
                 val item = document.toObject<ShoppingItems>()
                 if (item != null) {
                     shoppingList.add(item)
                 }
             }

            printShoppingItems()

        }

        docRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null)
                for (document in snapshot.documents) {
                    val item = document.toObject<ShoppingItems>()
                    if (item != null) {
                        shoppingList.add(item)
                    }

                }

        }

        printShoppingItems()
    }


    fun printShoppingItems() {
        for (item in shoppingList) {
            Log.d("!!!", "${item.name}")
        }
    }
}

 */
