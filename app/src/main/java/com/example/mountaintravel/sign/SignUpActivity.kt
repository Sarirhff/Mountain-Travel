package com.example.mountaintravel.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.mountaintravel.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        binding.toLogin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignup.setOnClickListener{
            val email = binding.editEmailSignup.text.toString()
            val password = binding.editPassSignup.text.toString()

            if(email.isEmpty()){
                binding.editEmailSignup.error = "email harus diisi"
                binding.editEmailSignup.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editEmailSignup.error = "email tidak valid"
                binding.editEmailSignup.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.editPassSignup.error = "password harus diisi"
                binding.editPassSignup.requestFocus()
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.editPassSignup.error = "password minimal 6 karakter"
                binding.editPassSignup.requestFocus()
                return@setOnClickListener
            }

            signupFirebase(email,password)
        }
    }

    private fun signupFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Sign Up Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}