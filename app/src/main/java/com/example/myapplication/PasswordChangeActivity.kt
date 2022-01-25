package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {
    private lateinit var editTextTextPassword: EditText
    private lateinit var editTextReptPassword: EditText
    private lateinit var buttonChangePassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()


        registerListeners()
    }


    private fun init(){
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        editTextReptPassword = findViewById(R.id.editTextReptPassword)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)

    }

    private fun registerListeners(){

        buttonChangePassword.setOnClickListener {
            val newPassword = editTextTextPassword.text.toString()
            val repeatPassword = editTextReptPassword.text.toString()

            if(newPassword.isEmpty() || repeatPassword.isEmpty()){
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(newPassword != repeatPassword){
                Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .currentUser
                ?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }


        }


    }
}