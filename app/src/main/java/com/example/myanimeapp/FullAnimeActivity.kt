package com.example.myanimeapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup

class FullAnimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_anime)

        val imageView = this.findViewById<ImageView>(R.id.poster_image)
        val ratingA = this.findViewById<TextView>(R.id.aRating)
        val titleA = this.findViewById<TextView>(R.id.aTitle)
        val overviewA = this.findViewById<TextView>(R.id.description)

        val url = "https://jut.su/anime"
        val document = Jsoup.connect(url).get()
        var selectedDiv = document.select("div.aaname")
        var titles = mutableListOf<String>()
        for (element in selectedDiv)
            titles.add(element.text())


        val url1 = "https://jut.su/anime"
        val document1 = Jsoup.connect(url1).get()
        val imageElements = document1.select("div.all_anime_image").first()
        val imageHref = imageElements?.attr("style").toString()
        val finalUrl = extractUrl(imageHref)



        println("$finalUrl")





        println(titles[0])
        println("${titles.size}")

        
    }
}