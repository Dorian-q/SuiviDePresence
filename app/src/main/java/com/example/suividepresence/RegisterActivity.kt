package com.example.suividepresence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.suividepresence.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // si on clique sur le bouton 's inscrire' on exécute le code suivant
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            when {

                //s'il manque l'adresse mail
                TextUtils.isEmpty(binding.email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Veuillez saisir votre email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //s'il manque le mot de passe
                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Veuillez saisir votre mot de passe",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //si rien n'est vide

                else -> {
                    //on l'utilisateur à mit un espace en trop on l'enlève
                    val email: String = binding.email.text.toString().trim { it <= ' ' }
                    val password: String = binding.password.text.toString().trim { it <= ' ' }

                    //utilisation de firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                            //si l'inscription est validée
                            if (task.isSuccessful) {

                                //inscription utilisateur sur firebase
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                //on prévient l'utilisateur que l'inscription est validée
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Inscription réussi",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@RegisterActivity, HomeTeacherActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                intent.putExtra("password_id", password)
                                startActivity(intent)
                                finish()
                            } else {
                                //si l'inscription n'est pas validée on affiche un message d'erreur
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }
            }
        }
    }
}