package com.example.myanimeapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myanimeapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar
import java.util.Locale


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        firebaseAuth = FirebaseAuth.getInstance()
        loadUserInfo()

        binding.backBut.setOnClickListener {
            onBackPressed()
        }


    }

    companion object {
        fun formatTimeStamp(timestamp: Long): String {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis - timestamp
            return DateFormat.format("dd/MM/yyyy", cal).toString()
        }
    }

    private fun loadUserInfo() {
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseAuth.uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val email = "${snapshot.child("email").value}"
                    val name = "${snapshot.child("name").value}"
                    val profileImage = "${snapshot.child("profileImage").value}"
                    val timestamp = "${snapshot.child("timestamp").value}"
                    val uid = "${snapshot.child("uid").value}"

                    val formattedDate = formatTimeStamp(timestamp.toLong())

                    binding.nameTv.text = name
                    binding.emailTv.text = email
                    binding.memberDateTv.text = formattedDate

                   try {
                       Glide.with(this@ProfileActivity)
                           .load(profileImage).placeholder(R.drawable.ic_person_gray)
                           .into(binding.profileIv)
                   }
                   catch (e: Exception) {

                   }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}