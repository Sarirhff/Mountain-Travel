package com.example.mountaintravel.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mountaintravel.R
import com.example.mountaintravel.databinding.FragmentProfileBinding
import com.example.mountaintravel.main.User
import com.example.mountaintravel.sign.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private var dbinding : FragmentProfileBinding? = null
    private val binding get () = dbinding
    lateinit var auth : FirebaseAuth
    private lateinit var userFirebase : FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        dbinding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        userFirebase = FirebaseAuth.getInstance().currentUser!!

        infoUser()
        logOut()
    }

        private fun infoUser() {
        val userRef = FirebaseDatabase.getInstance().getReference().child("USERS").child(userFirebase.uid)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val user =snapshot.getValue<User>(User::class.java)
                    user_name.text = user!!.username
                    t_isi_email.text = userFirebase.email
                    t_isi_phone.text = user!!.phoneNumber
                    t_isi_alamat.text = user!!.alamat

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun logOut (){
        binding!!.buttonLogout.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            Toast.makeText(requireContext(), getString(R.string.sign_out), Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}