package com.example.myanimeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myanimeapp.databinding.ActivityDashboardUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardUserBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var dbref : DatabaseReference
    private lateinit var animeRecyclerView : RecyclerView
    private lateinit var animeArrayList : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        animeRecyclerView = findViewById(R.id.animeRv)
        animeRecyclerView.layoutManager = LinearLayoutManager(this)
        animeRecyclerView.setHasFixedSize(true)

        animeArrayList = arrayListOf<User>()
        getUserData()



        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBut.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        binding.profileBut.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("anime")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

            if (snapshot.exists()){
                for (animeSnapshot in snapshot.children) {


                    val user = animeSnapshot.getValue(User::class.java)
                    animeArrayList.add(user!!)
                }

                animeRecyclerView.adapter = MyAdapter(animeArrayList)

            }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            startActivity(Intent(this, MainActivity:: class.java))
            finish()
        }
        else{
            val email = firebaseUser.email
            binding.subTitleTv.text = email
        }
    }
}