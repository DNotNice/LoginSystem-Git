package com.example.LoginSystem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
  private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var edtSignUp: Button
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.email)
        edtPass = findViewById(R.id.password)
        btnLogin = findViewById(R.id.login)
        edtSignUp = findViewById(R.id.signup)

        edtSignUp.setOnClickListener{
            val intent = Intent(
                this ,
                signUp::class.java
            )
            startActivity(intent)
        }
        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()
            login(email,password)
        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,HomePage::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User does not exist *_*",Toast.LENGTH_SHORT).show()
                }
            }

    }
}