package com.example.suividepresence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.example.suividepresence.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.text.DateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        val currentTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)
        val textViewDate = findViewById<TextView>(R.id.date)
        textViewDate.setText(currentDate)
        val textViewTime = findViewById<TextView>(R.id.time)
        textViewTime.setText(currentTime)

        binding.btnLogin.setOnClickListener{
            when
            {
                TextUtils.isEmpty(binding.email.text.toString().trim { it <= ' ' }) ->
                {
                    Toast.makeText(this, "Veuillez entrer un email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) ->
                {
                    Toast.makeText(this, "Veuillez entrer un Mot de passe", Toast.LENGTH_SHORT).show()
                }

                else ->
                {
                    val email : String = binding.email.text.toString().trim{ it <= ' ' }
                    val password : String = binding.password.text.toString().trim{ it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            Toast.makeText(this, "Vous êtes bien connecté", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeTeacherActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}