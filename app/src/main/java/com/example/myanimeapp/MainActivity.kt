package com.example.myanimeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myanimeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBut.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
                   startActivity(intent)
        }

        binding.skipBut.setOnClickListener{
            startActivity(Intent(this, GuestActivity::class.java))
        }
        }
    }
