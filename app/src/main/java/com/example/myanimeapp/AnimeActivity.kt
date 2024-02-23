package com.example.myanimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myanimeapp.R.layout
import org.jsoup.Jsoup

class AnimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_anime)


        val url = "https://jut.su/anime"
        val document = Jsoup.connect(url).get()
        var selectedDiv = document.select("div.aaname")
        var titles = mutableListOf<String>()
        for (element in selectedDiv) {
            titles.add(element.text())
        }


        val url1 = "https://jut.su/anime"
        val document1 = Jsoup.connect(url1).get()
            val imageElements = document1.select("image[href] style[src]").text()
                println("Ссылка на изображение: ${imageElements.length}")
        println("$imageElements")





        println("Содержимое: ${titles}")
        println("${titles.size}")



        val linkToProfile: ImageButton = findViewById(R.id.goToProfile)
        linkToProfile.setOnClickListener() {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
    }
}











































