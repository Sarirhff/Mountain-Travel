package com.example.mountaintravel.sign

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.mountaintravel.databinding.ActivitySignUpBinding
import com.example.mountaintravel.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var ref: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("USERS")

        binding.toLogin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSignup.setOnClickListener {
            val email = binding.editEmailSignup.text.toString().trim()
            val password = binding.editPassSignup.text.toString().trim()
            val username = binding.editUsername.text.toString().trim()
            val phoneNumber = binding.editNumhp.text.toString().trim()


            if (email.isEmpty()) {
                binding.editEmailSignup.error = "email harus diisi"
                binding.editEmailSignup.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmailSignup.error = "email tidak valid"
                binding.editEmailSignup.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.editPassSignup.error = "password harus diisi"
                binding.editPassSignup.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.editPassSignup.error = "password minimal 6 karakter"
                binding.editPassSignup.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.editUsername.error = "Full Name required"
                binding.editUsername.requestFocus()
                return@setOnClickListener

            }
            if (phoneNumber.isEmpty()) {
                binding.editNumhp.error = "Phone Number required"
                binding.editNumhp.requestFocus()
                return@setOnClickListener

            }

            signupFirebase(email, password, username, phoneNumber)
        }
    }

    private fun signupFirebase(
        email: String,
        username: String,
        password: String,
        phoneNumber: String,

    ) {
        val progressDialog = ProgressDialog(this@SignUpActivity)
        progressDialog.setTitle("Registratation User")
        progressDialog.setMessage("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    saveUser(email, username, phoneNumber, progressDialog)
                } else {
                    val message = it.exception!!.toString()
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUser(
        username: String,
        email: String,
        phoneNumber: String,
        progressDialog: ProgressDialog
    ) {
        val currentUserId = auth.currentUser!!.uid
        ref = FirebaseDatabase.getInstance().reference.child("USERS")
        val userMap = HashMap<String, Any>()
        userMap["id"] = currentUserId
        userMap["email"] = email
        userMap["username"] = username
        userMap["phoneNumber"] = phoneNumber
        userMap["alamat"] = ""

        ref.child(currentUserId).setValue(userMap).addOnCompleteListener {
            if (it.isSuccessful) {
                progressDialog.dismiss()
                Toast.makeText(this, "SignUp is Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                val message = it.exception!!.toString()
                Toast.makeText(this, "Error : $message", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
        }
    }
}