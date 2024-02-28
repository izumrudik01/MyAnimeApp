package com.example.myanimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myanimeapp.R.layout

class AnimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_anime)


//        val url = "https://jut.su/anime"
//        val document = Jsoup.connect(url).get()
//        var selectedDiv1 = document.select("div.aaname").first()?.text()
//        var titles = listOf(selectedDiv1)

//        val url1 = "https://jut.su/anime"
//        val document1 = Jsoup.connect(url1).get()
//            val imageElements = document1.select("div.all_anime_image").first()
//        val imageHref = imageElements?.attr("style").toString()
//        val finalUrl = extractUrl(imageHref)
//
//
//
//        println("$finalUrl")
//
//
//
//
//
//        println(titles)
//        println("${titles.size}")




        val linkToProfile: ImageButton = findViewById(R.id.goToProfile)
        linkToProfile.setOnClickListener() {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
    }



    }


fun extractUrl(input: String): String? {
    val regex = Regex("url\\('([^']+)'\\)")
    val matchResult = regex.find(input)
    return matchResult?.groupValues?.getOrNull(1)
}















































