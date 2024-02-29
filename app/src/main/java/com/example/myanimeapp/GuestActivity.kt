package com.example.myanimeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myanimeapp.databinding.ActivityGuestBinding


class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BackBut.setOnClickListener {
            onBackPressed()
        }

    }
}