package com.example.hw14

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.net.URL

class WebServiceFragment : Fragment(){
    private lateinit var webView: WebView
    data class CatImage(val url: String)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_webservice, container, false)

        // Initialize the WebView
        webView = view.findViewById(R.id.catImageView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Load the website
        webView.loadUrl("https://cataas.com/cat")

        // Fetch and display the image
        fetchAndDisplayCatImage()

        return view
    }

    private fun fetchAndDisplayCatImage() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val catImage = fetchRandomCatImage()
                val url = catImage.url
                val inputStream = URL(url).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                activity?.runOnUiThread {
                    val catImageView = view?.findViewById<ImageView>(R.id.catImageView)
                    catImageView?.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun fetchRandomCatImage(): CatImage {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cataas.com/cat")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val catApi = retrofit.create(CatApi::class.java)

        return catApi.getRandomCat()
    }

    // Retrofit API interface
    interface CatApi {
        @GET("cat")
        suspend fun getRandomCat(): CatImage
    }
}